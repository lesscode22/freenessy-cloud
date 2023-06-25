package cn.starry.freenessy.system;

import cn.starry.freenessy.cache.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    void test() {
        redisService.stringForSet("job", "doctor");
        String val = redisService.stringForGet("job");
        System.out.println("====== " + val);
    }
}
