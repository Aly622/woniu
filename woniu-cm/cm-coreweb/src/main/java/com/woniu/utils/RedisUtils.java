package com.woniu.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisZSetCommands.Limit;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.SerializationUtils;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RedisUtils {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private int cursorCounts = 10000;
	
	private int maxKeyCnt=5;

	private JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();

	// =============================common============================
	/**
	 * 指定缓存失效时间
	 * 
	 * @param: key 键
	 * @param: time 时间(秒)
	 * @return:
	 */
	public boolean expire(String key, long time) {
		try {
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	/**
	 * 根据key 获取过期时间
	 * 
	 * @param: key 键 不能为null
	 * @return: 时间(秒) 返回0代表为永久有效
	 */
	public long getExpire(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	/**
	 * 判断key是否存在
	 * 
	 * @param: key 键
	 * @return: true 存在 false不存在
	 */
	public boolean hasKey(String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	/**
	 * 删除缓存
	 * 
	 * @param: key 可以传一个值 或多个
	 */
	@SuppressWarnings("unchecked")
	public void del(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
			}
		}
	}

	// ============================String=============================
	/**
	 * 普通缓存获取
	 * 
	 * @param: key 键
	 * @return: 值
	 */
	public Object get(String key) {
		return key == null ? null : redisTemplate.opsForValue().get(key);
	}

	/**
	 * 普通缓存获取字符串
	 * 
	 * @param: key
	 * @return:
	 */
	public String getString(String key) {
		Object obj = this.get(key);
		if (obj != null) {
			return obj.toString();
		}
		return null;
	}

	/**
	 * 普通缓存放入
	 * 
	 * @param: key 键
	 * @param: value 值
	 * @return: true成功 false失败
	 */
	public boolean set(String key, Object value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}

	}

	/**
	 * 普通缓存放入并设置时间
	 * 
	 * @param: key 键
	 * @param: value 值
	 * @param: time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * @return: true成功 false 失败
	 */
	public boolean set(String key, Object value, long time) {
		try {
			if (time > 0) {
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			} else {
				set(key, value);
			}
			return true;
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	/**
	 * 若不存在时缓存并设置时间 5min
	 * 
	 * @param: key 键
	 * @param: value 值
	 * @return: true成功 false 失败
	 */
	public boolean setIfAbsent(String key, Object value) {
		try {
			redisTemplate.opsForValue().setIfAbsent(key, value,5, TimeUnit.MINUTES);
			return true;
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	public void deleteKey(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 递增
	 * 
	 * @param: key 键
	 * @param: by 要增加几(大于0)
	 * @return:
	 */
	public long incr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("递增因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * 递减
	 * 
	 * @param: key 键
	 * @param: by 要减少几(小于0)
	 * @return:
	 */
	public long decr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("递减因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, -delta);
	}

	// ================================Map=================================
	/**
	 * HashGet
	 * 
	 * @param: key 键 不能为null
	 * @param: item 项 不能为null
	 * @return: 值
	 */
	public Object hget(String key, String item) {
		return redisTemplate.opsForHash().get(key, item);
	}

	/**
	 * 获取hashKey对应的所有键值
	 * 
	 * @param: key 键
	 * @return: 对应的多个键值
	 */
	public Map<Object, Object> hmget(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * HashSet
	 * 
	 * @param: key 键
	 * @param: map 对应多个键值
	 * @return: true 成功 false 失败
	 */
	public boolean hmset(String key, Map<String, Object> map) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	/**
	 * HashSet 并设置时间
	 * 
	 * @param: key 键
	 * @param: map 对应多个键值
	 * @param: time 时间(秒)
	 * @return: true成功 false失败
	 */
	public boolean hmset(String key, Map<String, Object> map, long time) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	/**
	 * 向一张hash表中放入数据,如果不存在将创建
	 * 
	 * @param: key 键
	 * @param: item 项
	 * @param: value 值
	 * @return: true 成功 false失败
	 */
	public boolean hset(String key, String item, Object value) {
		try {
			redisTemplate.opsForHash().put(key, item, value);
			return true;
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	/**
	 * 向一张hash表中放入数据,如果不存在将创建
	 * 
	 * @param: key 键
	 * @param: item 项
	 * @param: value 值
	 * @param: time 时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
	 * @return: true 成功 false失败
	 */
	public boolean hset(String key, String item, Object value, long time) {
		try {
			redisTemplate.opsForHash().put(key, item, value);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	/**
	 * 删除hash表中的值
	 * 
	 * @param: key 键 不能为null
	 * @param: item 项 可以使多个 不能为null
	 */
	public void hdel(String key, Object... item) {
		redisTemplate.opsForHash().delete(key, item);
	}

	/**
	 * 判断hash表中是否有该项的值
	 * 
	 * @param: key 键 不能为null
	 * @param: item 项 不能为null
	 * @return: true 存在 false不存在
	 */
	public boolean hHasKey(String key, String item) {
		return redisTemplate.opsForHash().hasKey(key, item);
	}

	/**
	 * hash递增 如果不存在,就会创建一个 并把新增后的值返回
	 * 
	 * @param: key 键
	 * @param: item 项
	 * @param: by 要增加几(大于0)
	 * @return:
	 */
	public double hincr(String key, String item, double by) {
		return redisTemplate.opsForHash().increment(key, item, by);
	}

	/**
	 * hash递减
	 * 
	 * @param: key 键
	 * @param: item 项
	 * @param: by 要减少记(小于0)
	 * @return:
	 */
	public double hdecr(String key, String item, double by) {
		return redisTemplate.opsForHash().increment(key, item, -by);
	}

	// ============================set=============================
	/**
	 * 根据key获取Set中的所有值
	 * 
	 * @param: key 键
	 * @return:
	 */
	public Set<Object> sGet(String key) {
		try {
			return redisTemplate.opsForSet().members(key);
		} catch (Exception e) {
			log.error("redis error", e);
			return null;
		}
	}

	/**
	 * 根据value从一个set中查询,是否存在
	 * 
	 * @param: key 键
	 * @param: value 值
	 * @return: true 存在 false不存在
	 */
	public boolean sHasKey(String key, Object value) {
		try {
			return redisTemplate.opsForSet().isMember(key, value);
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	/**
	 * 将数据放入set缓存
	 * 
	 * @param: key 键
	 * @param: values 值 可以是多个
	 * @return: 成功个数
	 */
	public long sSet(String key, Object... values) {
		try {
			return redisTemplate.opsForSet().add(key, values);
		} catch (Exception e) {
			log.error("redis error", e);
			return 0;
		}
	}

	/**
	 * 将set数据放入缓存
	 * 
	 * @param: key 键
	 * @param: time 时间(秒)
	 * @param: values 值 可以是多个
	 * @return: 成功个数
	 */
	public long sSetAndTime(String key, long time, Object... values) {
		try {
			Long count = redisTemplate.opsForSet().add(key, values);
			if (time > 0)
				expire(key, time);
			return count;
		} catch (Exception e) {
			log.error("redis error", e);
			return 0;
		}
	}

	/**
	 * 获取set缓存的长度
	 * 
	 * @param: key 键
	 * @return:
	 */
	public long sGetSetSize(String key) {
		try {
			return redisTemplate.opsForSet().size(key);
		} catch (Exception e) {
			log.error("redis error", e);
			return 0;
		}
	}

	/**
	 * 移除值为value的
	 * 
	 * @param: key 键
	 * @param: values 值 可以是多个
	 * @return: 移除的个数
	 */
	public long setRemove(String key, Object... values) {
		try {
			Long count = redisTemplate.opsForSet().remove(key, values);
			return count;
		} catch (Exception e) {
			log.error("redis error", e);
			return 0;
		}
	}

	// ===============================list=================================
	/**
	 * 获取list缓存的内容
	 * 
	 * @param: key 键
	 * @param: start 开始
	 * @param: end 结束 0 到 -1代表所有值
	 * @return:
	 */
	public List<Object> lGet(String key, long start, long end) {
		try {
			return redisTemplate.opsForList().range(key, start, end);
		} catch (Exception e) {
			log.error("redis error", e);
			return null;
		}
	}

	/**
	 * 获取list缓存的长度
	 * 
	 * @param: key 键
	 * @return:
	 */
	public long lGetListSize(String key) {
		try {
			return redisTemplate.opsForList().size(key);
		} catch (Exception e) {
			log.error("redis error", e);
			return 0;
		}
	}

	/**
	 * 通过索引 获取list中的值
	 * 
	 * @param: key 键
	 * @param: index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
	 * @return:
	 */
	public Object lGetIndex(String key, long index) {
		try {
			return redisTemplate.opsForList().index(key, index);
		} catch (Exception e) {
			log.error("redis error", e);
			return null;
		}
	}

	/**
	 * 将list放入缓存
	 * 
	 * @param: key 键
	 * @param: value 值
	 * @param: time 时间(秒)
	 * @return:
	 */
	public boolean lSet(String key, Object value) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			return true;
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	/**
	 * 将list放入缓存
	 * 
	 * @param: key 键
	 * @param: value 值
	 * @param: time 时间(秒)
	 * @return:
	 */
	public boolean lSet(String key, Object value, long time) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			if (time > 0)
				expire(key, time);
			return true;
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	/**
	 * 将list放入缓存
	 * @param: key 键
	 * @param: value 值
	 * @param: time 时间(秒)
	 * @return:
	 */
	public boolean lSet(String key, List<Object> value) {
		try {
			redisTemplate.opsForList().rightPushAll(key, value);
			return true;
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	/**
	 * 将list放入缓存
	 * @param: key 键
	 * @param: value 值
	 * @param: time 时间(秒)
	 * @return:
	 */
	public boolean lSet(String key, List<Object> value, long time) {
		try {
			redisTemplate.opsForList().rightPushAll(key, value);
			if (time > 0)
				expire(key, time);
			return true;
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	/**
	 * 根据索引修改list中的某条数据
	 * 
	 * @param: key 键
	 * @param: index 索引
	 * @param: value 值
	 * @return:
	 */
	public boolean lUpdateIndex(String key, long index, Object value) {
		try {
			redisTemplate.opsForList().set(key, index, value);
			return true;
		} catch (Exception e) {
			log.error("redis error", e);
			return false;
		}
	}

	/**
	 * 移除N个值为value
	 * 
	 * @param: key 键
	 * @param: count 移除多少个
	 * @param: value 值
	 * @return: 移除的个数
	 */
	public long lRemove(String key, long count, Object value) {
		try {
			Long remove = redisTemplate.opsForList().remove(key, count, value);
			return remove;
		} catch (Exception e) {
			log.error("redis error", e);
			return 0;
		}
	}

	// 转 字符串 set
	public Set<String> sMembers(String key) {
		Set<Object> results = redisTemplate.opsForSet().members(key);
		return results.stream().map(result -> result.toString()).collect(Collectors.toSet());
	}

	public long sCount(String key) {
		return redisTemplate.opsForSet().size(key);
	}

	/**
	 * 给集合key添加多个值，集合不存在创建后再添加
	 */
	public boolean sAdd(String key, Object... value) {
		long counts = redisTemplate.opsForSet().add(key, value);
		return counts >= 0 ? true : false;
	}

	/**
	 * 给集合key添加多个值，集合不存在创建后再添加,有效time
	 */
	public boolean sAdd(String key, long time, Object... value) {
		try {
			long counts = redisTemplate.opsForSet().add(key, value);
			if (time > 0)
				expire(key, time);
			return counts >= 0 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Redis本身就是基于tcp的一个Request/Response protocol模式,优先选择Batch，一次cmd保存多个值
	 * PipeLine 将一批cmd打包起来发送一次request,若是一次发送cmd及数据太大，进行拆包
	 * @param strGroups
	 * @param expireTime
	 * @return
	 */
	public <T> boolean sAddBatch(Map<String, Set<T>> strGroups, long expireTime) {
		List<String> keys= IteratorUtils.toList(strGroups.keySet().iterator());
		List<List<String>> subRedisKeysList= ListUtils.splitList(keys, maxKeyCnt);
		subRedisKeysList.forEach(subKeys->{
			//拆包操作
			 redisTemplate.execute(new RedisCallback<Boolean>() {

				@Nullable
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					connection.openPipeline();
					subKeys.forEach(key->{
						List<T> vals = IteratorUtils.toList(strGroups.get(key).iterator());
						byte[] keyByte = rawKey(key);
						byte[][] bytearr = new byte[vals.size()][];
						for (int i = 0; i < vals.size(); i++) {
							bytearr[i] = serializer.serialize(vals.get(i));
						}
						connection.sAdd(keyByte, bytearr);
						connection.expire(keyByte, expireTime);
					});
					List<Object> result = connection.closePipeline();
					return !CollectionUtils.isEmpty(result);
				}
			});
		});
		return true;
	}

	public <T> boolean sAddBatch1(Map<String, Set<T>> strGroups, long expireTime) {

		return redisTemplate.execute(new RedisCallback<Boolean>() {

			@Nullable
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.openPipeline();
				strGroups.forEach((key, values) -> {
					List<T> vals = IteratorUtils.toList(values.iterator());
					byte[] keyByte = rawKey(key);
					byte[][] bytearr = new byte[vals.size()][];
					for (int i = 0; i < vals.size(); i++) {
						bytearr[i] = serializer.serialize(vals.get(i));
					}
					connection.sAdd(keyByte, bytearr);
					connection.expire(keyByte, expireTime);
				});
				List<Object> result = connection.closePipeline();
				return !CollectionUtils.isEmpty(result);
			}
		});
	}

	public boolean zAddBatch(Map<String, Map<String,Double>> numGroups, long expireTime) {

		return redisTemplate.execute(new RedisCallback<Boolean>() {

			@Nullable
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.openPipeline();
				numGroups.forEach((key, values) -> {
					byte[] keyByte = rawKey(key);	
					values.forEach((cid,num)->{
						  connection.zAdd(keyByte, num, serializer.serialize(cid));
					});
					connection.expire(keyByte, expireTime);
				});
				List<Object> result = connection.closePipeline();
				return !CollectionUtils.isEmpty(result);
			}
		});
	}

	/**
	 * 给集合key刪除多个值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean sRemove(String key, Object... value) {
		long counts = redisTemplate.opsForSet().remove(key, value);
		return counts >= 0 ? true : false;
	}

	/**
	 * 检查集合中是否包含某个元素
	 **/
	public boolean sIsMembers(String key, String value) {
		return redisTemplate.opsForSet().isMember(key, value);
	}

	/**
	 * 所有给定集合的并集
	 * 
	 * @param key1
	 * @param key2
	 * @param destKey
	 * @return
	 */
	public boolean sUnionStore(String key1, String key2, String destKey) {
		long counts = redisTemplate.opsForSet().unionAndStore(key1, key2, destKey);
		return counts >= 0 ? true : false;
	}

	public boolean sUnionStore(String sourceKey, List<String> targetKeys, String destKey) {
		long counts = redisTemplate.opsForSet().unionAndStore(sourceKey, targetKeys, destKey);
		return counts >= 0 ? true : false;
	}

	/**
	 * 所有给定集合之间的差集
	 * 
	 * @param key1
	 * @param key2
	 * @param destKey
	 * @return
	 */
	public boolean sDiffStore(String key1, String key2, String destKey) {
		long counts = redisTemplate.opsForSet().differenceAndStore(key1, key2, destKey);
		return counts >= 0 ? true : false;
	}

	public boolean sDiffStore(String sourceKey, List<String> targetKeys, String destKey) {
		long counts = redisTemplate.opsForSet().differenceAndStore(sourceKey, targetKeys, destKey);
		return counts >= 0 ? true : false;
	}

	/**
	 * 所有给定集合的交集 求指定集合与另一个集合的交集，并且存储到目标集合中
	 * 
	 * @param sourceKey
	 * @param targetKey
	 * @param destKey
	 * @return
	 */
	public boolean sInterStore(String sourceKey, String targetKey, String destKey) {
		long counts = redisTemplate.opsForSet().intersectAndStore(sourceKey, targetKey, destKey);
		return counts >= 0 ? true : false;
	}

	public boolean sInterStore(String sourceKey, List<String> targetKeys, String destKey) {
		long counts = redisTemplate.opsForSet().intersectAndStore(sourceKey, targetKeys, destKey);
		return counts >= 0 ? true : false;
	}

	// 一是keys命令，简单粗暴，由于Redis单线程这一特性，keys命令是以阻塞的方式执行的，keys是以遍历的方式实现的复杂度是 O(n），
	// Redis库中的key越多，查找实现代价越大，产生的阻塞时间越长。
	// 二是scan命令，以非阻塞的方式实现key值的查找，绝大多数情况下是可以替代keys命令的，可选性更强
	/**
	 * 批量zadd
	 * 
	 * @param key
	 * @param score :DefaultTypedTuple
	 * @return
	 */
	public boolean zAdd(String key, String value, double score) {
		return redisTemplate.opsForZSet().add(key, value, score);
	}

	public boolean zAdd(String key, Set<TypedTuple<Object>> typedTuples) {
		long counts = redisTemplate.opsForZSet().add(key, typedTuples);
		return counts >= 0 ? true : false;
	}

	/**
	 * [min,max] 成员的数量
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public long zcount(String key, double min, double max) {
		return redisTemplate.opsForZSet().count(key, min, max);
	}

	public long zCount(String key, Range range) {
		final byte[] rawKey = rawKey(key);
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.zCount(rawKey, range);
			}
		}, true);
	}

	public byte[] rawKey(String key) {
		return redisTemplate.getStringSerializer().serialize(key);
	}

	/**
	 * [min,max]
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<String> zRangeByScore(String key, double min, double max) {
		Set<Object> results = redisTemplate.opsForZSet().rangeByScore(key, min, max);
		return results.stream().map(result -> result.toString()).collect(Collectors.toSet());
	}

	/**
	 * 有序集成员按 score 值递增(从小到大)次序排列 LIMIT 参数指定返回结果的数量
	 * @param key
	 * @param min
	 * @param max
	 * @param offset
	 * @param count
	 * @return
	 */
	public Set<String> zRangeByScore(String key, double min, double max, int offset, int count) {
		Set<Object> results = redisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count);
		return results.stream().map(result -> result.toString()).collect(Collectors.toSet());
	}

	public Set<String> zRangeByScore(String key, Range range) {
		final byte[] rawKey = rawKey(key);
		Set<byte[]> rawValues = redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
			public Set<byte[]> doInRedis(RedisConnection connection) {
				return connection.zRangeByScore(rawKey, range);
			}
		}, true);
		return deserializeValues(rawValues);
	}

	public Set<String> deserializeValues(Set<byte[]> rawValues) {
		Set<Object> objs = SerializationUtils.deserialize(rawValues, serializer);
		return objs.stream().map(obj -> obj.toString()).collect(Collectors.toSet());
	}

	public Set<String> zRangeByScore(String key, Range range, Limit limit) {
		final byte[] rawKey = rawKey(key);
		Set<byte[]> rawValues = redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
			public Set<byte[]> doInRedis(RedisConnection connection) {
				return connection.zRangeByScore(rawKey, range, limit);
			}
		}, true);
		return deserializeValues(rawValues);
	}

	public void hSet(String key, String hashKey, String hashValue) {
		redisTemplate.opsForHash().put(key, hashKey, hashValue);
	}

	public void hSet(String key, Map<String, String> map) {
		redisTemplate.opsForHash().putAll(key, map);
	}

	public String hGet(String key, String hashKey) {
		return Objects.toString(redisTemplate.opsForHash().get(key, hashKey), "");
	}

	public List<String> scanKeys(String patternKey) {
		List<String> keys = new ArrayList<String>();
		Cursor<byte[]> rs = null;
		try {
			ScanOptions options = ScanOptions.scanOptions().count(cursorCounts).match(patternKey).build();
			rs = redisTemplate.getConnectionFactory().getConnection().scan(options);
			while (rs.hasNext()) {
				keys.add(new StringRedisSerializer().deserialize(rs.next()));
			}
			rs.close();
		} finally {
			if (rs != null) {
                rs.close();
            }
		}
		return keys;
	}

	// 根据key正则 获取所有key 以便进行排序后分页
	public List<String> findGrpsByPattern(String patternKey) {
		Set<String> grpSet = redisTemplate.keys(patternKey);
		if (CollectionUtils.isEmpty(grpSet)) {
			return null;
		}
		return new ArrayList<String>(grpSet);
	}
}
