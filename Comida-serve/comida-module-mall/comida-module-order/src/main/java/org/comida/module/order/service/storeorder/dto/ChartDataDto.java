package org.comida.module.order.service.storeorder.dto;

import lombok.Data;

/**
 * @ClassName ChartDataDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/25
 **/
@Data
public class ChartDataDto {

   // @Value("#{target.adminCount}")
   private  Double num;
   private  String time;
}
