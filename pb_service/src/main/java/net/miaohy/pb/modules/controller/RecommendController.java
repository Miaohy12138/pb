package net.miaohy.pb.modules.controller;

import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import net.bytebuddy.asm.Advice;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.common.utils.PayUtil;
import net.miaohy.pb.common.vo.PbUserManager;
import net.miaohy.pb.modules.entity.Hospital;
import net.miaohy.pb.modules.entity.PbDoctor;
import net.miaohy.pb.modules.entity.PbOrder;
import net.miaohy.pb.modules.entity.PbUserBasic;
import net.miaohy.pb.modules.entity.item.RecommendItem;
import net.miaohy.pb.modules.entity.item.SourceItem;
import net.miaohy.pb.modules.request.PayRequest;
import net.miaohy.pb.modules.service.impl.HospitalServiceImpl;
import net.miaohy.pb.modules.service.impl.PbDoctorServiceImpl;
import net.miaohy.pb.modules.service.impl.PbOrderServiceImpl;
import net.miaohy.pb.modules.service.impl.PbSourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author : MIAOHY
 * time : 2020/5/26
 */
@Api(tags="推荐接口")
@RestController
@RequestMapping("/pb/recommend")
public class RecommendController {
    @Autowired
    HospitalServiceImpl hospitalService;
    @Autowired
    PbDoctorServiceImpl doctorService;

    @PostMapping("/list")
    public Result pay() {
        PbUserBasic user = PbUserManager.getBosUser();
        List<Hospital> hospitals = hospitalService.getBaseMapper().selectList(new QueryWrapper<>());
        List<PbDoctor> doctors = doctorService.getBaseMapper().selectList(new QueryWrapper<>());
        Random  random1 = new Random(hospitals.size());
        Random  random2 = new Random(doctors.size());
        List<RecommendItem> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RecommendItem recommendItem = new RecommendItem();
            recommendItem.setDoctor( doctors.get(random2.nextInt(doctors.size())));
            recommendItem.setHospital(hospitals.get(random1.nextInt(hospitals.size())));
            list.add(recommendItem);
        }
        return Result.ok(list);
    }
}
