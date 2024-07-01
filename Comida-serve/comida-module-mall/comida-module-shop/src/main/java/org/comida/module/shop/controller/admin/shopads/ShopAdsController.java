package org.comida.module.shop.controller.admin.shopads;

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

import org.comida.module.shop.controller.admin.shopads.vo.*;
import org.comida.module.shop.dal.dataobject.shopads.ShopAdsDO;
import org.comida.module.shop.convert.shopads.ShopAdsConvert;
import org.comida.module.shop.service.shopads.ShopAdsService;

@Tag(name = "管理后台 - 广告图管理")
@RestController
@RequestMapping("/shop/ads")
@Validated
public class ShopAdsController {

    @Resource
    private ShopAdsService adsService;

    @PostMapping("/create")
    @Operation(summary = "创建广告图管理")
    public CommonResult<Long> createAds(@Valid @RequestBody ShopAdsCreateReqVO createReqVO) {
        return success(adsService.createAds(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新广告图管理")
    public CommonResult<Boolean> updateAds(@Valid @RequestBody ShopAdsUpdateReqVO updateReqVO) {
        adsService.updateAds(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除广告图管理")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteAds(@RequestParam("id") Long id) {
        adsService.deleteAds(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得广告图管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<ShopAdsRespVO> getAds(@RequestParam("id") Long id) {
        ShopAdsDO ads = adsService.getAds(id);
        return success(ShopAdsConvert.INSTANCE.convert(ads));
    }

    @GetMapping("/list")
    @Operation(summary = "获得广告图管理列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<List<ShopAdsRespVO>> getAdsList(@RequestParam("ids") Collection<Long> ids) {
        List<ShopAdsDO> list = adsService.getAdsList(ids);
        return success(ShopAdsConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得广告图管理分页")
    public CommonResult<PageResult<ShopAdsRespVO>> getAdsPage(@Valid ShopAdsPageReqVO pageVO) {
        PageResult<ShopAdsDO> pageResult = adsService.getAdsPage(pageVO);
        return success(ShopAdsConvert.INSTANCE.convertPage(pageResult));
    }
}
