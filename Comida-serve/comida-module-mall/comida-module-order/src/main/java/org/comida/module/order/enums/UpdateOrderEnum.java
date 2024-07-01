package org.comida.module.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
   * 应用来源相关枚举
 */
@Getter
@AllArgsConstructor
public enum UpdateOrderEnum {

	UPDATE_ORDER("updateOrder","修改订单"),
	ORDER_SEND("orderSend","订单发货"),
	RMARK("remark","备注"),
	SEND_INFO("sendInfo","配送信息");


	private String value;
	private String desc;


}
