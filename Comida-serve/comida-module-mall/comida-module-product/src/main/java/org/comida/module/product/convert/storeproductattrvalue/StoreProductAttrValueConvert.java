package org.comida.module.product.convert.storeproductattrvalue;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商品属性值 Convert
 *
  */
@Mapper
public interface StoreProductAttrValueConvert {

    StoreProductAttrValueConvert INSTANCE = Mappers.getMapper(StoreProductAttrValueConvert.class);
    

}
