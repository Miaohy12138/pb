package net.miaohy.pb.modules.controller;


import net.miaohy.pb.annotation.Log;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.request.EditVisitPersonRequest;
import net.miaohy.pb.modules.request.GetVisitPersonDetailRequest;
import net.miaohy.pb.modules.request.GetVisitPersonListRequest;
import net.miaohy.pb.modules.response.GetVisitPersonDetailResponse;
import net.miaohy.pb.modules.service.impl.PbVisitPersonServiceImpl;
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
 * @since 2020-04-17
 */
@RestController
@RequestMapping("/pb/visitPerson")
public class PbVisitPersonController {

    @Autowired
    PbVisitPersonServiceImpl visitPersonService;
    @Log
    @PostMapping("/edit")
    public Result editPerson(@RequestBody EditVisitPersonRequest request){
        return visitPersonService.editPerson(request);
    }
    @Log
    @PostMapping("/detail")
    public Result getDetail(@RequestBody GetVisitPersonDetailRequest request){
        return visitPersonService.getDetail(request);

    }
    @Log
    @PostMapping("/list")
    public Result list(@RequestBody GetVisitPersonListRequest request){
        return visitPersonService.getList(request);
    }

}

