package org.comida.module.store.controller.admin.storeshop.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.comida.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static org.comida.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 门店管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoreShopPageReqVO extends PageParam {

    @Schema(description = "店铺名称", example = "李四")
    private String name;

    @Schema(description = "店铺电话")
    private String mobile;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "多张图片")
    private String images;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "地图定位地址")
    private String addressMap;

    @Schema(description = "经度")
    private String lng;

    @Schema(description = "纬度")
    private String lat;

    @Schema(description = "外卖配送距离,单位为千米。0表示不送外卖")
    private Integer distance;

    @Schema(description = "起送价钱", example = "15157")
    private BigDecimal minPrice;

    @Schema(description = "配送价格", example = "11771")
    private BigDecimal deliveryPrice;

    @Schema(description = "公告")
    private String notice;

    @Schema(description = "是否营业:0=否,1=是", example = "2")
    private Integer status;

    @Schema(description = "管理员id", example = "22251")
    private String adminId;

    @Schema(description = "添加时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "营业开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "营业结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    private Long shopId;

}
