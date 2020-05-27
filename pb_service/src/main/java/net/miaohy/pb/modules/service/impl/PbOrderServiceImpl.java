package net.miaohy.pb.modules.service.impl;

import com.alipay.api.AbstractAlipayClient;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.common.model.ResultCode;
import net.miaohy.pb.common.vo.PbUserManager;
import net.miaohy.pb.modules.entity.Hospital;
import net.miaohy.pb.modules.entity.PbDoctor;
import net.miaohy.pb.modules.entity.PbOrder;
import net.miaohy.pb.modules.entity.PbSource;
import net.miaohy.pb.modules.entity.item.OrderItem;
import net.miaohy.pb.modules.entity.item.SourceItem;
import net.miaohy.pb.modules.mapper.PbOrderMapper;
import net.miaohy.pb.modules.request.EditOrderRequest;
import net.miaohy.pb.modules.request.GetSourceDetailRequest;
import net.miaohy.pb.modules.request.OrderListRequest;
import net.miaohy.pb.modules.service.PbOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miaohy
 * @since 2020-05-25
 */
@Service
public class PbOrderServiceImpl extends ServiceImpl<PbOrderMapper, PbOrder> implements PbOrderService {
    @Autowired
    PbSourceServiceImpl sourceService;
    @Autowired
    PbDoctorServiceImpl doctorService;
    @Autowired
    HospitalServiceImpl hospitalService;


    public static final String URL = "https://openapi.alipaydev.com/gateway.do";
    public static final String APP_ID = "2016093000631141";
    public static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCvmyEPMQ1J6VIt1n7Gdtbb6aQb+jjSSAkzDQbyUtCmoUujkzUvfz3TbymabLauGC/ShPPYyJja20RP018meNf8rUWJwhx8tXqRGcfwp0qt0ym2VFOBHRTGD0ObYIUln3gziKFEPOJ+RaaDUmRz4DrGw7t0AGFDVu/5cSoAiBjFvgsmy0FHPjOPsyjA4OfEMbkp8uttQTo2whZFsJ+8EuumwKJQW3rDyGYEIg8qD2l6g04MqB8WLpx2sbIO4vBkgW3eMcOK1PryFfBxht/w/qilwAGPEAnNN+DR6H1oOEBaddR1LNWTKIjuSkzP3tzO4FMdSBmO8FX0aWhg6Y1RID2/AgMBAAECggEBAJhsSVy7UfKGsAsTdETl4AdQHF8gaPO1DS8frpjlYzBPLq1T2EVa55o/kdf1ZvdkDRZoypOvQmYtDnVPXfaxy6AJ8iLptvpUN4f83bw4KS0J7dnSBg065c4ob6FNPSTz70yj2n61bk72O9seyAyAY+Qk/u9oaQQfUPqtzCxb9Uyk/qE41z9eqYC/fQnzpc9qqUZSYTENz+S5EyVU26gEeLef4dpau9qX9th4D/TfwNZgc5sBa+v4GTtI/88n1Q22ZvcPKpaEoYtFqPFf1sEjM9FRaYMljJFXepFZbx2HmF7YikXd+llaRNVASHbV590Dz9SakFnUtyjQnARRKEoqwDkCgYEA7x92KLgdWDatY5AWy8g5AVlDV0/lgprSgsSfXvDf4txOSjr6TmA4aRP2/eKloAwbA3Nt17QzmVGEcyUCiHwkUlRPms/y7I95F9AE7s3ttvkWGgs7yBy9Rk7tgArvU3nj6Zqeed5B2cPE+KEp3USid4wC6oE1O4qXWJg4Om/EiWsCgYEAvAAGut7pA1lgy2GK8IdsypYGoVWqhbiGxOSp51G2hWGi5Ci10+cDRBTh1QLZDJtWUBB6B9BrAGTQhoa6OEdJYTJg/QUbCe9RgW7KRAvXHYOdh35u1+xzJsYEWk4On8/dgQgbaQ/j2ktxzaQGHPRR4VgPgRxHsoRRxIRCnQs+Df0CgYARrYTBu+LHe7tTyfo1dIbqV5wH32s7PZDkg/64ORyEG/9Zs4R/uNNSyVwQJupRq7NvjOAI+1R5ODKPhKPsTSkuQPdlnYv6+xxbq1BCC01QopeEs2HnCGDhysH6HlTVGgTEZ17h6ZF6tONh8iAzMHjGV757cbPqw/MuZIp7n3mrIwKBgG0yTrZoF0vctD4tj/gZkPUyovM+wOcewRjIAG/dpHhgt65yfvpv6hO3VVzWZJ/P8hhbGaZERoJaDEy3IJ7juRl9m5wbRRNGMD9PzKgtgGsiMXOr2lIMgu34gjq5sADfSbdSsCkcE6qNu7JIHUzgFFchQRArDvH/rUbJ069IAmk5AoGAbLJmFrJgIi/vpFrxKXRo0xhQCsU51tC9MHBee6+G34G52OL4Gq14wdzMrriKosJT8o1aeqq4QUeMUld3ACbHe90kwbf+ijI/S61CTgBcbOScRBcvlZ7RQz+W9v0zB+cepXtT0ZXL0Sk1MZdBGUIZqeoRvhITaTBfBX4y8g3GuLM=";
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAve/lIaqJwquAySfWYeQDu7WCiwRvolDwMF/996i7gGGx42++zBfT0VrB6OAuB9eS5ljIjAgBPTEPkcSAn3m6VsdkeT6UaSOiOpF1xgwefXlIc1bdvQQKEEjYoxhN4qcEqA1TFBksNsv0YlBwfW9ljIlvXRG74p4XUF5cJ2nVylwq9wrG3WQkbVv0J2zvtt/7tybMpga2TeMi7Q2NZUANclBBqKKQ7L8s8krZrogUEDE9+wzBadsbrxMdNNJ2NIpF2Z96VfwQHeX27t64ZCAq0oMj1Rbr7SQ6teCVgv3FV2ChOh9ZF7UnUry4Lvg+UVdWty27Owa+fRCHEBucDK58cwIDAQAB";


    @Override
    public Result edit(EditOrderRequest request) throws AlipayApiException {
        String form = null;
        int actionType = request.getActionType();
        PbOrder order = request.getOrder();
        //添加
        String name = PbUserManager.getBosUser().getNickName();
        if (actionType == 1) {

            PbOrder uorder = this.baseMapper.selectOne(new QueryWrapper<PbOrder>()
                    .eq("user_id",PbUserManager.getUserId())
                    .eq("source_id",request.getOrder().getSourceId())
            );
            if(uorder!=null){
                return Result.fail(ResultCode.ORDER_EXITS);
            }

            order.setCreateBy(PbUserManager.getBosUser().getNickName());
            order.setCreateTime(new Date());
            order.setUserId(PbUserManager.getUserId());
            order.setStatus(0);
            this.baseMapper.insert(order);
            int id = order.getId();
            PbSource source = sourceService.getBaseMapper().selectById(request.getOrder().getSourceId());
            PbDoctor doctor = doctorService.getBaseMapper().selectById(source.getDoctorId());
            Hospital hospital = hospitalService.getById(source.getHospitalId());
            //支付
            AlipayClient alipayClient = new DefaultAlipayClient(URL,APP_ID,PRIVATE_KEY,"json","utf-8",ALIPAY_PUBLIC_KEY,"RSA2");
            AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
//            //alipayRequest.setReturnUrl("127.0.0.1:8000/pay_result/"+id);
            alipayRequest.setReturnUrl("http://miaohy.natapp1.cc/pay_result");
//            // 在公共参数中设置回跳和通知地址
//            alipayRequest.setNotifyUrl("https://127.0.0.1:3000/");
           // alipayRequest.setReturnUrl("https://xxx.xxx.xxx/open-pay/open-pay/aggregate/pay/QRPay");
            // 在公共参数中设置回跳和通知地址
            //alipayRequest.setNotifyUrl("https://xxx.xxx.xxx/open-pay/alipay/notify_url");
            String out_trade_no = "" +id;

            alipayRequest.setBizContent("{" +
                    " \"out_trade_no\":\"" + out_trade_no + "\"," +
                    " \"total_amount\":\""+request.getOrder().getAmount()+"\"," +
                    " \"subject\":\""+hospital.getName() +" "+doctor.getName()+"\"," +
                    " \"product_code\":\"QUICK_WAP_PAY\"" +
                    " }");//填充业务参数
            // 调用SDK生成表单
             form = alipayClient.pageExecute(alipayRequest).getBody();
            //直接将完整的表单html输出到页面

             form = form.split("<script>")[0];
            System.out.println(form);

            //更新号源
            source.setAmount(source.getAmount()+1);
            sourceService.getBaseMapper().updateById(source);

        } else if (actionType == 2) {
            //删除
            this.baseMapper.deleteById(request.getId());
        } else if (actionType == 3) {
            //修改
            this.baseMapper.updateById(order);
        }
        return Result.ok(form);
    }

    @Override
    public Result getlist(OrderListRequest request) {
        //0 未支付 1 进行中 2待评价 3 已完成
        int type = request.getType();
        List<PbOrder> list = this.baseMapper.selectList(new QueryWrapper<PbOrder>()
        .eq("user_id",PbUserManager.getUserId())
        .eq("status",type)
        );
        List<OrderItem> itemlist = new ArrayList<>();
        for(PbOrder order:list){
            int sourceId = order.getSourceId();
            SourceItem source = sourceService.getBaseMapper().getDetail(sourceId);
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setSourceItem(source);
            itemlist.add(orderItem);
        }
        return Result.ok(itemlist);
    }
}
