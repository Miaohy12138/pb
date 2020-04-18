package net.miaohy.pb.modules.service;

import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.PbUserBasic;
import com.baomidou.mybatisplus.extension.service.IService;
import net.miaohy.pb.modules.request.EditUserRequest;
import net.miaohy.pb.modules.request.RegisterRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miaohy
 * @since 2020-04-15
 */
public interface PbUserBasicService extends IService<PbUserBasic> {

    Result register(RegisterRequest request);

    Result getUserDetail(HttpServletRequest request);

    Result editUser(EditUserRequest request);
}
