package com.roee.warmest_structure.logic;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class WarmestDataStructure implements WarmestDataStructureInterface {

    private final StringRedisTemplate redisTemplate;
    private static final String DATA_KEY = "warmest_values"; 
    private static final String RANK_KEY = "warmest_scores"; 

    public WarmestDataStructure(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Integer put(String key, int value) {
        Object prev = redisTemplate.opsForHash().get(DATA_KEY, key);
        redisTemplate.opsForHash().put(DATA_KEY, key, String.valueOf(value));
        redisTemplate.opsForZSet().add(RANK_KEY, key, System.currentTimeMillis());
        return prev != null ? Integer.valueOf(prev.toString()) : null;
    }

    @Override
    public Integer get(String key) {
        Object val = redisTemplate.opsForHash().get(DATA_KEY, key);
        if (val != null) {
            redisTemplate.opsForZSet().add(RANK_KEY, key, System.currentTimeMillis());
            return Integer.valueOf(val.toString());
        }
        return null;
    }

    @Override
    public Integer remove(String key) {
        Object prev = redisTemplate.opsForHash().get(DATA_KEY, key);
        if (prev != null) {
            redisTemplate.opsForHash().delete(DATA_KEY, key);
            redisTemplate.opsForZSet().remove(RANK_KEY, key);
            return Integer.valueOf(prev.toString());
        }
        return null;
    }

    @Override
    public String getWarmest() {
        Set<String> result = redisTemplate.opsForZSet().reverseRange(RANK_KEY, 0, 0);
        return (result != null && !result.isEmpty()) ? result.iterator().next() : null;
    }
}