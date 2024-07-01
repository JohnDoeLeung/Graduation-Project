package org.comida.module.store.convert.storeshop;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;

import org.comida.module.store.controller.app.storeshop.vo.AppStoreShopVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.comida.module.store.controller.admin.storeshop.vo.*;
import org.comida.module.store.dal.dataobject.storeshop.StoreShopDO;

/**
 * 门店管理 Convert
 *
  */
@Mapper
public interface StoreShopConvert {

    StoreShopConvert INSTANCE = Mappers.getMapper(StoreShopConvert.class);

    StoreShopDO convert(StoreShopCreateReqVO bean);

    StoreShopDO convert(StoreShopUpdateReqVO bean);

    StoreShopRespVO convert(StoreShopDO bean);

    AppStoreShopVO convert02(StoreShopDO bean);

    List<StoreShopRespVO> convertList(List<StoreShopDO> list);

    PageResult<StoreShopRespVO> convertPage(PageResult<StoreShopDO> page);


}
