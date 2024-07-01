package org.comida.module.product.controller.admin.storeproductrule;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.*;
import java.util.*;

import org.comida.framework.common.pojo.PageResult;
import org.comida.framework.common.pojo.CommonResult;
import static org.comida.framework.common.pojo.CommonResult.success;

import org.comida.module.product.controller.admin.storeproductrule.vo.*;
import org.comida.module.product.dal.dataobject.storeproductrule.StoreProductRuleDO;
import org.comida.module.product.convert.storeproductrule.StoreProductRuleConvert;
import org.comida.module.product.service.storeproductrule.StoreProductRuleService;

@Tag(name = "管理后台 - 商品规则值(规格)")
@RestController
@RequestMapping("/product/store-product-rule")
@Validated
public class StoreProductRuleController {

    @Resource
    private StoreProductRuleService storeProductRuleService;


    @PostMapping("/save/{id}")
    @Operation(summary = "创建与更新商品规则值(规格)")
    public CommonResult<Integer> createStoreProductRule(@Valid @RequestBody StoreProductRuleCreateReqVO createReqVO,@PathVariable Integer id) {
        if(id != null && id > 0){
            StoreProductRuleUpdateReqVO updateReqVO = new StoreProductRuleUpdateReqVO();
            updateReqVO.setId(id);
            updateReqVO.setRuleName(createReqVO.getRuleName());
            updateReqVO.setRuleValue(createReqVO.getRuleValue());
            storeProductRuleService.updateStoreProductRule(updateReqVO);
            return success(1);
        }else{
           return success(storeProductRuleService.createStoreProductRule(createReqVO));
        }
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除商品规则值(规格)")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteStoreProductRule(@RequestParam("id") Integer id) {
        storeProductRuleService.deleteStoreProductRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商品规则值(规格)")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<StoreProductRuleRespVO> getStoreProductRule(@RequestParam("id") Integer id) {
        StoreProductRuleDO storeProductRule = storeProductRuleService.getStoreProductRule(id);
        return success(StoreProductRuleConvert.INSTANCE.convert(storeProductRule));
    }

    @GetMapping("/list")
    @Operation(summary = "获得商品规则值(规格)列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<List<StoreProductRuleRespVO>> getStoreProductRuleList(@RequestParam("ids") Collection<Integer> ids) {
        List<StoreProductRuleDO> list = storeProductRuleService.getStoreProductRuleList(ids);
        return success(StoreProductRuleConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得商品规则值(规格)分页")
    public CommonResult<PageResult<StoreProductRuleRespVO>> getStoreProductRulePage(@Valid StoreProductRulePageReqVO pageVO) {
        PageResult<StoreProductRuleDO> pageResult = storeProductRuleService.getStoreProductRulePage(pageVO);
        System.out.println("aa:"+pageResult);
        return success(StoreProductRuleConvert.INSTANCE.convertPage(pageResult));
    }
}
