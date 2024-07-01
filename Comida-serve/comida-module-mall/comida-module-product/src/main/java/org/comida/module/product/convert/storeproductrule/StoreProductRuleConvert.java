package org.comida.module.product.convert.storeproductrule;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.comida.module.product.controller.admin.storeproductrule.vo.*;
import org.comida.module.product.dal.dataobject.storeproductrule.StoreProductRuleDO;

/**
 * 商品规则值(规格) Convert
 *
  */
@Mapper
public interface StoreProductRuleConvert {

    StoreProductRuleConvert INSTANCE = Mappers.getMapper(StoreProductRuleConvert.class);

    StoreProductRuleDO convert(StoreProductRuleCreateReqVO bean);

    StoreProductRuleDO convert(StoreProductRuleUpdateReqVO bean);

    StoreProductRuleRespVO convert(StoreProductRuleDO bean);

    List<StoreProductRuleRespVO> convertList(List<StoreProductRuleDO> list);

    PageResult<StoreProductRuleRespVO> convertPage(PageResult<StoreProductRuleDO> page);


}
