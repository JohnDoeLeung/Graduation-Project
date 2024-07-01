package org.comida.module.shop.controller.app.ad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
* 广告图VO
*/
@Data
public class AppShopAdsVO {

    @Schema(description = "id", required = true, example = "24753")
    private Long id;

    @Schema(description = "图片")
    private String image;



}
