package org.comida.module.order.controller.admin.storeorder.vo;

import org.comida.framework.desensitize.core.slider.annotation.MobileDesensitize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.comida.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 订单 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class StoreOrderBaseVO {

    @Schema(description = "订单号", required = true, example = "20527")
    @NotNull(message = "订单号不能为空")
    private String orderId;

    @Schema(description = "用户id", required = true, example = "8323")
    @NotNull(message = "用户id不能为空")
    private Long uid;

    @Schema(description = "用户姓名", required = true, example = "张三")
    //@NotNull(message = "用户姓名不能为空")
    private String realName;

    @MobileDesensitize
    @Schema(description = "用户电话", required = true)
    //@NotNull(message = "用户电话不能为空")
    private String userPhone;

    @Schema(description = "详细地址", required = true)
   // @NotNull(message = "详细地址不能为空")
    private String userAddress;

    @Schema(description = "订单商品总数", required = true)
    //@NotNull(message = "订单商品总数不能为空")
    private Integer totalNum;

    @Schema(description = "订单总价", required = true, example = "31659")
    //@NotNull(message = "订单总价不能为空")
    private BigDecimal totalPrice;

    @Schema(description = "邮费", required = true)
   // @NotNull(message = "邮费不能为空")
    private BigDecimal totalPostage;

    @Schema(description = "实际支付金额", required = true, example = "19682")
   // @NotNull(message = "实际支付金额不能为空")
    private BigDecimal payPrice;

    @Schema(description = "支付邮费", required = true)
    //@NotNull(message = "支付邮费不能为空")
    private BigDecimal payPostage;

    @Schema(description = "优惠券id", required = true, example = "3299")
    //@NotNull(message = "优惠券id不能为空")
    private Integer couponId;

    @Schema(description = "优惠券金额", required = true, example = "22157")
    //@NotNull(message = "优惠券金额不能为空")
    private BigDecimal couponPrice;

    @Schema(description = "支付状态", required = true, example = "11728")
    //@NotNull(message = "支付状态不能为空")
    private Integer paid;

    @Schema(description = "支付时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime payTime;

    @Schema(description = "支付方式", required = true, example = "2")
    //@NotNull(message = "支付方式不能为空")
    private String payType;

    @Schema(description = "订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：已完成；-1：已退款）", required = true, example = "1")
    //@NotNull(message = "订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：已完成；-1：已退款）不能为空")
    private Integer status;

    @Schema(description = "0 未退款 1 申请中 2 已退款", required = true, example = "2")
    //@NotNull(message = "0 未退款 1 申请中 2 已退款不能为空")
    private Integer refundStatus;

    @Schema(description = "退款用户说明")
    private String refundReasonWapExplain;

    @Schema(description = "退款时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime refundReasonTime;

    @Schema(description = "前台退款原因")
    private String refundReasonWap;

    @Schema(description = "不退款的理由", example = "不喜欢")
    private String refundReason;

    @Schema(description = "退款金额", required = true, example = "7547")
    //@NotNull(message = "退款金额不能为空")
    private BigDecimal refundPrice;

    @Schema(description = "消费赚取积分", required = true)
    //@NotNull(message = "消费赚取积分不能为空")
    private BigDecimal gainIntegral;

    @Schema(description = "备注", required = true)
    //@NotNull(message = "备注不能为空")
    private String mark;

    @Schema(description = "管理员备注", example = "随便")
    private String remark;

    @Schema(description = "支付渠道(0微信公众号1微信小程序)")
    private Integer isChannel;

    @Schema(description = "系统删除")
    private Integer isSystemDel;

    @Schema(description = "订单类型")
    private String orderType;

    /**
     * 取餐👌
     */
    private Long numberId;

    /**
     * 门店ID
     */
    private Long shopId;

    /**
     * 门店名称
     */
    private String shopName;

    private LocalDateTime getTime;

}
