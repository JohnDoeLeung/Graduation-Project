package org.comida.module.product.controller.app.product;


import org.comida.framework.common.pojo.CommonResult;
import org.comida.module.product.controller.app.category.vo.AppCategoryRespVO;
import org.comida.module.product.controller.app.product.param.AppStoreProductQueryParam;
import org.comida.module.product.service.storeproduct.AppStoreProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.comida.framework.common.pojo.CommonResult.success;

/**
 * <p>
 * 商品控制器
 * </p>
 *
   * @since 2023-8-16
 */
@Slf4j
@RestController
@Tag(name = "用户 APP - 商品")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/product")
public class AppStoreProductController {

    private final AppStoreProductService storeProductService;
    /**
     * 获取产品列表
     */
    @GetMapping("/products")
    @Operation(summary = "商品列表")
    public CommonResult<List<AppCategoryRespVO>> goodsList(AppStoreProductQueryParam productQueryParam){
        return success(storeProductService.getGoodsList(productQueryParam));
    }









}

