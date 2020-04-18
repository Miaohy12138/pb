package net.miaohy.pb.modules.service;

import net.miaohy.pb.config.shiro.jwt.JwtToken;
import net.miaohy.pb.modules.request.LoginRequest;
import net.miaohy.pb.modules.response.LoginResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jinma.xu
 * @since 2020-02-06
 */
public interface LoginService {

    public LoginResponse login(LoginRequest loginRequest);

    public void logout(HttpServletRequest request);

    public void refreshToken(JwtToken jwtToken, HttpServletResponse httpServletResponse);
}
