package com.lb.service.impl;

import com.lb.service.IRedisService;
import com.lb.util.JsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private RedisTemplate<String, ?> redisTemplate;

    @Override
    public boolean set(final String key, final String value) throws Exception {
        Assert.hasText(key,"Key is not empty.");
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
            }
        });
        return result;
    }
    @Override
    public String get(final String key) throws Exception {
        Assert.hasText(key,"Key is not empty.");
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value =  connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }
    @Override
    public void del(final String key) throws Exception {
        Assert.hasText(key,"Key is not empty.");

        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection conn) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return conn.del(serializer.serialize(key));
            }
        });
    }



    @Override
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public <T> boolean setList(String key, List<T> list) throws Exception {
        Assert.hasText(key,"Key is not empty.");

        String value = JsonUtil.getJsonString(list);
        return set(key,value);
    }

    @Override
    public <T> List<T> getList(String key,Class<T> clz)  throws Exception{

        Assert.hasText(key,"Key is not empty.");

        String json = get(key);
        if(json!=null){
            List<T> list = JsonUtil.readJson2Array(json,clz);
            return list;
        }
        return null;
    }

    @Override
    public long lpush(final String key, Object obj)throws Exception {
        Assert.hasText(key,"Key is not empty.");

        final String value = JsonUtil.getJsonString(obj);
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));
                return count;
            }
        });
        return result;
    }

    @Override
    public long rpush(final String key, Object obj) throws Exception{
        Assert.hasText(key,"Key is not empty.");

        final String value = JsonUtil.getJsonString(obj);
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));
                return count;
            }
        });
        return result;
    }

    @Override
    public void hmset(String key, Object obj)  throws Exception{
        Assert.hasText(key,"Key is not empty.");

        Map<byte[], byte[]> data=JsonUtil.readJsonByteMap(JsonUtil.getJsonString(obj));
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.hMSet(serializer.serialize(key),data);
                return "";
            }
        });
    }

    @Override
    public <T> T hget(String key, Class<T> clz)  throws Exception{
        Assert.hasText(key,"Key is not empty.");

        return redisTemplate.execute(new RedisCallback<T>() {

            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();

                Map<String,Object> result;

                Map<byte[],byte[]> data=connection.hGetAll(serializer.serialize(key));
                result= new HashMap<>();
                for (Map.Entry<byte[], byte[]> entry: data.entrySet()) {
                    result.put(serializer.deserialize(entry.getKey()),serializer.deserialize(entry.getValue()));
                }

                return JsonUtil.json2Obj(JsonUtil.getJsonString(result),clz);
            }
        });
    }

    @Override
    public<T> List<T>  hmGetAll(String key,Class<T> clz) throws Exception{
        Assert.hasText(key,"Key is not empty.");

        List<Map<String,Object>> dataList= new ArrayList<>();
        return redisTemplate.execute(new RedisCallback<List<T>>() {
            @Override
            public List<T> doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();

                Set<String> keysSet=redisTemplate.keys(key);
                Map<byte[],byte[]> data;
                Map<String,Object> result;
                for(String newKey:keysSet) {
                    data=connection.hGetAll(serializer.serialize(newKey));
                    result= new HashMap<>();
                    for (Map.Entry<byte[], byte[]> entry: data.entrySet()) {
                        result.put(serializer.deserialize(entry.getKey()),serializer.deserialize(entry.getValue()));
                    }
                    dataList.add(result);
                }
                return JsonUtil.readJson2Array(JsonUtil.getJsonString(dataList),clz);
            }
        });
    }

    @Override
    public String lpop(final String key) throws Exception{
        Assert.hasText(key,"Key is not empty.");

        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] res =  connection.lPop(serializer.serialize(key));
                return serializer.deserialize(res);
            }
        });
        return result;
    }
}