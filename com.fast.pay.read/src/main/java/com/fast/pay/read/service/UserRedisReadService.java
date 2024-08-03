package com.fast.pay.read.service;

import com.fast.pay.read.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRedisReadService {

    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    protected String getKeyFrom(String keyword, UUID id) {
        if(null == id) {
            return keyword;
        }
        return keyword.concat(":%s").formatted(id.toString());
    }

    public User getById(UUID id) {
        String key = getKeyFrom("user", id);
        log.info("GET user from Redis with key: {}", key);
        Object obj = redisTemplate.opsForValue().get(key);

        return objectMapper.convertValue(obj, new TypeReference<User>() {
        });
    }

    public List<User> getAll() {
        String key = getKeyFrom("all_user", null);
        log.info("GET users from Redis with key: {}", key);
        Object obj = redisTemplate.opsForValue().get(key);

        return objectMapper.convertValue(obj,  new TypeReference<List<User>>() {
        });
    }

}
