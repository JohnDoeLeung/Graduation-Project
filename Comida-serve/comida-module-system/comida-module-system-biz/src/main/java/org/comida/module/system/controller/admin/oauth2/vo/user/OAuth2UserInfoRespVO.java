package org.comida.module.system.controller.admin.oauth2.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "管理后台 - OAuth2 获得用户基本信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2UserInfoRespVO {

    @Schema(description = "用户编号", required = true, example = "1")
    private Long id;

    @Schema(description = "用户账号", required = true, example = "comida")
    private String username;

    @Schema(description = "用户昵称", required = true, example = "comida")
    private String nickname;

    @Schema(description = "用户邮箱", example = "comida@yixiang.co")
    private String email;
    @Schema(description = "手机号码", example = "15601691300")
    private String mobile;

    @Schema(description = "用户性别,参见 SexEnum 枚举类", example = "1")
    private Integer sex;

    @Schema(description = "用户头像", example = "https://www.yixiang.co/xxx.png")
    private String avatar;

}

