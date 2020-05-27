package net.miaohy.pb.modules.mapper;

import net.miaohy.pb.modules.entity.PbSource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.miaohy.pb.modules.entity.item.SourceItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author miaohy
 * @since 2020-05-20
 */
public interface PbSourceMapper extends BaseMapper<PbSource> {

    List<SourceItem>  getlist(@Param("dId") Integer doctorId);

    SourceItem getDetail(Integer id);
}
