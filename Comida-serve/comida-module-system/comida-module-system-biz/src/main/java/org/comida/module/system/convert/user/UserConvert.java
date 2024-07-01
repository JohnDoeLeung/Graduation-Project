package org.comida.module.system.convert.user;

import org.comida.module.system.api.user.dto.AdminUserRespDTO;
import org.comida.module.system.controller.admin.user.vo.profile.UserProfileRespVO;
import org.comida.module.system.controller.admin.user.vo.profile.UserProfileUpdatePasswordReqVO;
import org.comida.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import org.comida.module.system.controller.admin.user.vo.user.*;
import org.comida.module.system.dal.dataobject.user.AdminUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserRespVO convert(AdminUserDO bean);

    AdminUserDO convert(UserCreateReqVO bean);

    AdminUserDO convert(UserUpdateReqVO bean);


    UserProfileRespVO convert03(AdminUserDO bean);



    AdminUserDO convert(UserProfileUpdateReqVO bean);

    AdminUserDO convert(UserProfileUpdatePasswordReqVO bean);

    List<UserSimpleRespVO> convertList04(List<AdminUserDO> list);

    AdminUserRespDTO convert4(AdminUserDO bean);

    List<AdminUserRespDTO> convertList4(List<AdminUserDO> users);

}
