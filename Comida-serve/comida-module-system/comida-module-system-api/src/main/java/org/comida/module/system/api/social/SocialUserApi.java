package org.comida.module.system.api.social;

import org.comida.framework.common.exception.ServiceException;
import org.comida.module.system.api.social.dto.SocialUserBindReqDTO;
import org.comida.module.system.api.social.dto.SocialUserUnbindReqDTO;
import org.comida.module.system.enums.social.SocialTypeEnum;

import javax.validation.Valid;

/**
 * 社交用户的 API 接口
 *
  */
public interface SocialUserApi {

    /**
     * 获得社交平台的授权 URL
     *
     * @param type 社交平台的类型 {@link SocialTypeEnum}
     * @param redirectUri 重定向 URL
     * @return 社交平台的授权 URL
     */
    String getAuthorizeUrl(Integer type, String redirectUri);
}
