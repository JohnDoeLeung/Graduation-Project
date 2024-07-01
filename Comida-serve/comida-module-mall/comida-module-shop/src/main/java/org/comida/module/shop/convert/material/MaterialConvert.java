package org.comida.module.shop.convert.material;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.comida.module.shop.controller.admin.material.vo.*;
import org.comida.module.shop.dal.dataobject.material.MaterialDO;

/**
 * 素材库 Convert
 *
  */
@Mapper
public interface MaterialConvert {

    MaterialConvert INSTANCE = Mappers.getMapper(MaterialConvert.class);

    MaterialDO convert(MaterialCreateReqVO bean);

    MaterialDO convert(MaterialUpdateReqVO bean);

    MaterialRespVO convert(MaterialDO bean);

    List<MaterialRespVO> convertList(List<MaterialDO> list);

    PageResult<MaterialRespVO> convertPage(PageResult<MaterialDO> page);

}
