package org.comida.module.member.service.auth;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.util.ObjectUtil;
import org.comida.framework.common.enums.CommonStatusEnum;
import org.comida.framework.common.enums.UserTypeEnum;
import org.comida.module.member.controller.app.auth.vo.*;
import org.comida.module.member.convert.auth.AuthConvert;
import org.comida.module.member.convert.user.UserConvert;
import org.comida.module.member.dal.dataobject.user.MemberUserDO;
import org.comida.module.member.dal.mysql.user.MemberUserMapper;
import org.comida.module.member.dal.redis.order.MiniRedisDAO;
import org.comida.module.member.service.user.MemberUserService;
import org.comida.module.system.api.oauth2.OAuth2TokenApi;
import org.comida.module.system.api.oauth2.dto.OAuth2AccessTokenCreateReqDTO;
import org.comida.module.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;
import org.comida.module.system.api.social.SocialUserApi;
import org.comida.module.system.enums.oauth2.OAuth2ClientConstants;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.math.BigDecimal;

import static org.comida.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.comida.module.member.enums.ErrorCodeConstants.*;
import static org.comida.module.system.enums.ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS;
import static org.comida.module.system.enums.ErrorCodeConstants.AUTH_LOGIN_USER_DISABLED;

/**
 * 用户的认证 Service 接口
 *
  */
@Service
@Slf4j
public class MemberAuthServiceImpl implements MemberAuthService {

    @Resource
    private MemberUserService userService;

    @Resource
    private OAuth2TokenApi oauth2TokenApi;

    @Resource
    private WxMaService wxMaService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private MemberUserMapper userMapper;

    @Resource
    private MiniRedisDAO miniRedisDAO;

    // 使用手机 + 密码，进行登录
    @Override
    public AppAuthLoginRespVO login(AppAuthLoginReqVO reqVO) {

        MemberUserDO user = login0(reqVO.getMobile(), reqVO.getPassword());
        if (user == null) {
            //抛出异常
            throw exception(USER_PASSWORD_FAILED);

        }
        AppAuthLoginRespVO appAuthLoginRespVO = createTokenAfterLoginSuccess(user,reqVO.getMobile());

        appAuthLoginRespVO.setUserInfo(UserConvert.INSTANCE.convert3(user));
        // 创建 Token 令牌，记录登录日志
        return appAuthLoginRespVO;
    }

    /**
     * 微信小程序的一键登录
     * @param loginVO 登录信息
     * @return 登录结果
     */
    @Override
    public AppAuthLoginRespVO weixinMiniAppLogin(AppWeixinMiniLoginVO loginVO) {

        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(loginVO.getCode());
            log.info(session.getOpenid());
            miniRedisDAO.set(session.getSessionKey(), session.getOpenid());
            //根据openid查用户是否存在
            MemberUserDO memberUserDO = userMapper.selectOne(new LambdaQueryWrapper<MemberUserDO>()
                    .eq(MemberUserDO::getRoutineOpenid, session.getOpenid()));
            AppAuthLoginRespVO appAuthLoginRespVO = new AppAuthLoginRespVO();

            if (memberUserDO != null) {
                appAuthLoginRespVO = createTokenAfterLoginSuccess(memberUserDO, memberUserDO.getMobile());
                appAuthLoginRespVO.setUserInfo(UserConvert.INSTANCE.convert3(memberUserDO));
                //return appAuthLoginRespVO;
            }
            appAuthLoginRespVO.setOpenId(session.getOpenid());
            return appAuthLoginRespVO;

        } catch (WxErrorException e) {
            log.error(e.getMessage());
            throw exception(MINI_AUTH_LOGIN_BAD);
        }
    }

    private AppAuthLoginRespVO createTokenAfterLoginSuccess(MemberUserDO user, String mobile) {
        // 创建 Token 令牌
        OAuth2AccessTokenRespDTO accessTokenRespDTO = oauth2TokenApi.createAccessToken(new OAuth2AccessTokenCreateReqDTO()
                .setUserId(user.getId()).setUserType(getUserType().getValue())
                .setClientId(OAuth2ClientConstants.CLIENT_ID_DEFAULT));
        // 构建返回结果
        return AuthConvert.INSTANCE.convert(accessTokenRespDTO);
    }

    private MemberUserDO login0(String mobile, String password) {
        // 校验账号是否存在
        MemberUserDO user = userService.getUserByMobile(mobile);
        if (user == null) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }
        return user;
    }

    @Override
    public void logout(String token) {
        // 删除访问令牌
        OAuth2AccessTokenRespDTO accessTokenRespDTO = oauth2TokenApi.removeAccessToken(token);
        if (accessTokenRespDTO == null) {
            return;
        }
    }

    @Override
    public void updatePassword(Long userId, AppAuthUpdatePasswordReqVO reqVO) {
        // 检验旧密码
        MemberUserDO userDO = checkOldPassword(userId, reqVO.getOldPassword());
        // 更新用户密码
        userMapper.updateById(MemberUserDO.builder().id(userDO.getId())
                .password(passwordEncoder.encode(reqVO.getPassword())).build());
    }

    @Override
    public void resetPassword(AppAuthResetPasswordReqVO reqVO) {
        // 检验用户是否存在
        MemberUserDO userDO = checkUserIfExists(reqVO.getMobile());
        // 更新密码
        userMapper.updateById(MemberUserDO.builder().id(userDO.getId())
                .password(passwordEncoder.encode(reqVO.getPassword())).build());
    }

    @Override
    public AppAuthLoginRespVO refreshToken(String refreshToken) {
        OAuth2AccessTokenRespDTO accessTokenDO = oauth2TokenApi.refreshAccessToken(refreshToken, OAuth2ClientConstants.CLIENT_ID_DEFAULT);
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }

    /**
     * 校验旧密码
     * @param id          用户 id
     * @param oldPassword 旧密码
     * @return MemberUserDO 用户实体
     */
    @VisibleForTesting
    public MemberUserDO checkOldPassword(Long id, String oldPassword) {
        MemberUserDO user = userMapper.selectById(id);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }
        // 参数：未加密密码，编码后的密码
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw exception(USER_PASSWORD_FAILED);
        }
        return user;
    }

    public MemberUserDO checkUserIfExists(String mobile) {
        MemberUserDO user = userMapper.selectByMobile(mobile);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }
        return user;
    }

    private String getMobile(Long userId) {
        if (userId == null) {
            return null;
        }
        MemberUserDO user = userService.getUser(userId);
        return user != null ? user.getMobile() : null;
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.MEMBER;
    }

    @Override
    public void userRegister(AppAuthRegisterReqVO authRegisterReqVO) {

        //校验用户是否存在
        boolean flag = checkUsernameIfExists(authRegisterReqVO.getUsername());
        if (flag) {
            // 插入用户
            MemberUserDO user = UserConvert.INSTANCE.convert5(authRegisterReqVO);
            // 加密密码
            user.setPassword(encodePassword(authRegisterReqVO.getPassword()));
            // 使用户状态可用
            user.setStatus(0);

            /*
            测试：为每一个新注册的用户增加余额
             */
            user.setNowMoney(BigDecimal.valueOf(10000));

            userMapper.insert(user);
        }
    }

    public boolean checkUsernameIfExists(String username) {

        MemberUserDO user = userMapper.selectOne(MemberUserDO::getUsername, username);
        if (user != null) {
            throw exception(USER_EXISTS);
        }
        return true;

    }

    /**
     * 对密码进行加密
     * @param password 密码
     * @return 加密后的密码
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
