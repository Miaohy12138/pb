package net.miaohy.pb.modules.service.impl;

import com.alipay.api.AbstractAlipayClient;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.common.model.ResultCode;
import net.miaohy.pb.common.utils.PayUtil;
import net.miaohy.pb.common.vo.PbUserManager;
import net.miaohy.pb.modules.entity.*;
import net.miaohy.pb.modules.entity.item.OrderItem;
import net.miaohy.pb.modules.entity.item.SourceItem;
import net.miaohy.pb.modules.mapper.PbOrderMapper;
import net.miaohy.pb.modules.request.EditOrderRequest;
import net.miaohy.pb.modules.request.GetSourceDetailRequest;
import net.miaohy.pb.modules.request.OrderDetailRequest;
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
    @Autowired
    PbVisitPersonServiceImpl visitPersonService;
    @Autowired
    PayUtil payUtil;

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
            form = payUtil.alipay(id,request.getOrder().getAmount(),hospital.getName(),doctor.getName());
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
            //获取号源信息
            SourceItem source = sourceService.getBaseMapper().getDetail(sourceId);
            //获取医生信息
            PbDoctor doctor = doctorService.getById(source.getDoctorId());
            PbVisitPerson visit = visitPersonService.getById(order.getVisitId());
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setSourceItem(source);
            orderItem.setDoctor(doctor);
            orderItem.setVisit(visit);
            itemlist.add(orderItem);
        }
        return Result.ok(itemlist);
    }

    @Override
    public Result detail(OrderDetailRequest request) {
        PbOrder order = this.getById(request.getId());
        OrderItem item = new OrderItem();
        SourceItem source = sourceService.getBaseMapper().getDetail(order.getSourceId());
        PbVisitPerson visit = visitPersonService.getById(order.getVisitId());
        PbDoctor doctor = doctorService.getById(source.getDoctorId());
        item.setVisit(visit);
        item.setOrder(order);
        item.setDoctor(doctor);
        item.setSourceItem(source);
        return Result.ok(item);
    }
}
