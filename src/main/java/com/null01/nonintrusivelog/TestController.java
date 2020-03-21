package com.null01.nonintrusivelog;

import com.null01.nonintrusivelog.annotation.NILogAround;
import com.null01.nonintrusivelog.annotation.NILogBefore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

//    @NILogAround
    @NILogBefore
    @RequestMapping("/h")
    public String hhh(String i,int e) throws Exception{
        System.err.println("h");
        throw new Exception("jjjj");
//        return "h";
    }
}
