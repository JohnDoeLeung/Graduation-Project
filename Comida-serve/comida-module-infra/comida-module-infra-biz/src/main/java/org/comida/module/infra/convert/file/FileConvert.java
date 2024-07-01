package org.comida.module.infra.convert.file;

import org.comida.framework.common.pojo.PageResult;
import org.comida.module.infra.controller.admin.file.vo.file.FileRespVO;
import org.comida.module.infra.dal.dataobject.file.FileDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileConvert {

    FileConvert INSTANCE = Mappers.getMapper(FileConvert.class);

    FileRespVO convert(FileDO bean);

    PageResult<FileRespVO> convertPage(PageResult<FileDO> page);

}
