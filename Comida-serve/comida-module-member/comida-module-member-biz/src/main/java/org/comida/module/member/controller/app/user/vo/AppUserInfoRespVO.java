package org.comida.module.member.controller.app.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "用户 APP - 用户个人信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserInfoRespVO {

    @Schema(description = "用户id", required = true, example = "comida")
    private Long id;

    @Schema(description = "用户昵称", required = true, example = "comida")
    private String nickname;

    @Schema(description = "用户头像", required = true, example = "/infra/file/get/35a12e57-4297-4faa-bf7d-7ed2f211c952")
    private String avatar;

    @Schema(description = "用户手机号", required = true, example = "15601691300")
    private String mobile;

    @Schema(description = "生日", required = true, example = "2023-10-11")
    private String birthday;
}
