package org.comida.module.product.convert.category;

import org.comida.module.product.controller.admin.category.vo.ProductCategoryCreateReqVO;
import org.comida.module.product.controller.admin.category.vo.ProductCategoryRespVO;
import org.comida.module.product.controller.admin.category.vo.ProductCategoryUpdateReqVO;
import org.comida.module.product.controller.app.category.vo.AppCategoryRespVO;
import org.comida.module.product.dal.dataobject.category.ProductCategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 商品分类 Convert
 *
  */
@Mapper
public interface ProductCategoryConvert {

    ProductCategoryConvert INSTANCE = Mappers.getMapper(ProductCategoryConvert.class);

    ProductCategoryDO convert(ProductCategoryCreateReqVO bean);

    ProductCategoryDO convert(ProductCategoryUpdateReqVO bean);

    ProductCategoryRespVO convert(ProductCategoryDO bean);

    List<ProductCategoryRespVO> convertList(List<ProductCategoryDO> list);

    List<AppCategoryRespVO> convertList03(List<ProductCategoryDO> list);
}
