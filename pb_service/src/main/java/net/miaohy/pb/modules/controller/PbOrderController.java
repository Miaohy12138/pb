package net.miaohy.pb.modules.controller;


import com.alipay.api.AlipayApiException;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.request.EditOrderRequest;
import net.miaohy.pb.modules.request.OrderDetailRequest;
import net.miaohy.pb.modules.request.OrderListRequest;
import net.miaohy.pb.modules.service.impl.PbOrderServiceImpl;
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
 * @since 2020-05-25
 */
@RestController
@RequestMapping("/pb/order")
public class PbOrderController {
    @Autowired
    private PbOrderServiceImpl orderService;
    @PostMapping("/edit")
    public Result edit(@RequestBody EditOrderRequest request) throws AlipayApiException {
        return orderService.edit(request);
    }
    @PostMapping("/list")
    public Result list(@RequestBody OrderListRequest request){
        return orderService.getlist(request);
    }
    @PostMapping("/detail")
    public Result list(@RequestBody OrderDetailRequest request){
        return orderService.detail(request);
    }
}

