package org.comida.module.system.convert.notice;

import org.comida.framework.common.pojo.PageResult;
import org.comida.module.system.controller.admin.notice.vo.NoticeCreateReqVO;
import org.comida.module.system.controller.admin.notice.vo.NoticeRespVO;
import org.comida.module.system.controller.admin.notice.vo.NoticeUpdateReqVO;
import org.comida.module.system.dal.dataobject.notice.NoticeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoticeConvert {

    NoticeConvert INSTANCE = Mappers.getMapper(NoticeConvert.class);

    PageResult<NoticeRespVO> convertPage(PageResult<NoticeDO> page);

    NoticeRespVO convert(NoticeDO bean);

    NoticeDO convert(NoticeUpdateReqVO bean);

    NoticeDO convert(NoticeCreateReqVO bean);

}
