package org.comida.module.order.dal.dataobject.ordernumber;

import org.comida.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单 DO
 *
  */
@TableName("comida_order_number")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderNumberDO  {

    /**
     * ID
     */
    private Long id;
    /**
     * 订单号
     */
    private String orderId;


}
