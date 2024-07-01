package org.comida.framework.common.constant;
/**
 * 订单统一常量
 */
public interface ShopConstants {

	/**
	 * 订单自动取消时间（分钟）
	 */
	long ORDER_OUTTIME_UNPAY = 30;

	/**
	 * redis订单未付款key
	 */

	String REDIS_ORDER_OUTTIME_UNPAY_QUEUE = "order-unpay-cancel-queue";
	/**
	 * redis订单收货key
	 */

	String REDIS_ORDER_OUTTIME_UNCONFIRM = "order:unconfirm:";

	long COMIDA_ORDER_CACHE_TIME = 3600L;
}
