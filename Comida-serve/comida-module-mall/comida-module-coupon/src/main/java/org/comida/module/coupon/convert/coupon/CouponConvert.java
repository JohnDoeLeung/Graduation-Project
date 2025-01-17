package org.comida.module.coupon.convert.coupon;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;

import org.comida.module.coupon.controller.app.coupon.vo.AppCouponVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.comida.module.coupon.controller.admin.coupon.vo.*;
import org.comida.module.coupon.dal.dataobject.coupon.CouponDO;

/**
 * 优惠券 Convert
 *
  */
@Mapper
public interface CouponConvert {

    CouponConvert INSTANCE = Mappers.getMapper(CouponConvert.class);

    CouponDO convert(CouponCreateReqVO bean);

    CouponDO convert(CouponUpdateReqVO bean);

    CouponRespVO convert(CouponDO bean);

    List<CouponRespVO> convertList(List<CouponDO> list);

    List<AppCouponVO> convertList03(List<CouponDO> list);

    PageResult<CouponRespVO> convertPage(PageResult<CouponDO> page);

}
