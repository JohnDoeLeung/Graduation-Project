package org.comida.module.system.convert.dict;

import org.comida.framework.common.pojo.PageResult;
import org.comida.module.system.api.dict.dto.DictDataRespDTO;
import org.comida.module.system.controller.admin.dict.vo.data.*;
import org.comida.module.system.dal.dataobject.dict.DictDataDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DictDataConvert {

    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    List<DictDataSimpleRespVO> convertList(List<DictDataDO> list);

    DictDataRespVO convert(DictDataDO bean);

    PageResult<DictDataRespVO> convertPage(PageResult<DictDataDO> page);

    DictDataDO convert(DictDataUpdateReqVO bean);

    DictDataDO convert(DictDataCreateReqVO bean);


    DictDataRespDTO convert02(DictDataDO bean);

}
