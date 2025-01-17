package org.comida.module.store.dal.mysql.storeshop;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;
import org.comida.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.comida.framework.mybatis.core.mapper.BaseMapperX;
import org.comida.framework.security.core.util.SecurityFrameworkUtils;
import org.comida.module.store.controller.app.storeshop.vo.AppStoreShopVO;
import org.comida.module.store.dal.dataobject.storeshop.StoreShopDO;
import org.apache.ibatis.annotations.Mapper;
import org.comida.module.store.controller.admin.storeshop.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 门店管理 Mapper
 *
  */
@Mapper
public interface StoreShopMapper extends BaseMapperX<StoreShopDO> {

    default PageResult<StoreShopDO> selectPage(StoreShopPageReqVO reqVO) {
        Long shopId = SecurityFrameworkUtils.getLoginUser().getShopId();
        if(shopId == 0) {
            reqVO.setShopId(null);
        }else {
            reqVO.setShopId(shopId);
        }
        return selectPage(reqVO, new LambdaQueryWrapperX<StoreShopDO>()
                .likeIfPresent(StoreShopDO::getName, reqVO.getName())
                .eqIfPresent(StoreShopDO::getMobile, reqVO.getMobile())
                .eqIfPresent(StoreShopDO::getId, reqVO.getShopId())
                .eqIfPresent(StoreShopDO::getDistance, reqVO.getDistance())
                .eqIfPresent(StoreShopDO::getMinPrice, reqVO.getMinPrice())
                .eqIfPresent(StoreShopDO::getDeliveryPrice, reqVO.getDeliveryPrice())
                .eqIfPresent(StoreShopDO::getNotice, reqVO.getNotice())
                .eqIfPresent(StoreShopDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(StoreShopDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(StoreShopDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(StoreShopDO::getEndTime, reqVO.getEndTime())
                .orderByDesc(StoreShopDO::getId));
    }

    default List<StoreShopDO> selectList(StoreShopExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<StoreShopDO>()
                .likeIfPresent(StoreShopDO::getName, reqVO.getName())
                .eqIfPresent(StoreShopDO::getMobile, reqVO.getMobile())
                .eqIfPresent(StoreShopDO::getImage, reqVO.getImage())
                .eqIfPresent(StoreShopDO::getImages, reqVO.getImages())
                .eqIfPresent(StoreShopDO::getAddress, reqVO.getAddress())
                .eqIfPresent(StoreShopDO::getAddressMap, reqVO.getAddressMap())
                .eqIfPresent(StoreShopDO::getLng, reqVO.getLng())
                .eqIfPresent(StoreShopDO::getLat, reqVO.getLat())
                .eqIfPresent(StoreShopDO::getDistance, reqVO.getDistance())
                .eqIfPresent(StoreShopDO::getMinPrice, reqVO.getMinPrice())
                .eqIfPresent(StoreShopDO::getDeliveryPrice, reqVO.getDeliveryPrice())
                .eqIfPresent(StoreShopDO::getNotice, reqVO.getNotice())
                .eqIfPresent(StoreShopDO::getStatus, reqVO.getStatus())
                .eqIfPresent(StoreShopDO::getAdminId, reqVO.getAdminId())
                .betweenIfPresent(StoreShopDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(StoreShopDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(StoreShopDO::getEndTime, reqVO.getEndTime())
                .orderByDesc(StoreShopDO::getId));
    }

    @Select("<script>SELECT*,ROUND(6378.138 * 2 * ASIN(SQRT(POW(SIN((#{lat} * PI() / 180 - lat * PI() / 180" +
            "    ) / 2),2) + COS(40.0497810000 * PI() / 180) * COS(lat * PI() / 180) * POW(" +
            "    SIN((#{lon} * PI() / 180 - lng * PI() / 180) / 2),2))) * 1000) AS dis" +
            "    FROM comida_store_shop WHERE deleted=0 AND status = 1 " +
            "<if test =\"name !=''\">and name = #{name}</if>" +
            "<if test =\"shopId > 0\">and id = #{shopId}</if>" +
            " ORDER BY dis ASC</script>"
    )
    List<AppStoreShopVO> getStoreList(@Param("lon") double lon, @Param("lat") double lat,
                                      @Param("name") String name,@Param("shopId") Integer shopId);

}
