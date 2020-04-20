package net.miaohy.pb.modules.service;

import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.Hospital;
import com.baomidou.mybatisplus.extension.service.IService;
import net.miaohy.pb.modules.request.GetHospitalDetailRequest;
import net.miaohy.pb.modules.request.GetHospitalListRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miaohy
 * @since 2020-04-19
 */
public interface HospitalService extends IService<Hospital> {

    Result getList(GetHospitalListRequest request);

    Result detail(GetHospitalDetailRequest request);
}
