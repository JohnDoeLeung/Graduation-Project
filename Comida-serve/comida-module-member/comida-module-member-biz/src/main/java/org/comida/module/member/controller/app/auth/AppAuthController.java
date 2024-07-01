package org.comida.module.member.controller.app.auth;

import cn.hutool.core.util.StrUtil;
import org.comida.framework.common.pojo.CommonResult;
import org.comida.framework.security.config.SecurityProperties;
import org.comida.framework.security.core.annotations.PreAuthenticated;
import org.comida.framework.security.core.util.SecurityFrameworkUtils;
import org.comida.module.member.controller.app.auth.vo.*;
import org.comida.module.member.service.auth.MemberAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.comida.framework.common.pojo.CommonResult.success;
import static org.comida.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 APP - 认证")
@RestController
@RequestMapping("/member/auth")
@Validated
@Slf4j
public class AppAuthController {

    @Resource
    private MemberAuthService authService;

    @Resource
    private SecurityProperties securityProperties;

    @Value("${comida.info.isActive}")
    private Boolean isActive;


    @PostMapping("/login")
    @Operation(summary = "使用手机 + 密码登录")
    public CommonResult<AppAuthLoginRespVO> login(@RequestBody @Valid AppAuthLoginReqVO reqVO) {
        return success(authService.login(reqVO));
    }

    @PostMapping("/logout")
    @PermitAll
    @Operation(summary = "登出系统")
    public CommonResult<Boolean> logout(HttpServletRequest request) {
        String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotBlank(token)) {
            authService.logout(token);
        }
        return success(true);
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "刷新令牌")
    @Parameter(name = "refreshToken", description = "刷新令牌", required = true)
    public CommonResult<AppAuthLoginRespVO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return success(authService.refreshToken(refreshToken));
    }

    @PostMapping("/reset-password")
    @Operation(summary = "重置密码", description = "用户忘记密码时使用")
    @PreAuthenticated
    public CommonResult<Boolean> resetPassword(@RequestBody @Valid AppAuthResetPasswordReqVO reqVO) {
        authService.resetPassword(reqVO);
        return success(true);
    }

    @PostMapping("/update-password")
    @Operation(summary = "修改用户密码", description = "用户修改密码时使用")
    @PreAuthenticated
    public CommonResult<Boolean> updatePassword(@RequestBody @Valid AppAuthUpdatePasswordReqVO reqVO) {
        authService.updatePassword(getLoginUserId(), reqVO);
        return success(true);
    }

    @PostMapping("/register")
    @Operation(summary = "注册=== 手机号+密码+昵称")
    public CommonResult<Boolean> userRegister(@RequestBody @Valid AppAuthRegisterReqVO reqVO) {
        authService.userRegister(reqVO);
        return success(true);
    }

    // ========== 社交登录相关 ==========

    @PostMapping("/auth-session")
    @Operation(summary = "微信小程序登录")
    public CommonResult<AppAuthLoginRespVO> weixinMiniAppLogin2(@RequestBody @Valid AppWeixinMiniLoginVO loginVO) {
        AppAuthLoginRespVO appAuthLoginRespVO = authService.weixinMiniAppLogin(loginVO);
        appAuthLoginRespVO.setIsActive(isActive);
        return success(appAuthLoginRespVO);
    }
}
