package org.comida.module.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
   * 应用来源相关枚举
 */
@Getter
@AllArgsConstructor
public enum AppFromEnum {

	WEIXIN_H5("weixinh5","weixinh5"),
	H5("h5","H5"),
	WECHAT("wechat","公众号"),
	APP("app","APP"),
	PC("pc","PC"),
	ROUNTINE("routine","小程序"),
	UNIAPPH5("uniappH5","uniappH5");


	private String value;
	private String desc;


}
