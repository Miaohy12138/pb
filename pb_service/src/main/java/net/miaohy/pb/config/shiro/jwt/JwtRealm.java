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

import lombok.extern.slf4j.Slf4j;
import net.miaohy.pb.modules.entity.PbUserIdentity;
import net.miaohy.pb.modules.service.PbUserIdentityService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Shiro 授权认证
 **/
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    private PbUserIdentityService pbUserIdentityService;//bos的用户表

    public JwtRealm(PbUserIdentityService pbUserIdentityService) {
        this.pbUserIdentityService = pbUserIdentityService;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof JwtToken;
    }

    /**
     * 授权认证,设置角色/权限信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("============================JwtRealm doGetAuthorizationInfo principalCollection========================================");
        // 设置角色/权限信息
        JwtToken jwtToken = (JwtToken) principalCollection.getPrimaryPrincipal();
        // 获取username
        String username = jwtToken.getUsername();
        // 获取登陆用户角色权限信息
        //TODO  获取用户信息 待做
        PbUserIdentity pbUserIdentity = pbUserIdentityService.lambdaQuery().eq(PbUserIdentity::getUserName, username).eq(PbUserIdentity::getIsDeleted, 0).one();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 设置角色
        //TODO 获取角色
//        authorizationInfo.setRoles(SetUtils.hashSet(。。。。));
        // 设置权限
        //TODO 获取权限
//        authorizationInfo.setStringPermissions(。。。。。);
        return authorizationInfo;
    }

    /**
     * 登陆认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("============================JwtRealm doGetAuthenticationInfo authenticationToken========================================");
        // 校验token
        JwtToken jwtToken = (JwtToken) authenticationToken;
        if (jwtToken == null) {
            throw new AuthenticationException("jwtToken不能为空");
        }
        String salt = jwtToken.getSalt();
        if (StringUtils.isBlank(salt)) {
            throw new AuthenticationException("salt不能为空");
        }
        return new SimpleAuthenticationInfo(
                jwtToken,
                salt,
                getName()
        );

    }

}
