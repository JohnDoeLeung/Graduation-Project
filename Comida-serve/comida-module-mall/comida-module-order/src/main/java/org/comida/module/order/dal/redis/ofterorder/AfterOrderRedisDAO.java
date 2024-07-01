package org.comida.module.order.dal.redis.ofterorder;

import org.comida.module.order.service.storeorder.dto.CacheDto;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import static org.comida.module.order.dal.redis.RedisKeyConstants.COMIDA_ORDER_SALE_STATUS_KEY;

/**
 * {@link CacheDto} 的 RedisDAO
 *
  */
@Repository
public class AfterOrderRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String get(String key,Long uid) {
        String redisKey = formatKey(key+uid);
        return stringRedisTemplate.opsForValue().get(redisKey);
    }

    public void set(Long uid,String key,String o) {
        String redisKey = formatKey(key + uid);


        stringRedisTemplate.opsForValue().set(redisKey, o);
    }

    public void delete(String key,Long uid) {
        String redisKey = formatKey(key+uid);
        stringRedisTemplate.delete(redisKey);
    }



    private static String formatKey(String key) {
        return String.format(COMIDA_ORDER_SALE_STATUS_KEY.getKeyTemplate(), key);
    }

}
