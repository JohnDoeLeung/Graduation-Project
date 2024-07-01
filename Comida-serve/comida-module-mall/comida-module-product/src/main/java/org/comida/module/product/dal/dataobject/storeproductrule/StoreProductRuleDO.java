package org.comida.module.product.dal.dataobject.storeproductrule;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import org.comida.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商品规则值(规格) DO
 *
  */
@TableName(value = "comida_store_product_rule",autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductRuleDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 规格名称
     */
    private String ruleName;
    /**
     * 规格值
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONArray ruleValue;

}
