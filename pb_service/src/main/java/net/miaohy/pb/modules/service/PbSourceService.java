package net.miaohy.pb.modules.service;

import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.PbSource;
import com.baomidou.mybatisplus.extension.service.IService;
import net.miaohy.pb.modules.request.GetSourceDetailRequest;
import net.miaohy.pb.modules.request.GetSourceListRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miaohy
 * @since 2020-05-20
 */
public interface PbSourceService extends IService<PbSource> {

    Result getListByDid(GetSourceListRequest request);

    Result getDetail(GetSourceDetailRequest request);
}
