package org.comida.module.coupon.service.coupon;

import org.comida.framework.common.pojo.PageResult;
import org.comida.module.coupon.controller.admin.coupon.vo.CouponCreateReqVO;
import org.comida.module.coupon.controller.admin.coupon.vo.CouponExportReqVO;
import org.comida.module.coupon.controller.admin.coupon.vo.CouponPageReqVO;
import org.comida.module.coupon.controller.admin.coupon.vo.CouponUpdateReqVO;
import org.comida.module.coupon.convert.coupon.CouponConvert;
import org.comida.module.coupon.dal.dataobject.coupon.CouponDO;
import org.comida.module.coupon.dal.mysql.coupon.CouponMapper;
import org.comida.module.store.dal.dataobject.storeshop.StoreShopDO;
import org.comida.module.store.dal.mysql.storeshop.StoreShopMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static org.comida.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.comida.module.coupon.enums.ErrorCodeConstants.COUPON_NOT_EXISTS;

/**
 * 优惠券 Service 实现类
 *
  */
@Service
@Validated
public class CouponServiceImpl implements CouponService {

    @Resource
    private CouponMapper Mapper;
    @Resource
    private StoreShopMapper storeShopMapper;

    @Override
    public Long create(CouponCreateReqVO createReqVO) {
        // 插入
        CouponDO  couponDO = CouponConvert.INSTANCE.convert(createReqVO);
        StoreShopDO storeShopDO = storeShopMapper.selectById(createReqVO.getShopId());
        couponDO.setShopName(storeShopDO.getName());
        Mapper.insert(couponDO);
        // 返回
        return couponDO.getId();
    }

    @Override
    public void update(CouponUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        CouponDO updateObj = CouponConvert.INSTANCE.convert(updateReqVO);
        StoreShopDO storeShopDO = storeShopMapper.selectById(updateReqVO.getShopId());
        updateObj.setShopName(storeShopDO.getName());
        Mapper.updateById(updateObj);
    }

    @Override
    public void delete(Long id) {
        // 校验存在
        validateExists(id);
        // 删除
        Mapper.deleteById(id);
    }

    private void validateExists(Long id) {
        if (Mapper.selectById(id) == null) {
            throw exception(COUPON_NOT_EXISTS);
        }
    }

    @Override
    public CouponDO get(Long id) {
        return Mapper.selectById(id);
    }

    @Override
    public List<CouponDO> getList(Collection<Long> ids) {
        return Mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CouponDO> getPage(CouponPageReqVO pageReqVO) {
        return Mapper.selectPage(pageReqVO);
    }

    @Override
    public List<CouponDO> getList(CouponExportReqVO exportReqVO) {
        return Mapper.selectList(exportReqVO);
    }

}
