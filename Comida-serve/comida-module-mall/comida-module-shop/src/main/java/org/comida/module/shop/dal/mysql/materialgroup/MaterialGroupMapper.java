package org.comida.module.shop.dal.mysql.materialgroup;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;
import org.comida.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.comida.framework.mybatis.core.mapper.BaseMapperX;
import org.comida.module.shop.dal.dataobject.materialgroup.MaterialGroupDO;
import org.apache.ibatis.annotations.Mapper;
import org.comida.module.shop.controller.admin.materialgroup.vo.*;

/**
 * 素材分组 Mapper
 *
  */
@Mapper
public interface MaterialGroupMapper extends BaseMapperX<MaterialGroupDO> {

    default PageResult<MaterialGroupDO> selectPage(MaterialGroupPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MaterialGroupDO>()
                .betweenIfPresent(MaterialGroupDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(MaterialGroupDO::getName, reqVO.getName())
                .orderByDesc(MaterialGroupDO::getId));
    }

    default List<MaterialGroupDO> selectList(MaterialGroupExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MaterialGroupDO>()
                .betweenIfPresent(MaterialGroupDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(MaterialGroupDO::getName, reqVO.getName())
                .orderByDesc(MaterialGroupDO::getId));
    }

}
