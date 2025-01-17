package org.comida.module.coupon.controller.app.coupon;

import org.comida.framework.common.enums.ShopCommonEnum;
import org.comida.framework.common.pojo.CommonResult;
import org.comida.framework.security.core.annotations.PreAuthenticated;
import org.comida.module.coupon.controller.app.coupon.vo.AppCouponVO;
import org.comida.module.coupon.controller.app.coupon.vo.AppMyCouponVO;
import org.comida.module.coupon.controller.app.coupon.vo.AppReceVO;
import org.comida.module.coupon.dal.dataobject.couponuser.CouponUserDO;
import org.comida.module.coupon.service.coupon.AppCouponService;
import org.comida.module.coupon.service.couponuser.AppCouponUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.comida.framework.common.pojo.CommonResult.success;
import static org.comida.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * <p>
 * 优惠券控制器
 * </p>
 *
   * @since 2023-8-20
 */
@Slf4j
@RestController
@Tag(name = "用户 APP - 优惠券")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/coupon")
public class AppCouponController {

    private final AppCouponUserService appCouponUserService;
    private final AppCouponService appCouponService;


    @PreAuthenticated
    @GetMapping("/count")
    @Operation(summary = "获取可用优惠券数量")
    public CommonResult<Long> getCount(@RequestParam("shop_id") Integer shopId,
                                                      @RequestParam("type") Integer type) {
        Long uid = getLoginUserId();
        LocalDateTime nowTime = LocalDateTime.now();
        Long count = appCouponUserService.count(new LambdaQueryWrapper<CouponUserDO>()
                .eq(CouponUserDO::getUserId,uid)
                .eq(CouponUserDO::getShopId,shopId)
                .lt(CouponUserDO::getStartTime,nowTime)
                .gt(CouponUserDO::getEndTime,nowTime)
                .and(i->i.eq(CouponUserDO::getType,type).or().eq(CouponUserDO::getType,0))
                .eq(CouponUserDO::getStatus, ShopCommonEnum.IS_STATUS_0));

        return success(count);
    }

    /**
     * 获取我的优惠券
     */
    @PreAuthenticated
    @GetMapping("/my")
    @Parameters({
            @Parameter(name = "shopId", description = "店铺ID",
                     example = "1"),
            @Parameter(name = "page", description = "页码,默认为1",
                    required = true, example = "1"),
            @Parameter(name = "pagesize", description = "页大小,默认为10",
                    required = true, example = "10      ")
    })
    @Operation(summary = "获取我的优惠券")
    public CommonResult<List<AppMyCouponVO>> myList(@RequestParam(value = "shopId",required = false)  Long shopId,
                                                    @RequestParam(value = "page",defaultValue = "1") int page,
                                                    @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        Long uid = getLoginUserId();
        return success(appCouponUserService.getList(uid,shopId,page,pagesize));
    }

    /**
     * 获取未被领取优惠券
     */
    @PreAuthenticated
    @GetMapping("/not")
    @Parameters({
            @Parameter(name = "shopId", description = "店铺ID",
                     example = "1"),
            @Parameter(name = "page", description = "页码,默认为1",
                    required = true, example = "1"),
            @Parameter(name = "pagesize", description = "页大小,默认为10",
                    required = true, example = "10      ")
    })
    @Operation(summary = "获取未被领取优惠券")
    public CommonResult<List<AppCouponVO>> myNotList(@RequestParam(value = "id",required = false)  Long shopId,
                                                     @RequestParam(value = "page",defaultValue = "1") int page,
                                                     @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        Long uid = getLoginUserId();
        return success(appCouponService.getNotList(uid,shopId,page,pagesize));
    }

    /**
     * 领取优惠券
     */
    @PreAuthenticated
    @PostMapping("/receive")
    @Parameters({
            @Parameter(name = "id", description = "优惠券ID",
                    example = "1"),
            @Parameter(name = "code", description = "优惠券兑换码",
                    example = "1")
    })
    @Operation(summary = "获取未被领取优惠券")
    public CommonResult<Boolean> receive(@RequestBody AppReceVO appReceVO){
        Long uid = getLoginUserId();
        appCouponService.receive(uid,appReceVO.getId(),appReceVO.getCode());
        return success(true);
    }


}

