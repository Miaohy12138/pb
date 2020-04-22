package net.miaohy.pb.modules.controller;


import net.miaohy.pb.annotation.Log;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.request.EditUserRequest;
import net.miaohy.pb.modules.request.GetUserDetailRequest;
import net.miaohy.pb.modules.request.RegisterRequest;
import net.miaohy.pb.modules.service.impl.PbUserBasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miaohy
 * @since 2020-04-15
 */
@RestController
@RequestMapping("/pb/user")
public class PbUserBasicController {
    @Autowired
    PbUserBasicServiceImpl pbUserBasicService;
    @Log
    @RequestMapping("/register")
    public Result register(@RequestBody RegisterRequest request){
        return pbUserBasicService.register(request);
    }
    @Log
    @RequestMapping("/editUser")
    public Result editUser(@RequestBody EditUserRequest request){
        return pbUserBasicService.editUser(request);
    }
    @Log
    @PostMapping("/userDetail")
    public Result getUserDetail(HttpServletRequest request){
        return pbUserBasicService.getUserDetail(request);
    }
}

