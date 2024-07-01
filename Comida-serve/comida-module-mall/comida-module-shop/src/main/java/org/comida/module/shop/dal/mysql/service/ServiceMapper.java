package org.comida.module.shop.dal.mysql.service;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;
import org.comida.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.comida.framework.mybatis.core.mapper.BaseMapperX;
import org.comida.module.shop.dal.dataobject.service.ServiceDO;
import org.apache.ibatis.annotations.Mapper;
import org.comida.module.shop.controller.admin.service.vo.*;

/**
 * 我的服务 Mapper
 *
  */
@Mapper
public interface ServiceMapper extends BaseMapperX<ServiceDO> {

    default PageResult<ServiceDO> selectPage(ServicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ServiceDO>()
                .likeIfPresent(ServiceDO::getName, reqVO.getName())
                .eqIfPresent(ServiceDO::getImage, reqVO.getImage())
                .eqIfPresent(ServiceDO::getType, reqVO.getType())
                .eqIfPresent(ServiceDO::getContent, reqVO.getContent())
                .eqIfPresent(ServiceDO::getPid, reqVO.getPid())
                .eqIfPresent(ServiceDO::getAppId, reqVO.getAppId())
                .eqIfPresent(ServiceDO::getPages, reqVO.getPages())
                .eqIfPresent(ServiceDO::getPhone, reqVO.getPhone())
                .eqIfPresent(ServiceDO::getWeigh, reqVO.getWeigh())
                .eqIfPresent(ServiceDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ServiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ServiceDO::getId));
    }

    default List<ServiceDO> selectList(ServiceExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ServiceDO>()
                .likeIfPresent(ServiceDO::getName, reqVO.getName())
                .eqIfPresent(ServiceDO::getImage, reqVO.getImage())
                .eqIfPresent(ServiceDO::getType, reqVO.getType())
                .eqIfPresent(ServiceDO::getContent, reqVO.getContent())
                .eqIfPresent(ServiceDO::getPid, reqVO.getPid())
                .eqIfPresent(ServiceDO::getAppId, reqVO.getAppId())
                .eqIfPresent(ServiceDO::getPages, reqVO.getPages())
                .eqIfPresent(ServiceDO::getPhone, reqVO.getPhone())
                .eqIfPresent(ServiceDO::getWeigh, reqVO.getWeigh())
                .eqIfPresent(ServiceDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ServiceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ServiceDO::getId));
    }

}
