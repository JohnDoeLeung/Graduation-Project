package org.comida.framework.common.params;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;


@Data
@Schema(description = "用户 APP - 查询参数对象")
public abstract class QueryParam implements Serializable{
    private static final long serialVersionUID = -3263921252635611410L;

    @Schema(description = "页码,默认为1", required = true)
	private Integer page =1;

    @Schema(description = "页大小,默认为10", required = true)
	private Integer limit = 10;

    @Schema(description = "搜索字符串", required = true)
    private String keyword;
}
