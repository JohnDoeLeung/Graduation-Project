package org.comida.module.order.dal.redis.order;

import cn.hutool.core.util.IdUtil;
import org.comida.framework.common.constant.ShopConstants;
import org.comida.framework.common.util.json.JsonUtils;
import org.comida.module.member.controller.app.user.vo.AppUserOrderCountVo;
import org.comida.module.order.service.storeorder.dto.CacheDto;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static org.comida.module.order.dal.redis.RedisKeyConstants.COMIDA_ORDER_CACHE_KEY;
import static org.comida.module.order.dal.redis.RedisKeyConstants.COMIDA_ORDER_COUNT_CACHE_KEY;

/**
 * {@link AppUserOrderCountVo} 的 RedisDAO
 *
  */
@Repository
public class AsyncOrderRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public AppUserOrderCountVo get(Long uid) {
        String redisKey = formatKey(""+uid);
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), AppUserOrderCountVo.class);
    }

    public void set(AppUserOrderCountVo appUserOrderCountVo, Long uid) {
        String redisKey = formatKey("" + uid);
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(appUserOrderCountVo));
    }

    public void delete(Long uid) {
        String redisKey = formatKey(""+uid);
        stringRedisTemplate.delete(redisKey);
    }


    private static String formatKey(String key) {
        return String.format(COMIDA_ORDER_COUNT_CACHE_KEY.getKeyTemplate(), key);
    }

}
