package org.comida.module.order.service.storeorder.dto;

import lombok.Data;

import java.io.Serializable;

/**
* @author hupeng
* @date 2020-05-12
*/
@Data
public class YxStoreOrderCartInfoDto implements Serializable {

    private Integer id;

    /** 订单id */
    private Integer oid;

    /**
     * 订单号
     */
    private String orderId;

    /** 商品ID */
    private Integer productId;

    /** 购买东西的详细信息 */
    private String cartInfo;

    /** 唯一id */
    private String unique;
}
