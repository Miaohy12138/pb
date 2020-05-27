package net.miaohy.pb.modules.service;

import com.alipay.api.AlipayApiException;
import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.PbOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import net.miaohy.pb.modules.request.EditOrderRequest;
import net.miaohy.pb.modules.request.OrderListRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miaohy
 * @since 2020-05-25
 */
public interface PbOrderService extends IService<PbOrder> {

    Result edit(EditOrderRequest request) throws AlipayApiException;

    Result getlist(OrderListRequest request);
}
