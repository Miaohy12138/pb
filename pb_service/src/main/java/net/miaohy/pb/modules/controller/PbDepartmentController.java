package net.miaohy.pb.modules.controller;


import net.miaohy.pb.annotation.Log;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.request.GetDepartmentListRequest;
import net.miaohy.pb.modules.service.impl.PbDepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RelationSupport;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miaohy
 * @since 2020-04-23
 */
@RestController
@RequestMapping("/pb/department")
public class PbDepartmentController {
    @Autowired
    private PbDepartmentServiceImpl pbDepartmentService;
    @Log
    @PostMapping("getByIds")
    public Result getByIds(@RequestBody  GetDepartmentListRequest request){
        return  pbDepartmentService.getByIds(request);
    }

}

