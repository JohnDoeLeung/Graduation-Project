package org.comida.module.member.convert.useraddress;

import java.util.*;

import org.comida.framework.common.pojo.PageResult;

import org.comida.module.member.controller.app.address.vo.AppUserAddressQueryVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.comida.module.member.controller.admin.useraddress.vo.*;
import org.comida.module.member.dal.dataobject.useraddress.UserAddressDO;

/**
 * 用户地址 Convert
 *
  */
@Mapper
public interface UserAddressConvert {

    UserAddressConvert INSTANCE = Mappers.getMapper(UserAddressConvert.class);

    UserAddressDO convert(UserAddressCreateReqVO bean);

    UserAddressDO convert(UserAddressUpdateReqVO bean);

    UserAddressRespVO convert(UserAddressDO bean);

    List<UserAddressRespVO> convertList(List<UserAddressDO> list);

    List<AppUserAddressQueryVo> convertList02(List<UserAddressDO> list);

    PageResult<UserAddressRespVO> convertPage(PageResult<UserAddressDO> page);

}
