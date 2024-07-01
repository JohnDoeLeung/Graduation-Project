package org.comida.module.product.service.category;

import org.comida.framework.common.enums.CommonStatusEnum;
import org.comida.module.product.controller.admin.category.vo.ProductCategoryCreateReqVO;
import org.comida.module.product.controller.admin.category.vo.ProductCategoryListReqVO;
import org.comida.module.product.controller.admin.category.vo.ProductCategoryUpdateReqVO;
import org.comida.module.product.convert.category.ProductCategoryConvert;
import org.comida.module.product.dal.dataobject.category.ProductCategoryDO;
import org.comida.module.product.dal.mysql.category.ProductCategoryMapper;
import org.comida.module.store.dal.dataobject.storeshop.StoreShopDO;
import org.comida.module.store.dal.mysql.storeshop.StoreShopMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static org.comida.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.comida.module.product.enums.ErrorCodeConstants.*;

/**
 * 商品分类 Service 实现类
 *
  */
@Service
@Validated
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategoryDO> implements ProductCategoryService {

    @Resource
    private ProductCategoryMapper productCategoryMapper;
    @Resource
    private StoreShopMapper storeShopMapper;

    @Override
    public Long createCategory(ProductCategoryCreateReqVO createReqVO) {
        // 校验父分类存在
        //validateParentProductCategory(createReqVO.getParentId());

        // 插入
        ProductCategoryDO category = ProductCategoryConvert.INSTANCE.convert(createReqVO);
        StoreShopDO storeShopDO = storeShopMapper.selectById(createReqVO.getShopId());
        category.setShopName(storeShopDO.getName());
        productCategoryMapper.insert(category);
        // 返回
        return category.getId();
    }

    @Override
    public void updateCategory(ProductCategoryUpdateReqVO updateReqVO) {
        // 校验分类是否存在
        validateProductCategoryExists(updateReqVO.getId());
        // 校验父分类存在
       //validateParentProductCategory(updateReqVO.getParentId());

        // 更新
        ProductCategoryDO updateObj = ProductCategoryConvert.INSTANCE.convert(updateReqVO);
        StoreShopDO storeShopDO = storeShopMapper.selectById(updateObj.getShopId());
        updateObj.setShopName(storeShopDO.getName());
        productCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteCategory(Long id) {
        // 校验分类是否存在
        validateProductCategoryExists(id);

        // TODO comida 补充只有不存在商品才可以删除
        // 删除
        productCategoryMapper.deleteById(id);
    }


    private void validateProductCategoryExists(Long id) {
        ProductCategoryDO category = productCategoryMapper.selectById(id);
        if (category == null) {
            throw exception(CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public ProductCategoryDO getCategory(Long id) {
        return productCategoryMapper.selectById(id);
    }

    @Override
    public void validateCategory(Long id) {
        ProductCategoryDO category = productCategoryMapper.selectById(id);
        if (category == null) {
            throw exception(CATEGORY_NOT_EXISTS);
        }
        if (Objects.equals(category.getStatus(), CommonStatusEnum.DISABLE.getStatus())) {
            throw exception(CATEGORY_DISABLED, category.getName());
        }
    }


    @Override
    public List<ProductCategoryDO> getEnableCategoryList(ProductCategoryListReqVO listReqVO) {
        return productCategoryMapper.selectList(listReqVO);
    }

    @Override
    public List<ProductCategoryDO> getEnableCategoryList(Integer shopId) {
        return productCategoryMapper.selectListByStatus(CommonStatusEnum.ENABLE.getStatus(),shopId);
    }

}
