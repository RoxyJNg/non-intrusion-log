package com.null01.nonintrusivelog;

import com.null01.nonintrusivelog.annotation.NILogBefore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NonIntrusiveLogApplicationTests {


    @Test
    @NILogBefore
    public void contextLoads() {
        System.err.println("----------test------------");
    }

}
