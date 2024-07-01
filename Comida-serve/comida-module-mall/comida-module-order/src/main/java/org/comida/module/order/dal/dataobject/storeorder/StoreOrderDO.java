package org.comida.module.order.dal.dataobject.storeorder;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.comida.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单 DO
 *
  */
@TableName("comida_store_order")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreOrderDO extends BaseDO {

    /**
     * 订单ID
     */
    @TableId
    private Long id;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 用户姓名
     */
    private String realName;
    /**
     * 用户电话
     */
    private String userPhone;
    /**
     * 详细地址
     */
    private String userAddress;

    /**
     * 订单商品总数
     */
    private Integer totalNum;
    /**
     * 订单总价
     */
    private BigDecimal totalPrice;
    /**
     * 邮费
     */
    private BigDecimal totalPostage;
    /**
     * 实际支付金额
     */
    private BigDecimal payPrice;
    /**
     * 支付邮费
     */
    private BigDecimal payPostage;

    /**
     * 优惠券id
     */
    private Integer couponId;
    /**
     * 优惠券金额
     */
    private BigDecimal couponPrice;
    /**
     * 支付状态
     */
    private Integer paid;
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    /**
     * 支付方式
     */
    private String payType;

    /**
     * 订单类型 购买类型:takein=自取,takeout=外卖
     */
    private String orderType;
    /**
     * 订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：已完成；-1：已退款）
     */
    private Integer status;
    /**
     * 0 未退款 1 申请中 2 已退款
     */
    private Integer refundStatus;

    /**
     * 退款用户说明
     */
    private String refundReasonWapExplain;
    /**
     * 退款时间
     */
    private LocalDateTime refundReasonTime;
    /**
     * 前台退款原因
     */
    private String refundReasonWap;
    /**
     * 不退款的理由
     */
    private String refundReason;
    /**
     * 退款金额
     */
    private BigDecimal refundPrice;

    /**
     * 消费赚取积分
     */
    private BigDecimal gainIntegral;

    /**
     * 备注
     */
    private String mark;
    /**
     * 管理员备注
     */
    private String remark;


    /**
     * 支付渠道(0微信公众号1微信小程序)
     */
    private Integer isChannel;
    /**
     * 系统删除
     */
    private Integer isSystemDel;

    /**
     * 门店ID
     */
    private Long shopId;

    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 取餐时间
     */
    private LocalDateTime getTime;

    /**
     * 取餐👌
     */
    private Long numberId;

}
