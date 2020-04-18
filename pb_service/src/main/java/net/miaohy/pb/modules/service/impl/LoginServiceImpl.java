package net.miaohy.pb.modules.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import net.miaohy.pb.common.constant.CommonConstant;
import net.miaohy.pb.common.utils.Assert;
import net.miaohy.pb.common.utils.IpUtil;
import net.miaohy.pb.common.utils.JwtTokenUtil;
import net.miaohy.pb.common.utils.JwtUtil;
import net.miaohy.pb.common.vo.PbUserManager;
import net.miaohy.pb.config.shiro.jwt.JwtProperties;
import net.miaohy.pb.config.shiro.jwt.JwtToken;
import net.miaohy.pb.modules.entity.PbUserBasic;
import net.miaohy.pb.modules.entity.PbUserIdentity;
import net.miaohy.pb.modules.request.LoginRequest;
import net.miaohy.pb.modules.response.LoginResponse;
import net.miaohy.pb.modules.service.PbUserIdentityService;
import net.miaohy.pb.modules.service.LoginService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jinma.xu
 * @since 2020-02-06
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private PbUserIdentityService pbUserIdentityServiceImpl;

    @Autowired
    private PbUserBasicServiceImpl pbUserBasicService;
    @Lazy
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录接口
     * @param loginRequest
     * @return
     */
    public LoginResponse login(LoginRequest loginRequest) {
        String name = loginRequest.getName();
        String md5passward = DigestUtils.md5Hex(loginRequest.getPassword());
        List<PbUserBasic> bosusers = pbUserBasicService.lambdaQuery().eq(PbUserBasic::getNickName, name).eq(PbUserBasic::getPassword, md5passward)
                .eq(PbUserBasic::getIsDeleted, 0).list();
        Assert.isCollEmpty(bosusers,"用户名或密码错误");

        // 生成token字符串并返回
        Long expireSecond = jwtProperties.getExpireSecond();
        String token = JwtUtil.generateToken(name, null, Duration.ofSeconds(expireSecond));
        log.debug("token:{}", token);

        // 创建AuthenticationToken
        JwtToken jwtToken = JwtToken.build(token, name, jwtProperties.getSecret(), expireSecond);
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 执行认证登陆
        subject.login(jwtToken);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        //更新userbasic表
        if(CollUtil.isNotEmpty(bosusers)){
            PbUserBasic user = bosusers.get(0);
            //设置IP地址
            String requestIp = IpUtil.getRequestIp();
            user.setIp(requestIp);
            //登录时间
            user.setLoginTime(new Date());
            user.setUpdateBy("system");
            user.setUpdateTime(new Date());
            pbUserBasicService.getBaseMapper().updateById(user);
        }
        return loginResponse;
    }

    @Override
    public void logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
    }

    @Override
    public void refreshToken(JwtToken jwtToken, HttpServletResponse httpServletResponse){
        if (jwtToken == null) {
            return;
        }
        String token = jwtToken.getToken();
        if (StrUtil.isBlank(token)) {
            return;
        }
        // 判断是否刷新token
        boolean isRefreshToken = jwtProperties.isRefreshToken();
        if (!isRefreshToken) {
            return;
        }
        // 获取过期时间
        Date expireDate = JwtUtil.getExpireDate(token);
        System.out.println(DateUtil.format(expireDate, DatePattern.NORM_DATETIME_PATTERN));
        // 获取倒计时
        Integer countdown = jwtProperties.getRefreshTokenCountdown();
        // 如果(当前时间+倒计时) > 过期时间，则刷新token
        System.out.println(DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN));
        boolean refresh = DateUtils.addSeconds(new Date(), countdown).after(expireDate);
        if (!refresh) {
            return;
        }
        // 如果token继续发往后台，则提示，此token已失效，请使用新token，不在返回新token，返回状态码：461
        String username = jwtToken.getUsername();
        String salt = jwtToken.getSalt();
        Long expireSecond = jwtProperties.getExpireSecond();
        // 生成新token字符串
        String newToken = JwtUtil.generateToken(username, salt, Duration.ofSeconds(expireSecond));
        // 设置响应头
        // 刷新token
        httpServletResponse.setStatus(CommonConstant.JWT_REFRESH_TOKEN_CODE);
        httpServletResponse.setHeader(JwtTokenUtil.getTokenName(), newToken);
    }

}
