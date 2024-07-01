package org.comida.module.system.api.social;

import org.comida.module.system.api.social.dto.SocialUserBindReqDTO;
import org.comida.module.system.api.social.dto.SocialUserUnbindReqDTO;
import org.comida.module.system.service.social.SocialUserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 社交用户的 API 实现类
 *
  */
@Service
@Validated
public class SocialUserApiImpl implements SocialUserApi {

    @Resource
    private SocialUserService socialUserService;

    @Override
    public String getAuthorizeUrl(Integer type, String redirectUri) {
        return socialUserService.getAuthorizeUrl(type, redirectUri);
    }
}
