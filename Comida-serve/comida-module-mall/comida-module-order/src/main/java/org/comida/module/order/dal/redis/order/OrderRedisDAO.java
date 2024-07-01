package org.comida.module.order.dal.redis.order;

import cn.hutool.core.util.IdUtil;
import org.comida.framework.common.constant.ShopConstants;
import org.comida.framework.common.util.json.JsonUtils;
import org.comida.module.order.service.storeorder.dto.CacheDto;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static org.comida.module.order.dal.redis.RedisKeyConstants.COMIDA_ORDER_CACHE_KEY;

/**
 * {@link CacheDto} 的 RedisDAO
 *
  */
@Repository
public class OrderRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public CacheDto get(String key,Long uid) {
        String redisKey = formatKey(key+uid);
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), CacheDto.class);
    }

    public String set(CacheDto cacheDto,Long uid) {
        String key = IdUtil.simpleUUID();
        String redisKey = formatKey(key + uid);
        long time =  ShopConstants.COMIDA_ORDER_CACHE_TIME;
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(cacheDto), time, TimeUnit.SECONDS);
        return key;
    }

    public void delete(String key,Long uid) {
        String redisKey = formatKey(key+uid);
        stringRedisTemplate.delete(redisKey);
    }



    private static String formatKey(String key) {
        return String.format(COMIDA_ORDER_CACHE_KEY.getKeyTemplate(), key);
    }

}
