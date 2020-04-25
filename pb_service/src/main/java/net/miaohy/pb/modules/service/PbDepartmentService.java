package net.miaohy.pb.modules.service;

import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.PbDepartment;
import com.baomidou.mybatisplus.extension.service.IService;
import net.miaohy.pb.modules.request.GetDepartmentDetailRequest;
import net.miaohy.pb.modules.request.GetDepartmentListRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miaohy
 * @since 2020-04-23
 */
public interface PbDepartmentService extends IService<PbDepartment> {

    Result getByIds(GetDepartmentListRequest request);

    Result detail(GetDepartmentDetailRequest request);
}
