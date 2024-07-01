package org.comida.module.store.controller.admin.storeshop;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import org.comida.framework.common.pojo.PageResult;
import org.comida.framework.common.pojo.CommonResult;
import static org.comida.framework.common.pojo.CommonResult.success;

import org.comida.module.store.controller.admin.storeshop.vo.*;
import org.comida.module.store.dal.dataobject.storeshop.StoreShopDO;
import org.comida.module.store.convert.storeshop.StoreShopConvert;
import org.comida.module.store.service.storeshop.StoreShopService;

@Tag(name = "管理后台 - 门店管理")
@RestController
@RequestMapping("/store/shop")
@Validated
public class StoreShopController {

    @Resource
    private StoreShopService shopService;

    @PostMapping("/create")
    @Operation(summary = "创建门店管理")
    public CommonResult<Long> createShop(@Valid @RequestBody StoreShopCreateReqVO createReqVO) {
        return success(shopService.createShop(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新门店管理")
    public CommonResult<Boolean> updateShop(@Valid @RequestBody StoreShopUpdateReqVO updateReqVO) {
        shopService.updateShop(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除门店管理")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteShop(@RequestParam("id") Long id) {
        shopService.deleteShop(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得门店管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<StoreShopRespVO> getShop(@RequestParam("id") Long id) {
        StoreShopDO shop = shopService.getShop(id);
        return success(StoreShopConvert.INSTANCE.convert(shop));
    }

    @GetMapping("/list")
    @Operation(summary = "获得门店管理列表")
    public CommonResult<List<StoreShopRespVO>> getShopList() {
        List<StoreShopDO> list = shopService.getShopList();
        return success(StoreShopConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得门店管理分页")
    public CommonResult<PageResult<StoreShopRespVO>> getShopPage(@Valid StoreShopPageReqVO pageVO) {
        PageResult<StoreShopDO> pageResult = shopService.getShopPage(pageVO);
        return success(StoreShopConvert.INSTANCE.convertPage(pageResult));
    }
}
