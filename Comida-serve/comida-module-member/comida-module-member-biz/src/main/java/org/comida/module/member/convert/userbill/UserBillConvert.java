package org.comida.module.member.convert.userbill;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;

import org.comida.module.member.controller.app.user.vo.AppUserBillVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.comida.module.member.controller.admin.userbill.vo.*;
import org.comida.module.member.dal.dataobject.userbill.UserBillDO;

/**
 * 用户账单 Convert
 *
  */
@Mapper
public interface UserBillConvert {

    UserBillConvert INSTANCE = Mappers.getMapper(UserBillConvert.class);

    UserBillDO convert(UserBillCreateReqVO bean);

    UserBillDO convert(UserBillUpdateReqVO bean);

    UserBillRespVO convert(UserBillDO bean);

    List<UserBillRespVO> convertList(List<UserBillDO> list);

    List<AppUserBillVO> convertList02(List<UserBillDO> list);

    PageResult<UserBillRespVO> convertPage(PageResult<UserBillDO> page);


}
