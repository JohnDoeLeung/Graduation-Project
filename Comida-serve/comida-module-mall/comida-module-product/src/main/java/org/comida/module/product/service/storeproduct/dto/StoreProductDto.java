package org.comida.module.product.service.storeproduct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 商品对象DTO
 *
   * @date 2020-04-23
 */
@Getter
@Setter
@ToString
public class StoreProductDto
{

    /** 商品id */
    private Long id;

    /**
     * 店铺id
     */
    private Integer shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /** 商品图片 */
    @NotBlank(message = "商品图片必传")
    private String image;



    /** 商品名称 */
    @NotBlank(message = "商品名称不能为空")
    @JsonProperty("store_name")
    private String storeName;

    /** 商品简介 */
    @JsonProperty("store_info")
    private String storeInfo;

    /** 分类id */
    @NotNull(message = "分类id不能为空")
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

    //属性项目
    private List<FromatDetailDto> items;

    //sku结果集
    private List<ProductFormatDto> attrs;


}
