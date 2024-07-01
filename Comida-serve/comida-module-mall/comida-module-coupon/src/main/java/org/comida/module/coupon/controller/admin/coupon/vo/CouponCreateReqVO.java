package org.comida.module.coupon.controller.admin.coupon.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 优惠券创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponCreateReqVO extends CouponBaseVO {

}
