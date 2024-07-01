package org.comida.module.product.service.storeproduct;

import java.util.*;
import javax.validation.*;
import org.comida.module.product.controller.admin.storeproduct.vo.*;
import org.comida.module.product.dal.dataobject.storeproduct.StoreProductDO;
import org.comida.framework.common.pojo.PageResult;
import org.comida.module.product.dal.dataobject.storeproductattrvalue.StoreProductAttrValueDO;
import org.comida.module.product.service.storeproduct.dto.StoreProductDto;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 商品 Service 接口
 *
  */
public interface StoreProductService extends IService<StoreProductDO> {

    /**
     * 创建商品
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStoreProduct(@Valid StoreProductCreateReqVO createReqVO);

    /**
     * 更新商品
     *
     * @param updateReqVO 更新信息
     */
    void updateStoreProduct(@Valid StoreProductUpdateReqVO updateReqVO);

    /**
     * 删除商品
     *
     * @param id 编号
     */
    void deleteStoreProduct(Long id);

    /**
     * 获得商品
     *
     * @param id 编号
     * @return 商品
     */
    StoreProductDO getStoreProduct(Long id);

    /**
     * 获得商品列表
     *
     * @param ids 编号
     * @return 商品列表
     */
    List<StoreProductDO> getStoreProductList(Collection<Long> ids);

    /**
     * 获得商品分页
     *
     * @param pageReqVO 分页查询
     * @return 商品分页
     */
    PageResult<StoreProductDO> getStoreProductPage(StoreProductPageReqVO pageReqVO);

    /**
     * 获得商品列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 商品列表
     */
    List<StoreProductDO> getStoreProductList(StoreProductExportReqVO exportReqVO);

    /**
     * 获取生成的属性
     * @param id 商品id
     * @param jsonStr jsonStr
     * @return map
     */
    Map<String,Object> getFormatAttr(Long id, String jsonStr,boolean isActivity);

    /**
     * 新增/保存商品
     * @param storeProductDto 商品
     */
    void insertAndEditYxStoreProduct(StoreProductDto storeProductDto);

    Map<String,Object> getProductInfo(Long id);

    /**
     * 商品上架下架
     * @param id 商品id
     * @param status  ShopCommonEnum
     */
    void onSale(Long id,Integer status);

}
