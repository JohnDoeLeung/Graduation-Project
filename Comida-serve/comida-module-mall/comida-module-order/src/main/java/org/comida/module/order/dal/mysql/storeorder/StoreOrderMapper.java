package org.comida.module.order.dal.mysql.storeorder;

import java.util.*;

import cn.hutool.core.util.StrUtil;
import org.comida.framework.common.enums.OrderInfoEnum;
import org.comida.framework.common.enums.ShopCommonEnum;
import org.comida.framework.common.pojo.PageResult;
import org.comida.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.comida.framework.mybatis.core.mapper.BaseMapperX;
import org.comida.framework.security.core.util.SecurityFrameworkUtils;
import org.comida.module.order.dal.dataobject.storeorder.StoreOrderDO;
import org.comida.module.order.enums.AdminOrderStatusEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.comida.module.order.controller.admin.storeorder.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 订单 Mapper
 *
  */
@Mapper
public interface StoreOrderMapper extends BaseMapperX<StoreOrderDO> {

    default PageResult<StoreOrderDO> selectPage(StoreOrderPageReqVO reqVO) {

        LambdaQueryWrapperX<StoreOrderDO> wrapper = new LambdaQueryWrapperX();
        Long shopId = SecurityFrameworkUtils.getLoginUser().getShopId();
        if(shopId > 0) {
            wrapper.eq(StoreOrderDO::getShopId,shopId);
        }

        wrapper.eqIfPresent(StoreOrderDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(StoreOrderDO::getNumberId, reqVO.getNumberId())
                .likeIfPresent(StoreOrderDO::getRealName, reqVO.getRealName())
                .eqIfPresent(StoreOrderDO::getUserPhone, reqVO.getUserPhone())
                .betweenIfPresent(StoreOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StoreOrderDO::getId);
        if (reqVO.getOrderStatus() != null) {
            switch (AdminOrderStatusEnum.toType(reqVO.getOrderStatus())) {
                //未支付
                case STATUS_0:
                    wrapper.eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_0.getValue())
                            .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                            .eq(StoreOrderDO::getStatus, OrderInfoEnum.STATUS_0.getValue());
                    break;
                //待发货
                case STATUS_1:
                    wrapper.eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                            .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                            .eq(StoreOrderDO::getStatus, OrderInfoEnum.STATUS_0.getValue());
                    break;
                //待收货
                case STATUS_2:
                    wrapper.eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                            .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                            .eq(StoreOrderDO::getStatus, OrderInfoEnum.STATUS_1.getValue());
                    break;
                //待评价
                case STATUS_3:
                    wrapper.eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                            .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                            .eq(StoreOrderDO::getStatus, OrderInfoEnum.STATUS_2.getValue());
                    break;
                //已完成
                case STATUS_4:
                    wrapper.eq(StoreOrderDO::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                            .eq(StoreOrderDO::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                            .eq(StoreOrderDO::getStatus, OrderInfoEnum.STATUS_3.getValue());
                    break;
                //退款单
                case STATUS_5:
                    String[] strs = {"1", "2"};
                    wrapper.in(StoreOrderDO::getRefundStatus, strs);
                    break;
                //已删除
                case STATUS_6:
                    wrapper.eq(StoreOrderDO::getIsSystemDel, ShopCommonEnum.DELETE_1.getValue());
                    break;
                default:
            }
        }
        if (StrUtil.isNotEmpty(reqVO.getPayStatus())) {
            wrapper.eq(StoreOrderDO::getPayType,reqVO.getPayStatus());
        }

        return selectPage(reqVO, wrapper);
    }

    default List<StoreOrderDO> selectList(StoreOrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<StoreOrderDO>()
                .eqIfPresent(StoreOrderDO::getOrderId, reqVO.getOrderId())
                .likeIfPresent(StoreOrderDO::getRealName, reqVO.getRealName())
                .eqIfPresent(StoreOrderDO::getUserPhone, reqVO.getUserPhone())
                .eqIfPresent(StoreOrderDO::getUserAddress, reqVO.getUserAddress())
                .betweenIfPresent(StoreOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StoreOrderDO::getId));
    }


    @Select("select IFNULL(sum(pay_price),0) from comida_store_order " +
            "where paid=1 and deleted=0 and refund_status=0 and uid=#{uid}")
    double sumPrice(@Param("uid") Long uid);

    @Select("SELECT IFNULL(sum(pay_price),0) " +
            " FROM comida_store_order ${ew.customSqlSegment}")
    Double todayPrice(@Param(Constants.WRAPPER) Wrapper<StoreOrderDO> wrapper);

    @Select( "select IFNULL(sum(pay_price),0)  from comida_store_order " +
            "where refund_status=0 and deleted=0 and paid=1")
    Double sumTotalPrice();

}
