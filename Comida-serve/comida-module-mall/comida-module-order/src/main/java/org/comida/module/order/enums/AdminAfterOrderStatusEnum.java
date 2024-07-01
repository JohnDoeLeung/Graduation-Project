package org.comida.module.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
   * 后台订单相关枚举
 */
@Getter
@AllArgsConstructor
public enum AdminAfterOrderStatusEnum {

	STATUS_1(1,"售后中"),
	STATUS_2(2,"已完成");




	private Integer value;
	private String desc;

	public static AdminAfterOrderStatusEnum toType(int value) {
		return Stream.of(AdminAfterOrderStatusEnum.values())
				.filter(p -> p.value == value)
				.findAny()
				.orElse(null);
	}


}
