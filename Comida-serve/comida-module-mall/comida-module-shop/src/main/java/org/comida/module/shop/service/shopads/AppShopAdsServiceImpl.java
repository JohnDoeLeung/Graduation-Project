package org.comida.module.shop.service.shopads;

import org.comida.module.shop.dal.dataobject.shopads.ShopAdsDO;
import org.comida.module.shop.dal.mysql.shopads.ShopAdsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 商品 AppShopAds 实现类
 *
  */
@Service
@Validated
public class AppShopAdsServiceImpl extends ServiceImpl<ShopAdsMapper, ShopAdsDO> implements AppShopAdsService {




}
