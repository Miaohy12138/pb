package net.miaohy.pb.modules.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.PbSource;
import net.miaohy.pb.modules.entity.item.SourceItem;
import net.miaohy.pb.modules.request.GetInfoRequest;
import net.miaohy.pb.modules.request.GetSourceDetailRequest;
import net.miaohy.pb.modules.request.GetSourceListRequest;
import net.miaohy.pb.modules.service.impl.PbSourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miaohy
 * @since 2020-05-20
 */
@RestController
@RequestMapping("/pb/source")
public class PbSourceController {
    @Autowired
    PbSourceServiceImpl pbSourceService;
    @PostMapping("/list")
    public Result list(@RequestBody GetSourceListRequest request){
        return pbSourceService.getListByDid(request);
    }
    @PostMapping("/detail")
    public Result getDetail(@RequestBody GetSourceDetailRequest request){
        return pbSourceService.getDetail(request);
    }
    @PostMapping("/hasSource")
    public Result get(@RequestBody GetInfoRequest request){
        List<SourceItem> source = pbSourceService.getBaseMapper().getlist(request.getId());

        if(source.size()!=0){
            return Result.ok(1);
        }else{
            return Result.ok(0);
        }
    }
}

