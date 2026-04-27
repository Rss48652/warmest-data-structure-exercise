package com.roee.warmest_structure;

import com.roee.warmest_structure.logic.WarmestDataStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class WarmestStructureApplicationTests {

    @Autowired
    private WarmestDataStructure ds;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @BeforeEach
    void setUp() {
        redisTemplate.getConnectionFactory().getConnection().serverCommands().flushAll();
    }

    @Test
    void testWarmestLogic() {
        assertNull(ds.getWarmest());
        assertNull(ds.put("a", 100));
        assertEquals("a", ds.getWarmest());

        assertEquals(100, ds.put("a", 101));
        assertEquals(101, ds.put("a", 101));
        assertEquals(101, ds.get("a"));
        assertEquals("a", ds.getWarmest());

        assertEquals(101, ds.remove("a"));
        assertNull(ds.remove("a"));
        assertNull(ds.getWarmest());

        assertNull(ds.put("a", 100));
        assertNull(ds.put("b", 200));
        assertNull(ds.put("c", 300));
        assertEquals("c", ds.getWarmest());

        assertEquals(200, ds.remove("b"));
        assertEquals("c", ds.getWarmest());
        assertEquals(300, ds.remove("c"));
        assertEquals("a", ds.getWarmest());
        assertEquals(100, ds.remove("a"));
        assertNull(ds.getWarmest());
        assertNull(ds.remove("a"));
    }
}