package org.comida.module.shop.dal.dataobject.shopads;

import org.comida.framework.mybatis.core.type.StringListTypeHandler;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.comida.framework.mybatis.core.dataobject.BaseDO;

/**
 * 广告图管理 DO
 *
  */
@TableName(value = "comida_shop_ads")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopAdsDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 图片
     */
    private String image;
    /**
     * 是否显现
     */
    private Integer isSwitch;
    /**
     * 权重
     */
    private Integer weigh;
    /**
     * 所支持的店铺id用','区分，0代表全部
     */
    private String shopId;

    private String shopName;

}
