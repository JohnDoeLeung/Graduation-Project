package org.comida.module.order.convert.storeorder;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;

import org.comida.module.order.controller.app.order.vo.AppStoreOrderQueryVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.comida.module.order.controller.admin.storeorder.vo.*;
import org.comida.module.order.dal.dataobject.storeorder.StoreOrderDO;

/**
 * 订单 Convert
 *
  */
@Mapper
public interface StoreOrderConvert {

    StoreOrderConvert INSTANCE = Mappers.getMapper(StoreOrderConvert.class);

    StoreOrderDO convert(StoreOrderCreateReqVO bean);

    StoreOrderDO convert(StoreOrderUpdateReqVO bean);

    StoreOrderRespVO convert(StoreOrderDO bean);

    AppStoreOrderQueryVo convert1(StoreOrderDO bean);

    List<StoreOrderRespVO> convertList(List<StoreOrderDO> list);

    List<AppStoreOrderQueryVo> convertList01(List<StoreOrderDO> list);

    PageResult<StoreOrderRespVO> convertPage(PageResult<StoreOrderDO> page);


}
