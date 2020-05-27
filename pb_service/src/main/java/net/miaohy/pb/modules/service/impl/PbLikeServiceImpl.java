package net.miaohy.pb.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.swaggerbootstrapui.util.CommonUtils;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.common.vo.PbUserManager;
import net.miaohy.pb.modules.entity.PbLike;
import net.miaohy.pb.modules.entity.item.LikeItem;
import net.miaohy.pb.modules.mapper.PbLikeMapper;
import net.miaohy.pb.modules.request.EditLikeRequest;
import net.miaohy.pb.modules.request.GetLikeListRequest;
import net.miaohy.pb.modules.response.GetLikeListResponse;
import net.miaohy.pb.modules.service.PbLikeService;
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
 * @since 2020-05-10
 */
@Service
public class PbLikeServiceImpl extends ServiceImpl<PbLikeMapper, PbLike> implements PbLikeService {

    @Override
    public Result edit(EditLikeRequest request) {
        int actionType = request.getActionType();
        PbLike like = new PbLike();
        like.setCreateBy(PbUserManager.getBosUser().getNickName());
        like.setTargetType(request.getTargetType());
        like.setTargetId(request.getTargetId());
        like.setUserId(PbUserManager.getUserId());
        like.setCreateTime(new Date());
        //通过数据库查询是否已经收藏
        PbLike pbLike = this.baseMapper.selectOne(new QueryWrapper<PbLike>()
                .eq("target_id",request.getTargetId())
                .eq("user_id",PbUserManager.getUserId()));

        //添加
        if(actionType==1){
            if(pbLike==null){
                this.baseMapper.insert(like);
            }
        }else if(actionType ==2){
        //删除
            this.baseMapper.deleteById(request.getId());
        }
        return Result.ok();
    }

    @Override
    public Result getList(GetLikeListRequest request) {
        int pageIdx = request.getPageIdx() *request.getPageSize();
        int pageSize = request.getPageSize();

        List<LikeItem> pbLikes = this.baseMapper.getList(request.getTargetType(),PbUserManager.getUserId(),pageIdx,pageSize);
        int totalCount = this.baseMapper.getTotalCount(request.getTargetType(),PbUserManager.getUserId());

        GetLikeListResponse response = new GetLikeListResponse();
        response.setLikeList(pbLikes);
        response.setTotalCount(totalCount);
        return Result.ok(response);
    }
}
