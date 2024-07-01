package org.comida.module.product.controller.admin.storeproduct;

import org.comida.framework.common.pojo.CommonResult;
import org.comida.framework.common.pojo.PageResult;
import org.comida.module.product.controller.admin.storeproduct.vo.*;
import org.comida.module.product.convert.storeproduct.StoreProductConvert;
import org.comida.module.product.dal.dataobject.storeproduct.StoreProductDO;
import org.comida.module.product.service.storeproduct.StoreProductService;
import org.comida.module.product.service.storeproduct.dto.StoreProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.comida.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 商品")
@RestController
@RequestMapping("/product/store-product")
@Validated
public class StoreProductController {

    @Resource
    private StoreProductService storeProductService;


    @PostMapping("/create")
    @Operation(summary = "创建商品")
    public CommonResult<Boolean> createStoreProduct(@Validated @RequestBody StoreProductDto storeProductDto) {
        storeProductService.insertAndEditYxStoreProduct(storeProductDto);
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新商品")
    public CommonResult<Boolean> updateStoreProduct(@Valid @RequestBody StoreProductUpdateReqVO updateReqVO) {
        storeProductService.updateStoreProduct(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除商品")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteStoreProduct(@RequestParam("id") Long id) {
        storeProductService.deleteStoreProduct(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商品")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<StoreProductRespVO> getStoreProduct(@RequestParam("id") Long id) {
        StoreProductDO storeProduct = storeProductService.getStoreProduct(id);
        return success(StoreProductConvert.INSTANCE.convert(storeProduct));
    }

    @GetMapping("/list")
    @Operation(summary = "获得商品列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<List<StoreProductRespVO>> getStoreProductList(@RequestParam("ids") Collection<Long> ids) {
        List<StoreProductDO> list = storeProductService.getStoreProductList(ids);
        return success(StoreProductConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得商品分页")
    public CommonResult<PageResult<StoreProductRespVO>> getStoreProductPage(@Valid StoreProductPageReqVO pageVO) {
        PageResult<StoreProductDO> pageResult = storeProductService.getStoreProductPage(pageVO);
        return success(StoreProductConvert.INSTANCE.convertPage(pageResult));
    }

    @Operation(summary = "获取商品信息")
    @GetMapping(value = "/info/{id}")
    public CommonResult<Map<String,Object>> info(@PathVariable Long id){
        return success(storeProductService.getProductInfo(id));
    }


    @Operation(summary = "生成属性")
    @PostMapping(value = "/isFormatAttr/{id}")
    public CommonResult<Map<String,Object>> isFormatAttr(@PathVariable Long id,@RequestBody String jsonStr){
        return success(storeProductService.getFormatAttr(id,jsonStr,false));
    }

    @Operation(summary = "商品上架/下架")
    @GetMapping(value = "/sale")
    public CommonResult<Boolean> onSale(@RequestParam("id") Long id,@RequestParam("type") int status){
        storeProductService.onSale(id,status);
        return success(true);
    }

}
