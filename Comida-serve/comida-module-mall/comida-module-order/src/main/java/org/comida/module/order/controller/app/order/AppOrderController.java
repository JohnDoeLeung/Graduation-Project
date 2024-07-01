package org.comida.module.order.controller.app.order;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.comida.framework.common.pojo.CommonResult;
import org.comida.framework.security.core.annotations.PreAuthenticated;
import org.comida.module.member.controller.app.user.vo.AppUserOrderCountVo;
import org.comida.module.order.controller.app.order.param.*;
import org.comida.module.order.controller.app.order.vo.AppStoreOrderQueryVo;
import org.comida.module.order.dal.redis.order.AsyncOrderRedisDAO;
import org.comida.module.order.service.storeorder.AppStoreOrderService;
import com.egzosn.pay.spring.boot.core.PayServiceManager;
import com.egzosn.pay.web.support.HttpRequestNoticeParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.comida.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.comida.framework.common.pojo.CommonResult.success;
import static org.comida.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static org.comida.module.order.enums.ErrorCodeConstants.PARAM_ERROR;
import static org.comida.module.order.enums.ErrorCodeConstants.STORE_ORDER_NOT_EXISTS;

/**
 * <p>
 * 订单控制器
 * </p>
 *
   * @since 2023-6-23
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "用户 APP - 订单模块")
@RequestMapping("/order")
public class AppOrderController {

    private final AppStoreOrderService appStoreOrderService;
    private final AsyncOrderRedisDAO asyncOrderRedisDAO;
    private final PayServiceManager manager;




    /**
     * 订单创建
     */
    @PreAuthenticated
    @PostMapping("/create")
    @Operation(summary = "订单创建")
    public CommonResult<Map<String, Object>> create(@RequestBody @Valid AppOrderParam param) {
        Long uid = getLoginUserId();
        return success(appStoreOrderService.createOrder(uid, param));
    }


    /**
     * 订单支付
     */
    @PreAuthenticated
    @PostMapping(value = "/pay")
    @Operation(summary = "订单支付")
    public CommonResult<Map<String, Object>> pay(@RequestBody @Valid AppPayParam param) {
        Long uid = getLoginUserId();
        return success(appStoreOrderService.pay(uid,param));
    }

    /**
     * 支付回调地址
     *
     * @param request   请求
     * @param detailsId 列表id
     * @return 支付是否成功
     */
    @RequestMapping(value = "payBack{detailsId}.json")
    public String payBack(HttpServletRequest request, @PathVariable String detailsId)  {
        return manager.payBack(detailsId, new HttpRequestNoticeParams(request));
    }


    /**
     * 订单列表
     */
    @PreAuthenticated
    @GetMapping("/list")
    @Operation(summary = "订单列表")
    @Parameters({
            @Parameter(name = "type", description = "商品状态,-1全部 默认为0未支付 1待发货 2待收货 3待评价 4已完成 5退款中 6已退款 7退款",
                    required = true, example = "1"),
            @Parameter(name = "page", description = "页码,默认为1",
                    required = true, example = "1"),
            @Parameter(name = "limit", description = "页大小,默认为10",
                    required = true, example = "10      ")
    })
    public CommonResult<List<AppStoreOrderQueryVo>> orderList(@RequestParam(value = "type", defaultValue = "0") int type,
                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "limit", defaultValue = "10") int limit) {
        Long uid = getLoginUserId();
        return success(appStoreOrderService.orderList(uid, type, page, limit));
    }


    /**
     * 订单详情
     */
    @PreAuthenticated
    @GetMapping("/detail/{key}")
    @Operation(summary = "订单详情")
    @Parameter(name = "key", description = "唯一的uni值或者订单号", required = true, example = "10      ")
    public CommonResult<AppStoreOrderQueryVo> detail(@PathVariable String key) {
        Long uid = getLoginUserId();
        if (StrUtil.isEmpty(key)) {
            throw exception(PARAM_ERROR);
        }
        AppStoreOrderQueryVo storeOrder = appStoreOrderService.getOrderInfo(key, uid);
        if (ObjectUtil.isNull(storeOrder)) {
            throw exception(STORE_ORDER_NOT_EXISTS);
        }
        return success(appStoreOrderService.handleOrder(storeOrder));
    }


    /**
     * 订单收货
     */
    @PreAuthenticated
    @PostMapping("/take")
    @Operation(summary = "订单收货")
    public CommonResult<Boolean> orderTake(@RequestBody @Validated AppDoOrderParam param) {
        Long uid = getLoginUserId();
        appStoreOrderService.takeOrder(param.getUni(), uid);
        return success(true);
    }

    /**
     * 订单退款审核
     */
    @PostMapping("/refund")
    @Operation(summary = "订单退款审核")
    public CommonResult<Boolean> refundVerify(@RequestBody AppRefundParam param) {
        Long uid = getLoginUserId();
        appStoreOrderService.orderApplyRefund(param.getRefundReasonWapExplain(),
                param.getRefundReasonWapImg(),
                param.getText(),
                param.getUni(), uid);
        return success(true);
    }




    /**
     * 订单删除
     */
    @PreAuthenticated
    @PostMapping("/del")
    @Operation(summary = "订单删除")
    public CommonResult<Boolean> orderDel(@Validated @RequestBody AppDoOrderParam param) {
        Long uid = getLoginUserId();
        appStoreOrderService.removeOrder(param.getUni(), uid);
        return success(true);
    }




    /**
     * 订单取消   未支付的订单回退积分,回退优惠券,回退库存
     */
    @PreAuthenticated
    @PostMapping("/cancel")
    @Operation(summary = "订单取消")
    public CommonResult<Boolean> cancelOrder(@Validated @RequestBody AppHandleOrderParam param) {
        Long uid = getLoginUserId();
        appStoreOrderService.cancelOrder(param.getId(), uid);
        return success(true);
    }

    /**
     * 个人中心订单统计
     */
    @PreAuthenticated
    @PostMapping("/user_count")
    @Operation(summary = "个人中心订单统计")
    public CommonResult<AppUserOrderCountVo> countOrder() {
        Long uid = getLoginUserId();
        AppUserOrderCountVo appUserOrderCountVo = asyncOrderRedisDAO.get(uid);
        return success(appUserOrderCountVo);
    }







}

