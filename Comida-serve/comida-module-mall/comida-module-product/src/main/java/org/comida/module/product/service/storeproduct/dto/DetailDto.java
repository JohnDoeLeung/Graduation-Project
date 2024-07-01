package org.comida.module.product.service.storeproduct.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DetailDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/12
 **/
@Data
public class DetailDto {
    private List<String> data;

    //private List<Map<String,List<Map<String,String>>>> res;

    private List<Map<String,Map<String,String>>> res;
}
