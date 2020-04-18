package net.miaohy.pb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.java.Log;
import net.miaohy.pb.common.utils.JwtTokenUtil;
import net.miaohy.pb.common.utils.JwtUtil;
import net.miaohy.pb.common.vo.PbUserManager;
import net.miaohy.pb.modules.entity.PbUserBasic;
import net.miaohy.pb.modules.entity.PbUserIdentity;
import net.miaohy.pb.modules.service.PbUserIdentityService;
import net.miaohy.pb.modules.service.impl.PbUserBasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 权限(Token)验证
 *
 * @author xujinma
 */
@Log
@Slf4j
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PbUserIdentityService pbUserIdentityService;
    @Autowired
    private PbUserBasicServiceImpl pbUserBasicService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("============================AuthorizationInterceptor preHandle========================================");
        //从头信息中获取token
        String token = JwtTokenUtil.getToken(request);
//        String token = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        List<PbUserBasic> pbUserIdentities = pbUserBasicService.lambdaQuery().eq(PbUserBasic::getNickName, username).eq(PbUserBasic::getIsDeleted, 0).list();
        if(CollUtil.isNotEmpty(pbUserIdentities)){
            PbUserManager.setBosUser(pbUserIdentities.get(0));
        }
        return super.preHandle(request, response, handler);
    }
}
