package org.comida.module.product.service.storeproduct.dto;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author hupeng
* @date 2020-06-28
*/
@Data
public class YxStoreProductRuleDto implements Serializable {

    private Integer id;

    /** 规格名称 */
    private String ruleName;

    /** 规格值 */
    private JSONArray ruleValue;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Integer isDel;
}
