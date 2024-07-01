package org.comida.module.coupon.service.couponuser;

import org.comida.framework.common.enums.ShopCommonEnum;
import org.comida.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.comida.module.coupon.controller.app.coupon.vo.AppMyCouponVO;
import org.comida.module.coupon.convert.couponuser.CouponUserConvert;
import org.comida.module.coupon.dal.dataobject.couponuser.CouponUserDO;
import org.comida.module.coupon.dal.mysql.couponuser.CouponUserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户领的优惠券 Service 实现类
 *
  */
@Service
@Validated
public class AppCouponUserServiceImpl extends ServiceImpl<CouponUserMapper, CouponUserDO> implements AppCouponUserService {

    @Resource
    private CouponUserMapper userMapper;



    /**
     * 获取我的优惠券列表
     * @param shopId 店铺id
     * @param page
     * @param pagesize
     * @return
     */
    @Override
    public List<AppMyCouponVO> getList(Long uid, Long shopId, int page, int pagesize) {
        LocalDateTime nowTime = LocalDateTime.now();
        Page<CouponUserDO> pageModel = new Page<>(page, pagesize);
        LambdaQueryWrapperX<CouponUserDO> wrapper = new LambdaQueryWrapperX<>();
        wrapper.eqIfPresent(CouponUserDO::getShopId, shopId)
                .lt(CouponUserDO::getStartTime,nowTime)
                .gt(CouponUserDO::getEndTime,nowTime)
                .eq(CouponUserDO::getUserId,uid)
                .eq(CouponUserDO::getStatus,ShopCommonEnum.IS_STATUS_0);
        IPage<CouponUserDO> pageList = this.baseMapper.selectPage(pageModel, wrapper);
        return CouponUserConvert.INSTANCE.convertList03(pageList.getRecords());
    }


}
