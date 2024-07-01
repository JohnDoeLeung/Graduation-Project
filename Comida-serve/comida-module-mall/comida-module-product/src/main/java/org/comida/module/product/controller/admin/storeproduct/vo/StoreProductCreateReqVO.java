package org.comida.module.product.controller.admin.storeproduct.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 商品创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoreProductCreateReqVO extends StoreProductBaseVO {

    @Schema(description = "商品简介", required = true)
    @NotNull(message = "商品简介不能为空")
    private String storeInfo;


    @Schema(description = "分类id", required = true, example = "4928")
    @NotNull(message = "分类id不能为空")
    private String cateId;



    @Schema(description = "状态（0：未上架，1：上架）")
    private Integer isShow;

    @Schema(description = "获得积分")
    private BigDecimal giveIntegral;

    @Schema(description = "规格 0单 1多", example = "1")
    private Integer specType;

}
