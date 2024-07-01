package org.comida.module.shop.convert.materialgroup;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.comida.module.shop.controller.admin.materialgroup.vo.*;
import org.comida.module.shop.dal.dataobject.materialgroup.MaterialGroupDO;

/**
 * 素材分组 Convert
 *
  */
@Mapper
public interface MaterialGroupConvert {

    MaterialGroupConvert INSTANCE = Mappers.getMapper(MaterialGroupConvert.class);

    MaterialGroupDO convert(MaterialGroupCreateReqVO bean);

    MaterialGroupDO convert(MaterialGroupUpdateReqVO bean);

    MaterialGroupRespVO convert(MaterialGroupDO bean);

    List<MaterialGroupRespVO> convertList(List<MaterialGroupDO> list);

    PageResult<MaterialGroupRespVO> convertPage(PageResult<MaterialGroupDO> page);


}
