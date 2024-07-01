package org.comida.module.order.dal.redis.order;

import org.comida.framework.common.util.json.JsonUtils;
import org.comida.module.order.service.storeorder.dto.OrderTimeDataDto;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import static org.comida.module.order.dal.redis.RedisKeyConstants.COMIDA_ADMIN_ORDER_COUNT_CACHE_KEY;

/**
 * {@link AppUserOrderCountVo} 的 RedisDAO
 *
  */
@Repository
public class AsyncCountRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public OrderTimeDataDto get() {
        String redisKey = COMIDA_ADMIN_ORDER_COUNT_CACHE_KEY.getKeyTemplate();
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), OrderTimeDataDto.class);
    }

    public void set(OrderTimeDataDto orderTimeDataDto) {
        String redisKey = COMIDA_ADMIN_ORDER_COUNT_CACHE_KEY.getKeyTemplate();
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(orderTimeDataDto));
    }

    public void delete(Long uid) {
        String redisKey = COMIDA_ADMIN_ORDER_COUNT_CACHE_KEY.getKeyTemplate();
        stringRedisTemplate.delete(redisKey);
    }


    private static String formatKey(String key) {
        return String.format(COMIDA_ADMIN_ORDER_COUNT_CACHE_KEY.getKeyTemplate(), key);
    }

}
