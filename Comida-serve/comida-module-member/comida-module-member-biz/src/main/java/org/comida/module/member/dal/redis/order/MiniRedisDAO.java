package org.comida.module.member.dal.redis.order;

import org.comida.framework.common.util.json.JsonUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import static org.comida.module.member.dal.redis.RedisKeyConstants.COMIDA_MINI_LOGIN_CACHE_KEY;


/**
 *
  */
@Repository
public class MiniRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String get(String key) {
        String redisKey = formatKey(key);
        return stringRedisTemplate.opsForValue().get(redisKey);
    }

    public String set(String str,String key) {
        String redisKey = formatKey(key);
        stringRedisTemplate.opsForValue().set(redisKey, str);
        return key;
    }

    public void delete(String key) {
        String redisKey = formatKey(key);
        stringRedisTemplate.delete(redisKey);
    }



    private static String formatKey(String key) {
        return String.format(COMIDA_MINI_LOGIN_CACHE_KEY.getKeyTemplate(), key);
    }

}
