package net.miaohy.pb.modules.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.miaohy.pb.annotation.Log;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.common.model.ResultCode;
import net.miaohy.pb.common.utils.JwtTokenUtil;
import net.miaohy.pb.modules.request.LoginRequest;
import net.miaohy.pb.modules.response.LoginResponse;
import net.miaohy.pb.modules.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(tags="登录登出相关接口")
@RestController
@RequestMapping("/pb/user")
public class LoginController {

    @Autowired
    private LoginService loginServiceImpl;

    @Log
    @PostMapping("/login")
    @ApiOperation(value = "登陆", notes = "bos用户登陆", response = LoginResponse.class)
    public Result login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response){
        LoginResponse loginResponse = loginServiceImpl.login(loginRequest);
        if(loginResponse==null){
            return Result.fail(ResultCode.LOGIN_EXCEPTION,"用户名或密码错误");
        }else{
            // 设置token响应头
            response.setHeader(JwtTokenUtil.getTokenName(), loginResponse.getToken());
            return Result.ok(loginResponse, "登陆成功");
        }
    }

    @Log
    @PostMapping("/logout")
    @ApiOperation(value = "退出", notes = "bos用户退出", response = LoginResponse.class)
    public Result logout(HttpServletRequest request) throws Exception {
        loginServiceImpl.logout(request);
        return Result.ok("退出成功");
    }


}
