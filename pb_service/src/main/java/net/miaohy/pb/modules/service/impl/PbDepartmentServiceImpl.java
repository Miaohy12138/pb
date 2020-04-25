package net.miaohy.pb.modules.service.impl;

import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.PbDepartment;
import net.miaohy.pb.modules.mapper.PbDepartmentMapper;
import net.miaohy.pb.modules.request.GetDepartmentListRequest;
import net.miaohy.pb.modules.response.GetDepartmentListResponse;
import net.miaohy.pb.modules.service.PbDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public Result getByIds(GetDepartmentListRequest request) {
        GetDepartmentListResponse response = new GetDepartmentListResponse();
        String ids = request.getIds();
        String [] splits = ids.split(",");
        Map<String,List<PbDepartment>> resultMap = new HashMap<>();
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
        return Result.ok(response);
    }
}
