package org.comida.module.member.dal.redis;

import org.comida.framework.redis.core.RedisKeyDefine;

import static org.comida.framework.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * System Redis Key 枚举类
 *
  */
public interface RedisKeyConstants {




    RedisKeyDefine COMIDA_MINI_LOGIN_CACHE_KEY = new RedisKeyDefine("小程序登录session",
            "comida_mini_login_cache:%s", // 参数为访问uid+key
            STRING, String.class, RedisKeyDefine.TimeoutTypeEnum.FOREVER);




}
