package org.comida.module.coupon.dal.mysql.couponuser;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;
import org.comida.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.comida.framework.mybatis.core.mapper.BaseMapperX;
import org.comida.module.coupon.dal.dataobject.couponuser.CouponUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.comida.module.coupon.controller.admin.couponuser.vo.*;

/**
 * 用户领的优惠券 Mapper
 *
  */
@Mapper
public interface CouponUserMapper extends BaseMapperX<CouponUserDO> {

    default PageResult<CouponUserDO> selectPage(CouponUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CouponUserDO>()
                .eqIfPresent(CouponUserDO::getShopId, reqVO.getShopId())
                .likeIfPresent(CouponUserDO::getShopName, reqVO.getShopName())
                .eqIfPresent(CouponUserDO::getTitle, reqVO.getTitle())
                .eqIfPresent(CouponUserDO::getLeast, reqVO.getLeast())
                .eqIfPresent(CouponUserDO::getValue, reqVO.getValue())
                .eqIfPresent(CouponUserDO::getType, reqVO.getType())
                .eqIfPresent(CouponUserDO::getImage, reqVO.getImage())
                .eqIfPresent(CouponUserDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CouponUserDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CouponUserDO::getCouponId, reqVO.getCouponId())
                .eqIfPresent(CouponUserDO::getExchangeCode, reqVO.getExchangeCode())
                .orderByDesc(CouponUserDO::getId));
    }

    default List<CouponUserDO> selectList(CouponUserExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CouponUserDO>()
                .eqIfPresent(CouponUserDO::getShopId, reqVO.getShopId())
                .likeIfPresent(CouponUserDO::getShopName, reqVO.getShopName())
                .eqIfPresent(CouponUserDO::getTitle, reqVO.getTitle())
                .eqIfPresent(CouponUserDO::getLeast, reqVO.getLeast())
                .eqIfPresent(CouponUserDO::getValue, reqVO.getValue())
                .eqIfPresent(CouponUserDO::getType, reqVO.getType())
                .eqIfPresent(CouponUserDO::getImage, reqVO.getImage())
                .eqIfPresent(CouponUserDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CouponUserDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CouponUserDO::getCouponId, reqVO.getCouponId())
                .eqIfPresent(CouponUserDO::getExchangeCode, reqVO.getExchangeCode())
                .orderByDesc(CouponUserDO::getId));
    }

}
