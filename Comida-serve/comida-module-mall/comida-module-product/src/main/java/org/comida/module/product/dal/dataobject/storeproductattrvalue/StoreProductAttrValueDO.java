package org.comida.module.product.dal.dataobject.storeproductattrvalue;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import org.comida.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商品属性值 DO
 */
@TableName("comida_store_product_attr_value")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductAttrValueDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 商品属性索引值 (attr_value|attr_value[|....])
     */
    private String sku;
    /**
     * 属性对应的库存
     */
    private Integer stock;
    /**
     * 销量
     */
    private Integer sales;
    /**
     * 属性金额
     */
    private BigDecimal price;
    /**
     * 图片
     */
    private String image;
    /**
     * 唯一值
     */
    @TableField(value = "`unique`")
    private String unique;

}
