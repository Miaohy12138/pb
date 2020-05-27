package net.miaohy.pb.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.Hospital;
import net.miaohy.pb.modules.mapper.HospitalMapper;
import net.miaohy.pb.modules.request.GetHospitalDetailRequest;
import net.miaohy.pb.modules.request.GetHospitalListRequest;
import net.miaohy.pb.modules.response.GetHospitalDetailResponse;
import net.miaohy.pb.modules.response.GetHospitalListResponse;
import net.miaohy.pb.modules.service.HospitalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author miaohy
 * @since 2020-04-19
 */
@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital> implements HospitalService {

    @Override
    //@Async("taskExecutor")
    public Result getList(GetHospitalListRequest request) {
        int pageIdx = request.getPageIdx()*request.getPageSize();
        int pageSize = request.getPageSize();
        List<Hospital> hospitals = this.baseMapper.getHospitalList(request.getLevel(),request.getName(),pageIdx,pageSize);
        int totalCount =  this.baseMapper.getCountByName(request.getLevel(),request.getName());
        GetHospitalListResponse response = new GetHospitalListResponse();
        response.setHospitals(hospitals);
        response.setTotalCount(totalCount);
        return Result.ok(response);
    }

    @Override
    public Result detail(GetHospitalDetailRequest request) {
        int id = request.getId();
        Hospital hospital = this.baseMapper.selectById(id);
        GetHospitalDetailResponse response = new GetHospitalDetailResponse();
        response.setHospital(hospital);
        return Result.ok(response);
    }
}
