package org.comida.module.product.dal.mysql.category;

import org.comida.framework.mybatis.core.mapper.BaseMapperX;
import org.comida.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.comida.framework.security.core.util.SecurityFrameworkUtils;
import org.comida.module.product.controller.admin.category.vo.ProductCategoryListReqVO;
import org.comida.module.product.dal.dataobject.category.ProductCategoryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品分类 Mapper
 *
  */
@Mapper
public interface ProductCategoryMapper extends BaseMapperX<ProductCategoryDO> {

    default List<ProductCategoryDO> selectList(ProductCategoryListReqVO listReqVO) {
        Long shopId = SecurityFrameworkUtils.getLoginUser().getShopId();
        if(shopId == 0) {
            listReqVO.setShopId(null);
        }else {
            listReqVO.setShopId(shopId.intValue());
        }
        return selectList(new LambdaQueryWrapperX<ProductCategoryDO>()
                .likeIfPresent(ProductCategoryDO::getName, listReqVO.getName())
                .likeIfPresent(ProductCategoryDO::getShopName, listReqVO.getShopName())
                .eqIfPresent(ProductCategoryDO::getShopId,listReqVO.getShopId())
                .orderByDesc(ProductCategoryDO::getId));
    }

    default List<ProductCategoryDO> selectListByStatus(Integer status,Integer shopId) {
        return selectList(new LambdaQueryWrapperX<ProductCategoryDO>()
                .eqIfPresent(ProductCategoryDO::getStatus, status)
                .eqIfPresent(ProductCategoryDO::getShopId, shopId)
                .orderByDesc(ProductCategoryDO::getId));
    }

}
