package net.miaohy.pb.modules.service.impl;

import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.common.vo.PbUserManager;
import net.miaohy.pb.modules.entity.PbUserBasic;
import net.miaohy.pb.modules.mapper.PbUserBasicMapper;
import net.miaohy.pb.modules.request.EditUserRequest;
import net.miaohy.pb.modules.request.RegisterRequest;
import net.miaohy.pb.modules.response.GetUserDeatilResponse;
import net.miaohy.pb.modules.service.PbUserBasicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miaohy
 * @since 2020-04-15
 */
@Service
public class PbUserBasicServiceImpl extends ServiceImpl<PbUserBasicMapper, PbUserBasic> implements PbUserBasicService {

    @Override
    public Result register(RegisterRequest request) {
        //密码MD5加密
        String md5Password = DigestUtils.md5Hex(request.getUserBasic().getPassword());
        request.getUserBasic().setPassword(md5Password);
        //默认性别男1
        int res = this.baseMapper.insert(request.getUserBasic());
        if(res>0){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @Override
    public Result getUserDetail(HttpServletRequest request) {
        GetUserDeatilResponse response = new GetUserDeatilResponse();
        int userId = PbUserManager.getUserId();
        PbUserBasic userBasic = this.baseMapper.selectById(userId);
        response.setUserBasic(userBasic);
        return Result.ok(response);
    }

    @Override
    public Result editUser(EditUserRequest request) {
        PbUserBasic test = PbUserManager.getBosUser();
        int userId = PbUserManager.getUserId();
        PbUserBasic user = request.getUserBasic();
        user.setId(userId);
        user.setUpdateTime(new Date());
        user.setUpdateBy("system");
        //修改
        if(request.getActionType()==1){
            int res = this.baseMapper.updateById(user);
            if(res>0){
                return Result.ok();
            }
        }
        return Result.fail("用户修改失败");
    }
}
