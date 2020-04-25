package net.miaohy.pb.modules.service.impl;

import com.alibaba.fastjson.JSON;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.common.utils.RedisUtil;
import net.miaohy.pb.modules.entity.PbDepartment;
import net.miaohy.pb.modules.mapper.PbDepartmentMapper;
import net.miaohy.pb.modules.request.GetDepartmentDetailRequest;
import net.miaohy.pb.modules.request.GetDepartmentListRequest;
import net.miaohy.pb.modules.response.GetDepartmentDetailResponse;
import net.miaohy.pb.modules.response.GetDepartmentListResponse;
import net.miaohy.pb.modules.service.PbDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miaohy
 * @since 2020-04-23
 */
@Service
public class PbDepartmentServiceImpl extends ServiceImpl<PbDepartmentMapper, PbDepartment> implements PbDepartmentService {
    String rediskey = "HOSPITAL:DEPARTMENTS:";
    @Autowired
    RedisUtil redisUtil;

    @Override
    public Result getByIds(GetDepartmentListRequest request) {
        Map<String,List<PbDepartment>> resultMap = new HashMap<>();;
        GetDepartmentListResponse response = new GetDepartmentListResponse();
        String key = rediskey+request.getHospitalId();
        String redisData = redisUtil.get(key).toString();
        resultMap = JSON.parseObject(redisData,resultMap.getClass());
        if(resultMap.size()!=0){
            response.setDepartments(resultMap);
            return Result.ok(response);
        }

        String ids = request.getIds();
        String [] splits = ids.split(",");

        for(String id :splits){
            String pid = this.getById(Integer.parseInt(id)).getPid();
            if(!resultMap.containsKey(pid)){
                resultMap.put(pid,new ArrayList<PbDepartment>());
            }
        }
        for(String id :splits){
            PbDepartment pb = this.getById(Integer.parseInt(id));
            String pid = pb.getPid();
            List<PbDepartment> departments = resultMap.get(pid);
            departments.add(pb);
        }
        response.setDepartments(resultMap);
        String data = JSON.toJSONString(resultMap);
        redisUtil.set(key,data,-1);
        return Result.ok(response);
    }

    @Override
    public Result detail(GetDepartmentDetailRequest request) {
        PbDepartment pbDepartment = this.baseMapper.selectById(request.getId());
        GetDepartmentDetailResponse response = new GetDepartmentDetailResponse();
        response.setDepartment(pbDepartment);
        return Result.ok(response);
    }
}
