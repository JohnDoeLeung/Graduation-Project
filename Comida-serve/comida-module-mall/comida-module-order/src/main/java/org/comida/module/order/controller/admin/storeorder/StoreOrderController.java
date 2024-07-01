package org.comida.module.order.controller.admin.storeorder;

import org.comida.framework.common.pojo.CommonResult;
import org.comida.framework.common.pojo.PageResult;
import org.comida.module.order.controller.admin.storeorder.vo.*;
import org.comida.module.order.convert.storeorder.StoreOrderConvert;
import org.comida.module.order.dal.dataobject.storeorder.StoreOrderDO;
import org.comida.module.order.dal.dataobject.storeorderstatus.StoreOrderStatusDO;
import org.comida.module.order.dal.redis.order.AsyncCountRedisDAO;
import org.comida.module.order.service.storeorder.AsyncStoreOrderService;
import org.comida.module.order.service.storeorder.StoreOrderService;
import org.comida.module.order.service.storeorder.dto.OrderTimeDataDto;
import org.comida.module.order.service.storeorderstatus.StoreOrderStatusService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static org.comida.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 订单")
@RestController
@RequestMapping("/order/store-order")
@Validated
public class StoreOrderController {

    @Resource
    private StoreOrderService storeOrderService;
    @Resource
    private StoreOrderStatusService storeOrderStatusService;
    @Resource
    private AsyncCountRedisDAO asyncCountRedisDAO;
    @Resource
    private AsyncStoreOrderService asyncStoreOrderService;


    @PostMapping("/create")
    @Operation(summary = "创建订单")
    public CommonResult<Long> createStoreOrder(@Valid @RequestBody StoreOrderCreateReqVO createReqVO) {
        return success(storeOrderService.createStoreOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新订单")
    public CommonResult<Boolean> updateStoreOrder(@Valid @RequestBody StoreOrderUpdateReqVO updateReqVO) {
        storeOrderService.updateStoreOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除订单")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteStoreOrder(@RequestParam("id") Long id) {
        storeOrderService.deleteStoreOrder(id);
        return success(true);
    }

    @GetMapping("/pay")
    @Operation(summary = "订单线下支付")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> payStoreOrder(@RequestParam("id") Long id) {
        storeOrderService.payStoreOrder(id);
        return success(true);
    }

    @GetMapping("/take")
    @Operation(summary = "确认收货")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> takeStoreOrder(@RequestParam("id") Long id) {
        storeOrderService.takeStoreOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<StoreOrderRespVO> getStoreOrder(@RequestParam("id") Long id) {
        return success(storeOrderService.getStoreOrder(id));
    }

    @GetMapping("/list")
    @Operation(summary = "获得订单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<List<StoreOrderRespVO>> getStoreOrderList(@RequestParam("ids") Collection<Long> ids) {
        List<StoreOrderDO> list = storeOrderService.getStoreOrderList(ids);
        return success(StoreOrderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得订单分页")
    public CommonResult<PageResult<StoreOrderRespVO>> getStoreOrderPage(@Valid StoreOrderPageReqVO pageVO) {
        return success(storeOrderService.getStoreOrderPage(pageVO));
    }

    @GetMapping("/record-list")
    @Operation(summary = "获得订单记录列表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<List<StoreOrderStatusDO>> getStoreOrderRecordList(@RequestParam("id") Long id) {
        List<StoreOrderStatusDO> list = storeOrderStatusService.list(new LambdaQueryWrapper<StoreOrderStatusDO>()
                .eq(StoreOrderStatusDO::getOid,id));
        return success(list);
    }

    @GetMapping("/count")
    @Operation(summary = "获得订单统计")
    public CommonResult<OrderTimeDataDto> getStoreOrderCount() {
        asyncStoreOrderService.getOrderTimeData();
        return success(asyncCountRedisDAO.get());
    }


    @Operation(summary = "退款")
    @PostMapping(value = "/refund")
    public CommonResult<Boolean> refund(@Validated @RequestBody StoreOrderRefundVO updateReqVO) {
        storeOrderService.orderRefund(updateReqVO.getId(),updateReqVO.getPayPrice(), 0, null);
        return success(true);
    }


}
