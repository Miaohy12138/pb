package net.miaohy.pb.modules.controller;


import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.request.GetHospitalDetailRequest;
import net.miaohy.pb.modules.request.GetHospitalListRequest;
import net.miaohy.pb.modules.service.impl.HospitalServiceImpl;
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
 * @since 2020-04-19
 */
@RestController
@RequestMapping("/pb/hospital")
public class HospitalController {
    @Autowired
    HospitalServiceImpl hospitalService;

    @PostMapping("list")
    public Result list(@RequestBody GetHospitalListRequest request){
        return hospitalService.getList(request);
    }

    @PostMapping("detail")
    public Result detail(@RequestBody GetHospitalDetailRequest request){
        return hospitalService.detail(request);
    }
}

