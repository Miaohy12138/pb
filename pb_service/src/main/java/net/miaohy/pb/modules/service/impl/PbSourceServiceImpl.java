package net.miaohy.pb.modules.service.impl;

import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.entity.PbSource;
import net.miaohy.pb.modules.entity.item.SourceItem;
import net.miaohy.pb.modules.mapper.PbSourceMapper;
import net.miaohy.pb.modules.request.GetSourceDetailRequest;
import net.miaohy.pb.modules.request.GetSourceListRequest;
import net.miaohy.pb.modules.service.PbSourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miaohy
 * @since 2020-05-20
 */
@Service
public class PbSourceServiceImpl extends ServiceImpl<PbSourceMapper, PbSource> implements PbSourceService {

    @Override
    public Result getListByDid(GetSourceListRequest request) {
        List<SourceItem> itemList = this.baseMapper.getlist(request.getDoctorId());
        return Result.ok(itemList);
    }

    @Override
    public Result getDetail(GetSourceDetailRequest request) {
        SourceItem item = this.baseMapper.getDetail(request.getId());
        return Result.ok(item);
    }
}
