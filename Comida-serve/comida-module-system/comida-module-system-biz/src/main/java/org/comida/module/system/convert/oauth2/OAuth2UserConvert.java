package org.comida.module.system.convert.oauth2;

import org.comida.module.system.controller.admin.oauth2.vo.user.OAuth2UserInfoRespVO;
import org.comida.module.system.controller.admin.oauth2.vo.user.OAuth2UserUpdateReqVO;
import org.comida.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import org.comida.module.system.dal.dataobject.user.AdminUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OAuth2UserConvert {

    OAuth2UserConvert INSTANCE = Mappers.getMapper(OAuth2UserConvert.class);

    OAuth2UserInfoRespVO convert(AdminUserDO bean);

    UserProfileUpdateReqVO convert(OAuth2UserUpdateReqVO bean);

}
