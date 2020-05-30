package net.miaohy.pb.modules.controller;

import com.alipay.api.AlipayApiException;
import io.swagger.annotations.Api;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.common.utils.PayUtil;
import net.miaohy.pb.modules.entity.PbOrder;
import net.miaohy.pb.modules.entity.item.SourceItem;
import net.miaohy.pb.modules.request.PayRequest;
import net.miaohy.pb.modules.service.impl.PbOrderServiceImpl;
import net.miaohy.pb.modules.service.impl.PbSourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : MIAOHY
 * time : 2020/5/26
 */
@Api(tags="支付相关接口")
@RestController
@RequestMapping("/pb/pay")
public class PayController {
    @Autowired
    PbOrderServiceImpl orderService;
    @Autowired
    PbSourceServiceImpl sourceService;
    @Autowired
    PayUtil payUtil;
    @PostMapping("pay")
    public Result pay(@RequestBody  PayRequest request) throws AlipayApiException {
        int id = request.getId();
        PbOrder order = orderService.getBaseMapper().selectById(id);
        SourceItem sourceItem = sourceService.getBaseMapper().getDetail(order.getSourceId());
        String form = payUtil.alipay(id,order.getAmount(),sourceItem.getHospitalName(),sourceItem.getHospitalName());
        System.out.println(form);
        return Result.ok(form);
    }
}
