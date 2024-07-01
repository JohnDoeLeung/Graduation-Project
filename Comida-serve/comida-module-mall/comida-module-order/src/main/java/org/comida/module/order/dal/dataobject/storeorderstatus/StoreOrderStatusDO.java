package org.comida.module.order.dal.dataobject.storeorderstatus;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.comida.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单操作记录 DO
 *
  */
@TableName("comida_store_order_status")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreOrderStatusDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 订单id
     */
    private Long oid;
    /**
     * 操作类型
     */
    private String changeType;
    /**
     * 操作备注
     */
    private String changeMessage;
    /**
     * 操作时间
     */
    private LocalDateTime changeTime;

}
