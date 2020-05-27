package net.miaohy.pb.modules.service;

import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.PbLike;
import com.baomidou.mybatisplus.extension.service.IService;
import net.miaohy.pb.modules.request.EditLikeRequest;
import net.miaohy.pb.modules.request.GetLikeListRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miaohy
 * @since 2020-05-10
 */
public interface PbLikeService extends IService<PbLike> {

    Result edit(EditLikeRequest request);

    Result getList(GetLikeListRequest request);
}
