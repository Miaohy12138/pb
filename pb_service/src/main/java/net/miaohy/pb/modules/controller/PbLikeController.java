package net.miaohy.pb.modules.controller;


import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.request.EditLikeRequest;
import net.miaohy.pb.modules.request.GetLikeListRequest;
import net.miaohy.pb.modules.service.impl.PbLikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author miaohy
 * @since 2020-05-10
 */
@RestController
@RequestMapping("/pb/like")
public class PbLikeController {
    @Autowired
    private PbLikeServiceImpl pbLikeService;

    @RequestMapping("/edit")
    public Result edit(@RequestBody EditLikeRequest request){
        return  pbLikeService.edit(request);
    }
    @RequestMapping("/list")
    public Result list(@RequestBody GetLikeListRequest request){
        return pbLikeService.getList(request);

    }
}

