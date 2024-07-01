package org.comida.module.order.controller.admin.storeorder.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.comida.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static org.comida.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 订单 Excel 导出 Request VO，参数和 StoreOrderPageReqVO 是一致的")
@Data
public class StoreOrderExportReqVO {

    @Schema(description = "订单号", example = "20527")
    private String orderId;

    @Schema(description = "用户id", example = "8323")
    private Long uid;

    @Schema(description = "用户姓名", example = "张三")
    private String realName;

    @Schema(description = "用户电话")
    private String userPhone;

    @Schema(description = "详细地址")
    private String userAddress;

    @Schema(description = "订单商品总数")
    private Integer totalNum;

    @Schema(description = "订单总价", example = "31659")
    private BigDecimal totalPrice;

    @Schema(description = "邮费")
    private BigDecimal totalPostage;

    @Schema(description = "实际支付金额", example = "19682")
    private BigDecimal payPrice;

    @Schema(description = "支付邮费")
    private BigDecimal payPostage;

    @Schema(description = "抵扣金额", example = "16463")
    private BigDecimal deductionPrice;

    @Schema(description = "优惠券id", example = "3299")
    private Integer couponId;

    @Schema(description = "优惠券金额", example = "22157")
    private BigDecimal couponPrice;

    @Schema(description = "支付状态", example = "11728")
    private Integer paid;

    @Schema(description = "支付时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] payTime;

    @Schema(description = "支付方式", example = "2")
    private String payType;

    @Schema(description = "订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：已完成；-1：已退款）", example = "1")
    private Integer status;

    @Schema(description = "0 未退款 1 申请中 2 已退款", example = "2")
    private Integer refundStatus;

    @Schema(description = "退款用户说明")
    private String refundReasonWapExplain;

    @Schema(description = "退款时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] refundReasonTime;

    @Schema(description = "前台退款原因")
    private String refundReasonWap;

    @Schema(description = "不退款的理由", example = "不喜欢")
    private String refundReason;

    @Schema(description = "退款金额", example = "7547")
    private BigDecimal refundPrice;


    @Schema(description = "消费赚取积分")
    private BigDecimal gainIntegral;

    @Schema(description = "备注")
    private String mark;

    @Schema(description = "管理员备注", example = "随便")
    private String remark;

    @Schema(description = "商户ID", example = "8499")
    private Integer merId;

    @Schema(description = "支付渠道(0微信公众号1微信小程序)")
    private Integer isChannel;

    @Schema(description = "系统删除")
    private Integer isSystemDel;

    @Schema(description = "添加时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
