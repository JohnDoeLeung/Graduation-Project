package org.comida.module.product.dal.dataobject.storeproductattrresult;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.comida.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商品属性详情 DO
 *
  */
@TableName("comida_store_product_attr_result")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductAttrResultDO  {

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
     * 商品属性参数
     */
    private String result;
    /**
     * 上次修改时间
     */
    private Date changeTime;

}
