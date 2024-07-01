package org.comida.module.coupon.controller.admin.couponuser;

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

import org.comida.module.coupon.controller.admin.couponuser.vo.*;
import org.comida.module.coupon.dal.dataobject.couponuser.CouponUserDO;
import org.comida.module.coupon.convert.couponuser.CouponUserConvert;
import org.comida.module.coupon.service.couponuser.CouponUserService;

@Tag(name = "管理后台 - 用户领的优惠券")
@RestController
@RequestMapping("/coupon/user")
@Validated
public class CouponUserController {

    @Resource
    private CouponUserService userService;

    @PostMapping("/create")
    @Operation(summary = "创建用户领的优惠券")
    public CommonResult<Integer> createUser(@Valid @RequestBody CouponUserCreateReqVO createReqVO) {
        return success(userService.createUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户领的优惠券")
    public CommonResult<Boolean> updateUser(@Valid @RequestBody CouponUserUpdateReqVO updateReqVO) {
        userService.updateUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户领的优惠券")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteUser(@RequestParam("id") Integer id) {
        userService.deleteUser(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户领的优惠券")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<CouponUserRespVO> getUser(@RequestParam("id") Integer id) {
        CouponUserDO user = userService.getUser(id);
        return success(CouponUserConvert.INSTANCE.convert(user));
    }

    @GetMapping("/list")
    @Operation(summary = "获得用户领的优惠券列表")
    @Parameter(name = "id", description = "编号列表", required = true, example = "1024")
   //      ("  ('coupon:user:query')")
    public CommonResult<List<CouponUserRespVO>> getUserList(@RequestParam("couponId") Integer couponId) {
        List<CouponUserDO> list = userService.getUserList(couponId);
        return success(CouponUserConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户领的优惠券分页")
    //     ("  ('coupon:user:query')")
    public CommonResult<PageResult<CouponUserRespVO>> getUserPage(@Valid CouponUserPageReqVO pageVO) {
        PageResult<CouponUserDO> pageResult = userService.getUserPage(pageVO);
        return success(CouponUserConvert.INSTANCE.convertPage(pageResult));
    }
}
