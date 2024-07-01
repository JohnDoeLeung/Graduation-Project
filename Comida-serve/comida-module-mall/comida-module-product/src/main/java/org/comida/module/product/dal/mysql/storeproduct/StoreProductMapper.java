package org.comida.module.product.dal.mysql.storeproduct;

import java.util.*;

import cn.hutool.core.convert.Convert;
import org.comida.framework.common.pojo.PageResult;
import org.comida.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.comida.framework.mybatis.core.mapper.BaseMapperX;
import org.comida.framework.security.core.util.SecurityFrameworkUtils;
import org.comida.module.product.dal.dataobject.storeproduct.StoreProductDO;
import org.comida.module.product.enums.product.DefaultEnum;
import org.apache.ibatis.annotations.Mapper;
import org.comida.module.product.controller.admin.storeproduct.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 商品 Mapper
 *
  */
@Mapper
public interface StoreProductMapper extends BaseMapperX<StoreProductDO> {

    default PageResult<StoreProductDO> selectPage(StoreProductPageReqVO reqVO) {
        LambdaQueryWrapperX<StoreProductDO> wrapper = new LambdaQueryWrapperX<>();

        Long shopId = SecurityFrameworkUtils.getLoginUser().getShopId();
        if(shopId > 0) {
            wrapper.eq(StoreProductDO::getShopId,shopId);
        }

        wrapper.likeIfPresent(StoreProductDO::getStoreName, reqVO.getStoreName())
                .likeIfPresent(StoreProductDO::getShopName, reqVO.getShopName())
                .eqIfPresent(StoreProductDO::getCateId,reqVO.getCateId())
                .orderByDesc(StoreProductDO::getId);

        wrapper.eq(StoreProductDO::getIsShow,Convert.toInt(reqVO.getIsShow()));
        if(DefaultEnum.DEFAULT_0.getValue().equals(Convert.toInt(reqVO.getStock()))){
            wrapper.eq(StoreProductDO::getStock,DefaultEnum.DEFAULT_0.getValue());
        }

        return selectPage(reqVO, wrapper);

    }

    default List<StoreProductDO> selectList(StoreProductExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<StoreProductDO>()
                .likeIfPresent(StoreProductDO::getStoreName, reqVO.getStoreName())
                .orderByDesc(StoreProductDO::getId));
    }

    @Update("update comida_store_product set is_show = #{status} where id = #{id}")
    void updateOnsale(@Param("status") Integer status, @Param("id") Long id);


    /**
     * 正常商品库存 加库存 减销量
     * @param num
     * @param productId
     * @return
     */
    @Update("update comida_store_product set stock=stock+#{num}, sales=sales-#{num}" +
            " where id=#{productId}")
    int incStockDecSales(@Param("num") Integer num,@Param("productId") Long productId);

    /**
     * 正常商品库存 减库存 加销量
     * @param num
     * @param productId
     * @return
     */
    @Update("update comida_store_product set stock=stock-#{num}, sales=sales+#{num}" +
            " where id=#{productId} and stock >= #{num}")
    int decStockIncSales(@Param("num") Integer num,@Param("productId") Long productId);

}
