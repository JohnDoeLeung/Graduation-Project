package org.comida.module.system.controller.admin.oauth2;

import cn.hutool.core.collection.CollUtil;
import org.comida.framework.common.pojo.CommonResult;
import org.comida.module.system.controller.admin.oauth2.vo.user.OAuth2UserInfoRespVO;
import org.comida.module.system.controller.admin.oauth2.vo.user.OAuth2UserUpdateReqVO;
import org.comida.module.system.convert.oauth2.OAuth2UserConvert;
import org.comida.module.system.dal.dataobject.user.AdminUserDO;
import org.comida.module.system.service.user.AdminUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static org.comida.framework.common.pojo.CommonResult.success;
import static org.comida.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * 提供给外部应用调用为主
 *
 * 1. 在 getUserInfo 方法上，添加      ("@ss.hasScope('user.read')") 注解，声明需要满足 scope = user.read
 * 2. 在 updateUserInfo 方法上，添加      ("@ss.hasScope('user.write')") 注解，声明需要满足 scope = user.write
 *
  */
@Tag(name = "管理后台 - OAuth2.0 用户")
@RestController
@RequestMapping("/system/oauth2/user")
@Validated
@Slf4j
public class OAuth2UserController {

    @Resource
    private AdminUserService userService;

    @GetMapping("/get")
    @Operation(summary = "获得用户基本信息")
    public CommonResult<OAuth2UserInfoRespVO> getUserInfo() {
        // 获得用户基本信息
        AdminUserDO user = userService.getUser(getLoginUserId());
        OAuth2UserInfoRespVO resp = OAuth2UserConvert.INSTANCE.convert(user);

        return success(resp);
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户基本信息")
    public CommonResult<Boolean> updateUserInfo(@Valid @RequestBody OAuth2UserUpdateReqVO reqVO) {
        // 这里将 UserProfileUpdateReqVO =》UserProfileUpdateReqVO 对象，实现接口的复用。
        // 主要是，AdminUserService 没有自己的 BO 对象，所以复用只能这么做
        userService.updateUserProfile(getLoginUserId(), OAuth2UserConvert.INSTANCE.convert(reqVO));
        return success(true);
    }

}
