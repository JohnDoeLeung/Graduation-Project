package org.comida.module.product.api.property;

import org.comida.module.product.api.property.dto.ProductPropertyValueDetailRespDTO;

import java.util.Collection;
import java.util.List;

/**
 * 商品属性值 API 接口
 *
  */
public interface ProductPropertyValueApi {

    /**
     * 根据编号数组，获得属性值列表
     *
     * @param ids 编号数组
     * @return 属性值明细列表
     */
    List<ProductPropertyValueDetailRespDTO> getPropertyValueDetailList(Collection<Long> ids);

}
