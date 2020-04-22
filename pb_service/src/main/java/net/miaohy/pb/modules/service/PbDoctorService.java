package net.miaohy.pb.modules.service;

import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.PbDoctor;
import com.baomidou.mybatisplus.extension.service.IService;
import net.miaohy.pb.modules.request.GetDoctorDetailRequest;
import net.miaohy.pb.modules.request.GetDoctorListRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miaohy
 * @since 2020-04-20
 */
public interface PbDoctorService extends IService<PbDoctor> {

    Result getList(GetDoctorListRequest request);

    Result detail(GetDoctorDetailRequest request);
}
