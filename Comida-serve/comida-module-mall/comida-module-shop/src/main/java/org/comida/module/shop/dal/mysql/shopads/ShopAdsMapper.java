package org.comida.module.shop.dal.mysql.shopads;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;
import org.comida.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.comida.framework.mybatis.core.mapper.BaseMapperX;
import org.comida.module.shop.dal.dataobject.shopads.ShopAdsDO;
import org.apache.ibatis.annotations.Mapper;
import org.comida.module.shop.controller.admin.shopads.vo.*;

/**
 * 广告图管理 Mapper
 *
  */
@Mapper
public interface ShopAdsMapper extends BaseMapperX<ShopAdsDO> {

    default PageResult<ShopAdsDO> selectPage(ShopAdsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ShopAdsDO>()
                .eqIfPresent(ShopAdsDO::getImage, reqVO.getImage())
                .eqIfPresent(ShopAdsDO::getIsSwitch, reqVO.getIsSwitch())
                .eqIfPresent(ShopAdsDO::getWeigh, reqVO.getWeigh())
                .likeIfPresent(ShopAdsDO::getShopName, reqVO.getShopName())
                .betweenIfPresent(ShopAdsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShopAdsDO::getId));
    }

    default List<ShopAdsDO> selectList(ShopAdsExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ShopAdsDO>()
                .eqIfPresent(ShopAdsDO::getImage, reqVO.getImage())
                .eqIfPresent(ShopAdsDO::getIsSwitch, reqVO.getIsSwitch())
                .eqIfPresent(ShopAdsDO::getWeigh, reqVO.getWeigh())
                .eqIfPresent(ShopAdsDO::getShopId, reqVO.getShopId())
                .betweenIfPresent(ShopAdsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShopAdsDO::getId));
    }

}
