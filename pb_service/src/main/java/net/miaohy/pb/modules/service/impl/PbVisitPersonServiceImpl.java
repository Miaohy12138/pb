package net.miaohy.pb.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.common.vo.PbUserManager;
import net.miaohy.pb.modules.entity.PbVisitPerson;
import net.miaohy.pb.modules.mapper.PbVisitPersonMapper;
import net.miaohy.pb.modules.request.EditVisitPersonRequest;
import net.miaohy.pb.modules.request.GetVisitPersonDetailRequest;
import net.miaohy.pb.modules.request.GetVisitPersonListRequest;
import net.miaohy.pb.modules.response.GetVisitPersonDetailResponse;
import net.miaohy.pb.modules.response.GetVisitPersonListResponse;
import net.miaohy.pb.modules.service.PbVisitPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miaohy
 * @since 2020-04-17
 */
@Service
public class PbVisitPersonServiceImpl extends ServiceImpl<PbVisitPersonMapper, PbVisitPerson> implements PbVisitPersonService {

    @Override
    public Result editPerson(EditVisitPersonRequest request) {
        // 1、添加 2、删除 3、修改
        int actionType = request.getActionType();
        if(actionType==1){
            PbVisitPerson visitPerson = request.getVisitPerson();
            visitPerson.setCreateBy(PbUserManager.getBosUser().getNickName());
            visitPerson.setUserId(PbUserManager.getUserId());
            visitPerson.setCreateTime(new Date());
            this.baseMapper.insert(visitPerson);
            return Result.ok();
        }else if(actionType==2){
            PbVisitPerson visitPerson = request.getVisitPerson();
            visitPerson.setUpdateBy(PbUserManager.getBosUser().getNickName());
            visitPerson.setIsDeleted(1);
            this.baseMapper.deleteById(visitPerson.getId());
            return Result.ok();
        }else if(actionType==3){
            PbVisitPerson visitPerson = request.getVisitPerson();
            visitPerson.setUpdateBy(PbUserManager.getBosUser().getNickName());
            visitPerson.setUpdateTime(new Date());
            this.baseMapper.updateById(visitPerson);
            return Result.ok();
        }
        return Result.fail("actionType 错误");
    }

    @Override
    public Result getDetail(GetVisitPersonDetailRequest request) {
        GetVisitPersonDetailResponse response = new GetVisitPersonDetailResponse();
        PbVisitPerson visitPerson = this.baseMapper.selectById(request.getId());
        if(visitPerson!=null){
            response.setVisitPerson(visitPerson);
        }
        return Result.ok(response);
    }

    @Override
    public Result getList(GetVisitPersonListRequest request) {
        Integer userId = PbUserManager.getUserId();
        GetVisitPersonListResponse response = new GetVisitPersonListResponse();
        int pageIdx = request.getPageIdx()*request.getPageSize();
        int pageSize = request.getPageSize();
        int totalCount = this.baseMapper.selectCount(new QueryWrapper<PbVisitPerson>().eq("user_id",userId).eq("is_deleted",0));
        List<PbVisitPerson>  pbVisitPersonList = this.baseMapper.getList(userId,pageIdx,pageSize);
        response.setTotalCount(totalCount);
        response.setVisitPersons(pbVisitPersonList);
        return Result.ok(response);
    }
}
