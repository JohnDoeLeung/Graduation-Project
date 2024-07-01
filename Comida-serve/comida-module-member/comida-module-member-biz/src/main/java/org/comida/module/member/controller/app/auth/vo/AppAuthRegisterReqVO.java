package org.comida.module.member.controller.app.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.comida.framework.common.validation.Mobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Schema(description = "用户 APP - 用户注册")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppAuthRegisterReqVO {

    @Schema(description = "用户账户(跟accout一样)", example = "王五")
    @NotEmpty(message = "密码不能为空")
    private String username;

    @Schema(description = "密码", required = true, example = "123456")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    @Schema(description = "昵称", required = true, example = "咖啡豆")
    @NotEmpty(message = "昵称不能为空")
    private String nickname;

    @Schema(description = "手机号", required = true, example = "17011960827")
    @NotEmpty(message = "手机号不能为空")
    @Mobile
    private String mobile;

}
