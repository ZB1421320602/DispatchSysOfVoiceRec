package com.telecom.ccs.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RedisOps {

    @Autowired
    private  RedisTemplate<String,String> redisTemplate;

    private static RedisTemplate<String,String> myredisTemplet;

    @PostConstruct
    public void init() {
        myredisTemplet = redisTemplate;
    }

    public static String  get(String key){
        ValueOperations<String,String> operations = myredisTemplet.opsForValue();
        return  operations.get(key);
    }

    public static void  set(String key,String value){
        ValueOperations<String,String> operations = myredisTemplet.opsForValue();
        operations.set(key, value);
    }


    public static  long  leftPush(String queue,String value){
        ListOperations<String,String> operations = myredisTemplet.opsForList();
        return  operations.leftPush(queue,value);
    }

    public static String  rightPop(String queue){
        ListOperations<String,String> operations = myredisTemplet.opsForList();
        return  operations.rightPop(queue);
    }

}
