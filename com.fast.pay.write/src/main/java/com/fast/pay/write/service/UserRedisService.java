package com.fast.pay.write.service;

import com.fast.pay.write.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRedisService {

    private static final String RAW_KEY = "USER";
    private final RedisTemplate<String, Object> redisTemplate;

    protected String getKeyFrom(String keyword, UUID id) {
        if(null == id) {
            return keyword;
        }
        return keyword.concat(":%s").formatted(id.toString());
    }

    public void put(User user) {
        String key = getKeyFrom("user", user.getUuid());
        log.info("Put user to Redis with key: {}", key);
        redisTemplate.opsForValue().set(key, user, 30, TimeUnit.SECONDS);
    }

    public void putAll(List<User> users) {
        String key = getKeyFrom("all_user", null);
        log.info("Put users to Redis with key: {}", key);
        redisTemplate.opsForValue().set(key, users, 30, TimeUnit.SECONDS);
    }

    public void delete(String key) {
        redisTemplate.opsForValue().getAndDelete(key);
    }

}
