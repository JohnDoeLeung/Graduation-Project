package org.comida.module.store.dal.redis;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static org.comida.module.store.dal.redis.RedisKeyConstants.COMIDA_WEB_PRINT_TOKEN_KEY;


/**

 *
  */
@Repository
public class PrintTokenRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String get() {
        String redisKey = COMIDA_WEB_PRINT_TOKEN_KEY.getKeyTemplate();
        return stringRedisTemplate.opsForValue().get(redisKey);
    }

    public void set(String o) {
        String redisKey = COMIDA_WEB_PRINT_TOKEN_KEY.getKeyTemplate();


        stringRedisTemplate.opsForValue().set(redisKey, o,30, TimeUnit.DAYS);
    }

    public void delete() {
        String redisKey = COMIDA_WEB_PRINT_TOKEN_KEY.getKeyTemplate();
        stringRedisTemplate.delete(redisKey);
    }


//
//    private static String formatKey) {
//        return String.format(COMIDA_ORDER_SALE_STATUS_KEY.getKeyTemplate(), key);
//    }

}
