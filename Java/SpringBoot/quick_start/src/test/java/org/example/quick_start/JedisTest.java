package org.example.quick_start;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        // 建立连接
        jedis = new Jedis("127.0.0.0", 6379);
        // 设置密码
        jedis.auth("12345");
        // 选择库
        jedis.select(0);
    }
    @Test
    void testString(){
        String result = jedis.set("name","lxc");
        System.out.println("reslut"+ result);
        String name = jedis.get("name");
        System.out.println("name"+name);
    }
    @AfterEach
    void tearDown(){
        if(jedis != null){
            jedis.close();
        }
    }

}

