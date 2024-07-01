package org.comida.module.system.controller.admin.user.vo.profile;

import org.comida.module.system.controller.admin.user.vo.user.UserBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "管理后台 - 用户个人中心信息 Response VO")
public class UserProfileRespVO extends UserBaseVO {

    @Schema(description = "用户编号", required = true, example = "1")
    private Long id;

    @Schema(description = "状态,参见 CommonStatusEnum 枚举类", required = true, example = "1")
    private Integer status;

    @Schema(description = "最后登录 IP", required = true, example = "192.168.1.1")
    private String loginIp;

    @Schema(description = "最后登录时间", required = true, example = "时间戳格式")
    private LocalDateTime loginDate;

    @Schema(description = "创建时间", required = true, example = "时间戳格式")
    private LocalDateTime createTime;

}
