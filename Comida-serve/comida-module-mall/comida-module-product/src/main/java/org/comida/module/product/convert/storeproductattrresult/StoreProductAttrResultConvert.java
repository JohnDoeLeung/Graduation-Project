package org.comida.module.product.convert.storeproductattrresult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商品属性详情 Convert
 *
  */
@Mapper
public interface StoreProductAttrResultConvert {

    StoreProductAttrResultConvert INSTANCE = Mappers.getMapper(StoreProductAttrResultConvert.class);

}
