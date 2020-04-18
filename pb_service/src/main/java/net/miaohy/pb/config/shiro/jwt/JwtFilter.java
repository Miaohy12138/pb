/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.miaohy.pb.config.shiro.jwt;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.common.model.ResultCode;
import net.miaohy.pb.common.utils.HttpServletResponseUtil;
import net.miaohy.pb.common.utils.JwtTokenUtil;
import net.miaohy.pb.common.utils.JwtUtil;
import net.miaohy.pb.modules.service.PbUserIdentityService;
import net.miaohy.pb.modules.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Shiro JWT授权过滤器
 **/
@Slf4j
public class JwtFilter extends AuthenticatingFilter {

    private LoginService loginServiceImpl;
    @Autowired
    private PbUserIdentityService pbUserIdentityService;

    private JwtProperties jwtProperties;

    public JwtFilter(LoginService loginServiceImpl, PbUserIdentityService pbUserIdentityService, JwtProperties jwtProperties) {
        this.loginServiceImpl = loginServiceImpl;
        this.pbUserIdentityService = pbUserIdentityService;
        this.jwtProperties = jwtProperties;
    }

    /**
     * 将JWT Token包装成AuthenticationToken
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        log.info("============================JwtFilter AuthenticationToken========================================");
        //添加这行代码，让OPTIONS请求通过

        String token = JwtTokenUtil.getToken();
        if (StringUtils.isBlank(token)) {
            throw new AuthenticationException("token不能为空");
        }
        if (JwtUtil.isExpired(token)) {
            throw new AuthenticationException("JWT Token已过期,token:" + token);
        }

        // 如果开启redis二次校验，或者设置为单个用户token登陆，则先在redis中判断token是否存在
//        if (jwtProperties.isRedisCheck() || jwtProperties.isSingleLogin()) {
//            boolean redisExpired = loginRedisService.exists(token);
//            if (!redisExpired) {
//                throw new AuthenticationException("Redis Token不存在,token:" + token);
//            }
//        }


        //是否开启盐值校验
        String username = JwtUtil.getUsername(token);
        String salt;
        if (jwtProperties.isSaltCheck()){
//            salt = loginRedisService.getSalt(username);
            //TODO   盐值校验  暂时不开始盐值校验
            salt= null;
        }else{
            salt = jwtProperties.getSecret();
        }
        return JwtToken.build(token, username, salt, jwtProperties.getExpireSecond());
    }

    /**
     * 访问失败处理
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        // 返回401
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 设置响应码为401或者直接输出消息
        String url = httpServletRequest.getRequestURI();
        log.error("onAccessDenied url：{}", url);
        Result result = Result.fail(ResultCode.UNAUTHORIZED);
        HttpServletResponseUtil.printJSON(httpServletResponse, result);
        return false;
    }

    /**
     * 判断是否允许访问
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String url = WebUtils.toHttp(request).getRequestURI();
        log.debug("isAccessAllowed url:{}", url);
        if (this.isLoginRequest(request, response)) {
            return true;
        }
        boolean allowed = false;
        try {
            allowed = executeLogin(request, response);
        } catch (IllegalStateException e) { //not found any token
            log.error("Token不能为空", e);
        } catch (Exception e) {
            log.error("访问错误", e);
        }
        return allowed || super.isPermissive(mappedValue);
    }

    /**
     * 登陆成功处理
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        String url = WebUtils.toHttp(request).getRequestURI();
        log.info("========================="+ StrUtil.format("鉴权成功,token:{},url:{}", token, url) +"==================================");
        log.debug("鉴权成功,token:{},url:{}", token, url);
        // 刷新token
        JwtToken jwtToken = (JwtToken) token;
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        //将token设置到response中返回给前端
        loginServiceImpl.refreshToken(jwtToken, httpServletResponse);
        return true;
    }

    /**
     * 登陆失败处理
     *
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.error("登陆失败，token:" + token + ",error:" + e.getMessage(), e);
        return false;
    }
}
