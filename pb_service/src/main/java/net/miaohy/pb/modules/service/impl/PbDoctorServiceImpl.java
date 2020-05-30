package net.miaohy.pb.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.PbDoctor;
import net.miaohy.pb.modules.entity.item.DoctorItem;
import net.miaohy.pb.modules.mapper.PbDoctorMapper;
import net.miaohy.pb.modules.request.DoctorListRequest;
import net.miaohy.pb.modules.request.GetDoctorDetailRequest;
import net.miaohy.pb.modules.request.GetDoctorListRequest;
import net.miaohy.pb.modules.response.GetDocrorListByNameResponse;
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
        //根据hospitalId  和 departmentId 查询医生列表
        Integer hospitalId = request.getHospitalId();
        Integer departmentId = request.getDepartmentId();
        int pageIdx = request.getPageIdx()*request.getPageSize();
        int pageSize = request.getPageSize();
        List<PbDoctor> doctorlist = this.baseMapper.getList(hospitalId,departmentId,pageIdx,pageSize);
        int totalCount = this.baseMapper.getCount(hospitalId,departmentId);
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

    public Result listByName(DoctorListRequest request) {
        int pageIdx = request.getPageIdx()*request.getPageSize();
        int pageSize = request.getPageSize();
        List<DoctorItem> list = this.baseMapper.getByName(request.getName(),pageIdx,pageSize);

        int totalCount = this.baseMapper.getCountByName(request.getName());
        GetDocrorListByNameResponse response = new GetDocrorListByNameResponse();
        response.setList(list);
        response.setTotalCount(totalCount);
        return Result.ok(response);
    }
}
