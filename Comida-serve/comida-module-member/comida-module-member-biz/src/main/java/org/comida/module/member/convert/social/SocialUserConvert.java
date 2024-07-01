package org.comida.module.member.convert.social;

import org.comida.module.system.api.social.dto.SocialUserBindReqDTO;
import org.comida.module.system.api.social.dto.SocialUserUnbindReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SocialUserConvert {

    SocialUserConvert INSTANCE = Mappers.getMapper(SocialUserConvert.class);

}
