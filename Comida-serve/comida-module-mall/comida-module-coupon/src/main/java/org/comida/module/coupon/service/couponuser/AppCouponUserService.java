package org.comida.module.coupon.service.couponuser;

import org.comida.module.coupon.controller.app.coupon.vo.AppMyCouponVO;
import org.comida.module.coupon.dal.dataobject.couponuser.CouponUserDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户领的优惠券 Service 接口
 *
  */
public interface AppCouponUserService extends IService<CouponUserDO> {

    /**
     * 获取我的优惠券列表
     * @param shopId 店铺id
     * @param page
     * @param pagesize
     * @return
     */
    List<AppMyCouponVO> getList(Long uid, Long shopId, int page, int pagesize);



}
