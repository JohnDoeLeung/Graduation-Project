package org.comida.module.store.service.storeshop;

import org.comida.framework.security.core.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import org.comida.module.store.controller.admin.storeshop.vo.*;
import org.comida.module.store.dal.dataobject.storeshop.StoreShopDO;
import org.comida.framework.common.pojo.PageResult;

import org.comida.module.store.convert.storeshop.StoreShopConvert;
import org.comida.module.store.dal.mysql.storeshop.StoreShopMapper;

import static org.comida.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.comida.module.store.enums.ErrorCodeConstants.*;

/**
 * 门店管理 Service 实现类
 *
  */
@Service
@Validated
public class StoreShopServiceImpl implements StoreShopService {

    @Resource
    private StoreShopMapper shopMapper;

    @Override
    public Long createShop(StoreShopCreateReqVO createReqVO) {
        // 插入
        StoreShopDO shop = StoreShopConvert.INSTANCE.convert(createReqVO);
        shopMapper.insert(shop);
        // 返回
        return shop.getId();
    }

    @Override
    public void updateShop(StoreShopUpdateReqVO updateReqVO) {
        // 校验存在
        validateShopExists(updateReqVO.getId());
        // 更新
        StoreShopDO updateObj = StoreShopConvert.INSTANCE.convert(updateReqVO);
        shopMapper.updateById(updateObj);
    }

    @Override
    public void deleteShop(Long id) {
        // 校验存在
        validateShopExists(id);
        // 删除
        shopMapper.deleteById(id);
    }

    private void validateShopExists(Long id) {
        if (shopMapper.selectById(id) == null) {
            throw exception(SHOP_NOT_EXISTS);
        }
    }

    @Override
    public StoreShopDO getShop(Long id) {
        return shopMapper.selectById(id);
    }

    @Override
    public List<StoreShopDO> getShopList() {
        Long shopId = SecurityFrameworkUtils.getLoginUser().getShopId();
        LambdaQueryWrapper<StoreShopDO> wrapper = new LambdaQueryWrapper<>();
        if(shopId == 0) {
            wrapper.ge(StoreShopDO::getId,shopId);
        }else {
            wrapper.eq(StoreShopDO::getId,shopId);
        }
        return shopMapper.selectList(wrapper);
    }

    @Override
    public PageResult<StoreShopDO> getShopPage(StoreShopPageReqVO pageReqVO) {
        return shopMapper.selectPage(pageReqVO);
    }

    @Override
    public List<StoreShopDO> getShopList(StoreShopExportReqVO exportReqVO) {
        return shopMapper.selectList(exportReqVO);
    }

}
