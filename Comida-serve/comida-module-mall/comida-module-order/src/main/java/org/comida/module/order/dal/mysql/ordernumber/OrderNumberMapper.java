package org.comida.module.order.dal.mysql.ordernumber;

import org.comida.framework.mybatis.core.mapper.BaseMapperX;
import org.comida.module.order.dal.dataobject.ordernumber.OrderNumberDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单 Mapper
 *
  */
@Mapper
public interface OrderNumberMapper extends BaseMapperX<OrderNumberDO> {

}
