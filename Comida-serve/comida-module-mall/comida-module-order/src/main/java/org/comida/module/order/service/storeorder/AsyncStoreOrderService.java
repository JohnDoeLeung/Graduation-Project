package org.comida.module.order.service.storeorder;

import org.comida.module.member.controller.app.user.vo.AppUserOrderCountVo;
import org.comida.module.order.controller.admin.storeorder.vo.ShoperOrderTimeDataVo;
import org.comida.module.order.controller.app.order.vo.AppStoreOrderQueryVo;
import org.comida.module.order.service.storeorder.dto.OrderTimeDataDto;

/**
 * 异步订单 Service 接口
 *
  */
public interface AsyncStoreOrderService {

    /**
     * 计算某个用户的订单统计数据
     * @param uid uid>0 取用户 否则取所有
     * @return UserOrderCountVo
     */
    void orderData(Long uid);



    /**
     * 异步后台数据统计
     */
    void getOrderTimeData();




}
