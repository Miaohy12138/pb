package net.miaohy.pb.modules.controller;


import net.miaohy.pb.annotation.Log;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.request.DoctorListRequest;
import net.miaohy.pb.modules.request.GetDoctorDetailRequest;
import net.miaohy.pb.modules.request.GetDoctorListRequest;
import net.miaohy.pb.modules.service.impl.PbDoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miaohy
 * @since 2020-04-20
 */
@RestController
@RequestMapping("/pb/doctor")
public class PbDoctorController {
    @Autowired
    private PbDoctorServiceImpl pbDoctorService;
    @Log
    @PostMapping("/list")
    public Result list(@RequestBody GetDoctorListRequest request){
        return pbDoctorService.getList(request);
    }
    @PostMapping("/listByName")
    public Result listByName(@RequestBody DoctorListRequest request){
        return pbDoctorService.listByName(request);
    }
    @Log
    @PostMapping("/detail")
    public Result detail(@RequestBody GetDoctorDetailRequest request){
        return pbDoctorService.detail(request);
    }



}

