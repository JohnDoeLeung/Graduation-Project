package org.comida.module.product.controller.app.category.vo;

import org.comida.module.product.controller.app.product.vo.AppStoreProductRespVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Schema(description = "用户 APP - 商品分类 Response VO")
public class AppCategoryRespVO {

    @Schema(description = "分类编号", required = true, example = "2")
    private Long id;

    @Schema(description = "分类名称", required = true, example = "办公文具")
    @NotBlank(message = "分类名称不能为空")
    private String name;

    @Schema(description = "商品列表", required = true)
    private List<AppStoreProductRespVo> goodsList;

}
