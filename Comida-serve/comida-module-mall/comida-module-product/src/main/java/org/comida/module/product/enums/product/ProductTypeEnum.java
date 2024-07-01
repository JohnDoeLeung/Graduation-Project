package org.comida.module.product.enums.product;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
   * 产品类型枚举
 */

@Getter
@AllArgsConstructor
public enum ProductTypeEnum {

    PINK("pink","拼团"),

    SECKILL("seckill","秒杀"),

    COMBINATION("combination","拼团产品");

    private String value;
    private String desc;
}
