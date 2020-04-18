package net.miaohy.pb.modules.service;

import net.miaohy.pb.common.model.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import net.miaohy.pb.modules.entity.PbVisitPerson;
import net.miaohy.pb.modules.request.EditVisitPersonRequest;
import net.miaohy.pb.modules.request.GetVisitPersonDetailRequest;
import net.miaohy.pb.modules.request.GetVisitPersonListRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miaohy
 * @since 2020-04-17
 */
public interface PbVisitPersonService extends IService<PbVisitPerson> {

    Result editPerson(EditVisitPersonRequest request);

    Result getDetail(GetVisitPersonDetailRequest request);

    Result getList(GetVisitPersonListRequest request);
}
