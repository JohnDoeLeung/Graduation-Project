package org.comida.module.product.service.storeproduct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductFormatDto {

    private String sku = "";


    private Double price = 0d;


    private Integer stock = 0;

    private Integer integral = 0;

    private String pic = "";

    private String value1 = "";

    private String value2 = "";

    private Double volume = 0d;

    private Map<String, String> detail;

}
