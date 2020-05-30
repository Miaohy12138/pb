package net.miaohy.pb.modules.service;

import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.PbComment;
import com.baomidou.mybatisplus.extension.service.IService;
import net.miaohy.pb.modules.request.EditCommentRequest;
import net.miaohy.pb.modules.request.CommentListRequest;
import net.miaohy.pb.modules.request.GetInfoRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miaohy
 * @since 2020-05-24
 */
public interface PbCommentService extends IService<PbComment> {

    Result getList(CommentListRequest request);

    Result edit(EditCommentRequest request);

    Result getCount(GetInfoRequest request);

    Result getRate(GetInfoRequest request);
}
