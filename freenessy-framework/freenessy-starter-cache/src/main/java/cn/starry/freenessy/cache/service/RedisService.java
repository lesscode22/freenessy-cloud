package cn.starry.freenessy.cache.service;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description: redis缓存服务
 *
 * @author: TJ
 * @date: 2022-04-27
 **/
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public final class RedisService {

    private final RedisTemplate redisTemplate;

    public RedisService(RedisTemplate<String, Object> rt) {
        this.redisTemplate = rt;
    }

    /**
     * 设置缓存过期时间
     *
     * @param key  键
     * @param timeout 过期时间, 单位毫秒
     */
    public Boolean expire(String key, long timeout) {
        if (timeout > 0) {
            return redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
        }
        return false;
    }

    /**
     * 获取指定键过期时间
     *
     * @param key 键
     * @return 过期时间, 单位秒, 返回 0 代表永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断 key 是否存在
     *
     * @param key 键
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     */
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    // ============================ String

    /**
     * 缓存获取
     */
    public <T> T stringForGet(String key) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 设置缓存
     */
    public void stringForSet(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param timeout  过期时间, 单位毫秒; 若小于等于0则永不过期
     */
    public void stringForSet(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 当key不存在时, set成功, 返回true
     * 当key存在时, 取消set, 返回false
     */
    public Boolean stringForSetIfAbsent(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 当key不存在时, set成功, 返回true, 值过期时间单位毫秒
     * 当key存在时, 取消set, 返回false
     *
     */
    public Boolean stringForSetIfAbsent(String key, Object value, long timeout) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 递增
     * @param key   键
     * @param delta 要增加的数量
     */
    public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key   键
     * @param delta 要减少的数量
     */
    public Long decr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    // ================================ Hash

    /**
     * 获取Hash结构缓存值
     *
     * @param key 键
     * @return 对应的多个键值对
     */
    public <V> Map<String, V> hashForGet(String key) {
        HashOperations<String, String, V> opsForHash = redisTemplate.opsForHash();
        return opsForHash.entries(key);
    }

    /**
     * 获取Hash结构缓存值
     *
     * @param key  键
     * @param item 项
     * @return 值
     */
    public <V> V hashForGet(String key, String item) {
        HashOperations<String, String, V> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, item);
    }

    /**
     * Hash结构设置缓存
     *
     * @param key 键
     * @param map 对应多个键值对, key不能为null
     */
    public void hashForSet(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * Hash结构设置缓存带过期时间
     *
     * @param key  键
     * @param map  对应多个键值对, key不能为null
     * @param timeout 过期时间, 单位毫秒
     */
    public void hashForSet(String key, Map<String, Object> map, long timeout) {
        redisTemplate.opsForHash().putAll(key, map);
        if (timeout > 0) {
            expire(key, timeout);
        }
    }

    /**
     * Hash设置缓存
     */
    public void hashForSet(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * Hash设置缓存, 带过期时间, 单位毫秒
     */
    public void hashForSet(String key, String item, Object value, long timeout) {
        redisTemplate.opsForHash().put(key, item, value);
        if (timeout > 0) {
            expire(key, timeout);
        }
    }

    /**
     * Hash删除值
     *
     * @param key  键
     * @param item 项
     */
    public void hashForDel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键, 不能为null
     * @param item 项, 不能为null
     */
    public boolean existHashKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    // ============================ set

    /**
     * 获取Set结构值
     */
    public <T> Set<T> setForGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 设置set结构缓存
     *
     * @param key    键
     * @param values 值, 可以是多个
     * @return 成功个数
     */
    public Long setForAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param timeout   过期时间, 单位毫秒
     * @param values 值
     * @return 成功个数
     */
    public Long setForAdd(String key, long timeout, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if (timeout > 0L) {
            expire(key, timeout);
        }
        return count;
    }

    /**
     * 删除值为value的数据
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long setForDel(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 判断set结构中是否存在该值
     */
    public Boolean existSetValue(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    // =============================== list

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束, 0 到 -1 代表获取所有值
     */
    public <T> List<T> listForGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     */
    public Long listForSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 通过索引获取list中的值
     *
     * @param key   键
     * @param index 索引, 当index >= 0时, 0:表头; 1:第二个元素, 依次类推;
     *                     index < 0时, -1:表尾; -2:倒数第二个元素, 依次类推
     */
    public <V> V listForGet(String key, long index) {
        ListOperations<String, V> operations = redisTemplate.opsForList();
        return operations.index(key, index);
    }

    /**
     * 加入list缓存
     *
     * @param key   键
     * @param value 值
     */
    public void listForAdd(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 加入list缓存带过期时间
     *
     * @param key   键
     * @param value 值
     * @param timeout  单位毫秒
     */
    public void listForAdd(String key, Object value, long timeout) {
        redisTemplate.opsForList().rightPush(key, value);
        if (timeout > 0) {
            expire(key, timeout);
        }
    }

    /**
     * 加入list缓存
     *
     * @param key   键
     * @param value 值
     */
    public void listForAdd(String key, List<Object> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 加入list缓存
     *
     * @param key   键
     * @param value 值
     * @param timeout  过期时间, 单位毫秒
     */
    public void listForAdd(String key, List<Object> value, long timeout) {
        redisTemplate.opsForList().rightPushAll(key, value);
        if (timeout > 0) {
            expire(key, timeout);
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     */
    public void listForUpdate(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    // ================================ zset

    /**
     * zset添加
     */
    public Boolean zsetForAdd(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 获取指定元素分数
     */
    public Double zsetForGet(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * zset获取指定分数内的元素个数
     */
    public Long zsetForCount(String key, double minScore, double maxScore) {
        return redisTemplate.opsForZSet().count(key, minScore, maxScore);
    }

    /**
     * 获取指定分数范围内的集合
     */
    public <V> Set<V> zsetForRange(String key, double minScore, double maxScore) {
        return redisTemplate.opsForZSet().rangeByScore(key, minScore, maxScore);
    }

    /**
     * 将元素按score降序之后取 start ~ end 范围内数据;
     * 包含 start/end 索引位置
     */
    public <V> Set<V> zsetForReverse(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }
}
