package org.comida.module.product.dal.dataobject.storeproduct;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import org.comida.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商品 DO
 *
  */
@TableName("comida_store_product")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductDO extends BaseDO {

    /**
     * 商品id
     */
    @TableId
    private Long id;

    /**
     * 店铺id
     */
    private Integer shopId;

    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 商品图片
     */
    private String image;
    /**
     * 商品名称
     */
    private String storeName;
    /**
     * 商品简介
     */
    private String storeInfo;

    /**
     * 分类id
     */
    private String cateId;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 销量
     */
    private Integer sales;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 状态（0：未上架，1：上架）
     */
    private Integer isShow;

    /**
     * 获得积分
     */
    private BigDecimal giveIntegral;

    /**
     * 规格 0单 1多
     */
    private Integer specType;

}
