package net.miaohy.pb.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.common.vo.PbUserManager;
import net.miaohy.pb.modules.entity.PbComment;
import net.miaohy.pb.modules.entity.PbOrder;
import net.miaohy.pb.modules.mapper.PbCommentMapper;
import net.miaohy.pb.modules.request.EditCommentRequest;
import net.miaohy.pb.modules.request.CommentListRequest;
import net.miaohy.pb.modules.request.GetInfoRequest;
import net.miaohy.pb.modules.service.PbCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miaohy
 * @since 2020-05-24
 */
@Service
public class PbCommentServiceImpl extends ServiceImpl<PbCommentMapper, PbComment> implements PbCommentService {
    @Autowired
    PbOrderServiceImpl orderService;
    @Override
    public Result getList(CommentListRequest request) {
        Integer id = request.getId();
        Integer pageIdx = request.getPageIdx()*request.getPageSize();
        Integer pageSize = request.getPageSize();
        Integer type = request.getType();
        List<PbComment> list = this.baseMapper.getList(id,type,pageIdx,pageSize);
        return Result.ok(list);
    }

    @Override
    public Result edit(EditCommentRequest request) {
        PbComment comment = request.getComment();
        int actionType = request.getActionType();
        //添加
        if(actionType==1){
            comment.setCreateBy(PbUserManager.getBosUser().getNickName());
            this.baseMapper.insert(comment);
            //更新订单信息
            PbOrder order = orderService.getById(comment.getOrderId());
            order.setStatus(3);
            orderService.updateById(order);
        }else if(actionType==2){
            //删除
            this.baseMapper.deleteById(comment.getId());
        }else if(actionType==3){
            //修改
            this.baseMapper.updateById(comment);
        }
        return Result.ok();
    }

    @Override
    public Result getCount(GetInfoRequest request) {
        List<PbComment> pbComments = this.baseMapper.selectList(new QueryWrapper<PbComment>().eq("target_type", request.getType())
                .eq("target_id", request.getId()));
        return Result.ok(pbComments.size());
    }
    @Override
    public Result getRate(GetInfoRequest request) {
        List<PbComment> pbComments = this.baseMapper.selectList(new QueryWrapper<PbComment>().eq("target_type", request.getType())
                .eq("target_id", request.getId()));
        int length = pbComments.size();
        float total = 0;
        for(PbComment comment:pbComments){
            Float rate = comment.getScore();
            total+=rate;
        }
        float averageRate = total/length;

        return Result.ok(averageRate);
    }
}
