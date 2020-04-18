package net.miaohy.pb.modules.controller;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.miaohy.pb.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@Api("test")
@RestController
@RequestMapping("/modules/bosuser-identity")
public class BosuserIdentityController {

    @Log("测试")
    @ApiOperation("test")
    @GetMapping("/test")
    public void getTest(){
        Date date = new Date();
        String format = DateUtil.format(date, DatePattern.NORM_DATETIME_MS_PATTERN);
        System.out.println(format);
        System.out.println(JSON.toJSONString(date));
    }

}

