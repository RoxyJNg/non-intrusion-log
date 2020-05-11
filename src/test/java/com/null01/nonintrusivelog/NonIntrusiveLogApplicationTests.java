package com.null01.nonintrusivelog;

import com.null01.nonintrusivelog.annotation.NILogBefore;
import com.null01.nonintrusivelog.resolver.JsonResolver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NonIntrusiveLogApplicationTests {


    @Test
    @NILogBefore
    public void contextLoads() throws Exception{
//        String before = JsonResolver.getBefore();
//        System.err.println(before);
    }

}
