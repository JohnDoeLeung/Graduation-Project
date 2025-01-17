package org.comida.module.member.dal.dataobject.userbill;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.comida.framework.mybatis.core.dataobject.BaseDO;

/**
 * 用户账单 DO
 *
  */
@TableName("comida_user_bill")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBillDO extends BaseDO {

    /**
     * 用户账单id
     */
    @TableId
    private Long id;
    /**
     * 用户uid
     */
    private Long uid;
    /**
     * 关联id
     */
    private String linkId;
    /**
     * 0 = 支出 1 = 获得
     */
    private Integer pm;
    /**
     * 账单标题
     */
    private String title;
    /**
     * 明细种类
     */
    private String category;
    /**
     * 明细类型
     */
    private String type;
    /**
     * 明细数字
     */
    private BigDecimal number;
    /**
     * 剩余
     */
    private BigDecimal balance;
    /**
     * 备注
     */
    private String mark;
    /**
     * 0 = 带确定 1 = 有效 -1 = 无效
     */
    private Integer status;

    @TableField(exist = false)
    private BigDecimal sumAll;

    private String extendField;

}
