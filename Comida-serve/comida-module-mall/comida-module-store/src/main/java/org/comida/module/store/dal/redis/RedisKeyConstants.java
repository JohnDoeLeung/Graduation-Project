package org.comida.module.store.dal.redis;

import org.comida.framework.redis.core.RedisKeyDefine;

import static org.comida.framework.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * System Redis Key 枚举类
 *
  */
public interface RedisKeyConstants {

//
//    RedisKeyDefine COMIDA_ORDER_CACHE_KEY = new RedisKeyDefine("确认订单数据缓存",
//            "comida_order_cache:%s", // 参数为访问uid+key
//            STRING, CacheDto.class, RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);

    RedisKeyDefine COMIDA_WEB_PRINT_TOKEN_KEY = new RedisKeyDefine("打印机token",
            "comida_web_print_token_cache:", // 参数为访问uid+key
            STRING, String.class, RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);

//    RedisKeyDefine COMIDA_ORDER_COUNT_CACHE_KEY = new RedisKeyDefine("统计订单数据缓存",
//            "comida_order_count_cache:%s", // 参数为访问uid
//            STRING, CacheDto.class, RedisKeyDefine.TimeoutTypeEnum.FOREVER);
//
//    RedisKeyDefine COMIDA_ADMIN_ORDER_COUNT_CACHE_KEY = new RedisKeyDefine("后台统计订单数据缓存",
//            "comida_admin_order_count_cache:", // 参数为访问uid
//            STRING, CacheDto.class, RedisKeyDefine.TimeoutTypeEnum.FOREVER);


}
