package org.comida.module.member.enums;

import org.comida.framework.common.exception.ErrorCode;

/**
 * Member 错误码枚举类
 *
 * member 系统，使用 1-004-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 用户相关  1004001000============
    ErrorCode USER_NOT_EXISTS = new ErrorCode(1004001000, "用户不存在");
    ErrorCode USER_PASSWORD_FAILED = new ErrorCode(1004001001, "密码校验失败");
    ErrorCode USER_EXISTS = new ErrorCode(1004001002, "用户已存在");
    ErrorCode MINI_AUTH_LOGIN_BAD = new ErrorCode(1004004002, "登录失败，请联系管理员");

    // ========== 用户收件地址 1004004000 ==========
    ErrorCode USER_ADDRESS_NOT_EXISTS = new ErrorCode(1004004000, "用户收件地址不存在");
    ErrorCode USER_ADDRESS_PARAM_NOT_EXISTS = new ErrorCode(1004004001, "用户收件地址参数错误");
    ErrorCode COUPON_NOT_CONDITION = new ErrorCode(1004004003, "此优惠券不满足使用提交");

}
