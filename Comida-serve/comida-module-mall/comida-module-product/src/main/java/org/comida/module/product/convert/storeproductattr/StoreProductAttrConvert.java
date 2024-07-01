package org.comida.module.product.convert.storeproductattr;

import org.comida.module.product.controller.admin.storeproduct.vo.StoreProductRespVO;
import org.comida.module.product.controller.app.product.vo.AppStoreProductAttrQueryVo;
import org.comida.module.product.dal.dataobject.storeproduct.StoreProductDO;
import org.comida.module.product.dal.dataobject.storeproductattr.StoreProductAttrDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商品属性 Convert
 *
  */
@Mapper
public interface StoreProductAttrConvert {

    StoreProductAttrConvert INSTANCE = Mappers.getMapper(StoreProductAttrConvert.class);

    AppStoreProductAttrQueryVo convert(StoreProductAttrDO bean);
    

}
