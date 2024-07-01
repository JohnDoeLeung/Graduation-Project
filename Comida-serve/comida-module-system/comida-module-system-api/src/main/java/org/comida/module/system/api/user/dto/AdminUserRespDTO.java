package org.comida.module.system.api.user.dto;

import org.comida.framework.common.enums.CommonStatusEnum;
import lombok.Data;

/**
 * Admin 用户 Response DTO
 **/
@Data
public class AdminUserRespDTO {

    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 帐号状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 手机号码
     */
    private String mobile;

}
