package com.null01.nonintrusivelog;

import com.null01.nonintrusivelog.annotation.NILogAfter;
import com.null01.nonintrusivelog.annotation.NILogAround;
import com.null01.nonintrusivelog.annotation.NILogBefore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @NILogAround
    @RequestMapping("/h")
    public String h() throws Exception{
        System.err.println("h");
        throw new Exception("jjjj");
//        return "h";
    }
}
