package org.comida.module.coupon.controller.admin.coupon;

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

import org.comida.module.coupon.controller.admin.coupon.vo.*;
import org.comida.module.coupon.dal.dataobject.coupon.CouponDO;
import org.comida.module.coupon.convert.coupon.CouponConvert;
import org.comida.module.coupon.service.coupon.CouponService;

@Tag(name = "管理后台 - 优惠券")
@RestController
@RequestMapping("/coupon/")
@Validated
public class CouponController {

    @Resource
    private CouponService Service;

    @PostMapping("/create")
    @Operation(summary = "创建优惠券")
    public CommonResult<Long> create(@Valid @RequestBody CouponCreateReqVO createReqVO) {
        return success(Service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新优惠券")
    public CommonResult<Boolean> update(@Valid @RequestBody CouponUpdateReqVO updateReqVO) {
        Service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除优惠券")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> delete(@RequestParam("id") Long id) {
        Service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得优惠券")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<CouponRespVO> get(@RequestParam("id") Long id) {
        CouponDO couponDO = Service.get(id);
        return success(CouponConvert.INSTANCE.convert(couponDO));
    }

    @GetMapping("/list")
    @Operation(summary = "获得优惠券列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<List<CouponRespVO>> getList(@RequestParam("ids") Collection<Long> ids) {
        List<CouponDO> list = Service.getList(ids);
        return success(CouponConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得优惠券分页")
    public CommonResult<PageResult<CouponRespVO>> getPage(@Valid CouponPageReqVO pageVO) {
        PageResult<CouponDO> pageResult = Service.getPage(pageVO);
        return success(CouponConvert.INSTANCE.convertPage(pageResult));
    }
}
