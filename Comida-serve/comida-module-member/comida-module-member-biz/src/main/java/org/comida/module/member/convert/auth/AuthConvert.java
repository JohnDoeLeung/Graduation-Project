package org.comida.module.member.convert.auth;

import org.comida.module.member.controller.app.auth.vo.*;
import org.comida.module.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;
import org.comida.module.system.api.social.dto.SocialUserBindReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    AppAuthLoginRespVO convert(OAuth2AccessTokenRespDTO bean);

}
