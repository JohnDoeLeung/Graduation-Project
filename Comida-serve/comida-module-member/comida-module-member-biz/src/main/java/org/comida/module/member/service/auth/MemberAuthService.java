package org.comida.module.member.service.auth;

import org.comida.module.member.controller.app.auth.vo.*;

import javax.validation.Valid;

/**
 * 会员的认证 Service 接口
 *
 * 提供用户的账号密码登录、token 的校验等认证相关的功能
 *
  */
public interface MemberAuthService {

    /**
     * 手机 + 密码登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AppAuthLoginRespVO login(@Valid AppAuthLoginReqVO reqVO);

    /**
     * 基于 token 退出登录
     *
     * @param token token
     */
    void logout(String token);

    /**
     * 微信小程序的一键登录
     *
     * @param loginVO 登录信息
     * @return 登录结果
     */
    AppAuthLoginRespVO weixinMiniAppLogin(AppWeixinMiniLoginVO loginVO);

    /**
     * 修改用户密码
     * @param userId 用户id
     * @param userReqVO 用户请求实体类
     */
    void updatePassword(Long userId, AppAuthUpdatePasswordReqVO userReqVO);

    /**
     * 忘记密码
     * @param userReqVO 用户请求实体类
     */
    void resetPassword(AppAuthResetPasswordReqVO userReqVO);

    /**
     * 刷新访问令牌
     *
     * @param refreshToken 刷新令牌
     * @return 登录结果
     */
    AppAuthLoginRespVO refreshToken(String refreshToken);

    void userRegister(AppAuthRegisterReqVO reqVO);
}
