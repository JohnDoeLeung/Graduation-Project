package org.comida.module.coupon.service.coupon;

import org.comida.module.coupon.controller.app.coupon.vo.AppCouponVO;
import org.comida.module.coupon.dal.dataobject.coupon.CouponDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 优惠券 Service 接口
 *
  */
public interface AppCouponService extends IService<CouponDO> {

    /**
     * 获取未被领取优惠券
     * @param shopId 店铺id
     * @param page
     * @param pagesize
     * @return
     */
    List<AppCouponVO> getNotList(Long uid, Long shopId, int page, int pagesize);

    /**
     * 领取优惠券
     * @param uid 用户ID
     * @param id  优惠券ID
     * @param code 兑换码
     */
    void receive(Long uid,Long id,String code);
}
