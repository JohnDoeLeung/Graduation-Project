package org.comida.module.product.convert.storeproduct;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;

import org.comida.module.product.controller.app.product.vo.AppStoreProductRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.comida.module.product.controller.admin.storeproduct.vo.*;
import org.comida.module.product.dal.dataobject.storeproduct.StoreProductDO;

/**
 * 商品 Convert
 *
  */
@Mapper
public interface StoreProductConvert {

    StoreProductConvert INSTANCE = Mappers.getMapper(StoreProductConvert.class);

    StoreProductDO convert(StoreProductCreateReqVO bean);

    StoreProductDO convert(StoreProductUpdateReqVO bean);

    StoreProductRespVO convert(StoreProductDO bean);

    AppStoreProductRespVo convert01(StoreProductDO bean);

    List<StoreProductRespVO> convertList(List<StoreProductDO> list);

    PageResult<StoreProductRespVO> convertPage(PageResult<StoreProductDO> page);


    List<AppStoreProductRespVo> convertList03(List<StoreProductDO> list);
}
