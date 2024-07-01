package org.comida.module.product.service.storeproduct.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 商品对象VO
 *
   * @date 2020-04-25
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto
{

    /** 商品id */
    private Long id;

    /** 商品图片 */
    private String image;

    private String shopName;

    private Integer shopId;

    /** 商品名称 */
    @JsonProperty("store_name")
    private String storeName;

    /** 商品简介 */
    @JsonProperty("store_info")
    private String storeInfo;

    /** 分类id */
    @JsonProperty("cate_id")
    private String cateId;

    /** 商品价格 */
    private Double price;

    /** 销量 */
    private Long sales;

    /** 库存 */
    private Long stock;

    /** 状态（0：未上架，1：上架） */
    @JsonProperty("is_show")
    private Integer isShow;

    /** 获得积分 */
    @JsonProperty("give_integral")
    private Double giveIntegral;

    /** 规格 0单 1多 */
    @JsonProperty("spec_type")
    private Integer specType;

    private ProductFormatDto attr;

    private List<FromatDetailDto> items;

    private List<ProductFormatDto> attrs;




}
