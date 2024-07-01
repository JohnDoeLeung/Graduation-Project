package org.comida.module.order.service.storeorder.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {

    private String image;

    private Double price;

    private String storeName;

    private ProductAttrDto attrInfo;

}
