package org.comida.module.order.service.storeordercartinfo;

import org.comida.module.order.dal.dataobject.storeordercartinfo.StoreOrderCartInfoDO;
import org.comida.module.product.controller.app.cart.vo.AppStoreCartQueryVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 订单购物详情 Service 接口
 *
  */
public interface StoreOrderCartInfoService extends IService<StoreOrderCartInfoDO> {

    /**
     * 添加购物车商品信息
     * @param oid 订单id
     * @param orderId 订单号
     * @param productIds 商品id
     * @param numbers 商品数量
     * @param specs 商品规格
     */
    void saveCartInfo(Long oid, String orderId,List<String> productIds,List<String> numbers,List<String> specs);


}
