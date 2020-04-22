package net.miaohy.pb.modules.service.impl;

import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.PbDoctor;
import net.miaohy.pb.modules.mapper.PbDoctorMapper;
import net.miaohy.pb.modules.request.GetDoctorDetailRequest;
import net.miaohy.pb.modules.request.GetDoctorListRequest;
import net.miaohy.pb.modules.response.GetDoctorDetailResponse;
import net.miaohy.pb.modules.response.GetDoctorListResponse;
import net.miaohy.pb.modules.service.PbDoctorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miaohy
 * @since 2020-04-20
 */
@Service
public class PbDoctorServiceImpl extends ServiceImpl<PbDoctorMapper, PbDoctor> implements PbDoctorService {

    @Override
    public Result getList(GetDoctorListRequest request) {
        String name = request.getName();
        int pageIdx = request.getPageIdx()*request.getPageSize();
        int pageSize = request.getPageSize();
        List<PbDoctor> doctorlist = this.baseMapper.getList(name,pageIdx,pageSize);
        int totalCount = this.baseMapper.getCount(name);
        GetDoctorListResponse response = new GetDoctorListResponse();
        response.setDoctors(doctorlist);
        response.setTotalCount(totalCount);
        return Result.ok(response);
    }

    @Override
    public Result detail(GetDoctorDetailRequest request) {
        int id = request.getId();
        PbDoctor pbdoctor = this.baseMapper.selectById(id);
        GetDoctorDetailResponse response =new GetDoctorDetailResponse();
        response.setDoctor(pbdoctor);
        return Result.ok(response);
    }
}
