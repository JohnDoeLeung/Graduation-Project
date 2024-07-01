package org.comida.module.coupon.service.coupon;

import cn.hutool.core.bean.BeanUtil;
import org.comida.framework.mybatis.core.query.LambdaQueryWrapperX;

import org.comida.module.coupon.controller.app.coupon.vo.AppCouponVO;
import org.comida.module.coupon.convert.coupon.CouponConvert;
import org.comida.module.coupon.dal.dataobject.coupon.CouponDO;
import org.comida.module.coupon.dal.dataobject.couponuser.CouponUserDO;
import org.comida.module.coupon.dal.mysql.coupon.CouponMapper;
import org.comida.module.coupon.service.couponuser.AppCouponUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.comida.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.comida.module.coupon.enums.ErrorCodeConstants.*;

/**
 * 优惠券 Service 实现类
  */
@Service
@Validated
public class AppCouponServiceImpl extends ServiceImpl<CouponMapper, CouponDO> implements AppCouponService {

    @Resource
    private CouponMapper couponMapper;
    @Resource
    private AppCouponUserService appCouponUserService;

    /**
     * 获取未被领取优惠券
     * @param shopId 店铺id
     * @param page
     * @param pagesize
     * @return
     */
    @Override
    public List<AppCouponVO> getNotList(Long uid, Long shopId, int page, int pagesize) {

        List<CouponUserDO> couponUserDOS = appCouponUserService.list(new LambdaQueryWrapper<CouponUserDO>()
                .eq(CouponUserDO::getUserId,uid));

        LocalDateTime nowTime = LocalDateTime.now();
        Page<CouponDO> pageModel = new Page<>(page, pagesize);
        LambdaQueryWrapperX<CouponDO> wrapper = new LambdaQueryWrapperX<>();
        wrapper.eqIfPresent(CouponDO::getShopId, shopId)
                .gt(CouponDO::getEndTime,nowTime);
        if(couponUserDOS != null && !couponUserDOS.isEmpty()) {
            List<Integer> couponIds = couponUserDOS.stream().map(CouponUserDO::getCouponId).collect(Collectors.toList());
            wrapper.notIn(CouponDO::getId,couponIds);
        }
        IPage<CouponDO> pageList = this.baseMapper.selectPage(pageModel, wrapper);
        return CouponConvert.INSTANCE.convertList03(pageList.getRecords());
    }

    /**
     * 领取优惠券
     * @param uid 用户ID
     * @param id  优惠券ID
     * @param code 兑换码
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void receive(Long uid, Long id, String code) {
        CouponDO couponDO = null;
        if(id != null){
            couponDO = this.baseMapper.selectById(id);
        }else {
            couponDO = couponMapper.selectOne(CouponDO::getExchangeCode,code);
        }
        if(couponDO == null){
            throw exception(COUPON_NOT_EXISTS);
        }
        if(couponDO.getReceive() >= couponDO.getDistribute()){
            throw exception(COUPON_RECEIVE_ZERO);
        }
        Long couponCount = appCouponUserService.count(new LambdaQueryWrapper<CouponUserDO>()
                .eq(CouponUserDO::getUserId,uid).eq(CouponUserDO::getCouponId,couponDO.getId()));
        if(couponCount > 0){
            throw exception(COUPON_RECEIVED);
        }

        CouponUserDO couponUserDO = BeanUtil.copyProperties(couponDO,CouponUserDO.class,"id");

        couponUserDO.setUserId(uid.intValue());
        couponUserDO.setCouponId(couponDO.getId().intValue());
        couponUserDO.setEndTime(couponDO.getEndTime());
        couponUserDO.setExchangeCode(code);
        appCouponUserService.save(couponUserDO);

        int newRecive = couponDO.getReceive() + 1;
        couponDO.setReceive(newRecive);
        couponMapper.updateById(couponDO);
    }
}
