package net.miaohy.pb.modules.mapper;

import net.miaohy.pb.modules.entity.PbLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.miaohy.pb.modules.entity.item.LikeItem;
import net.miaohy.pb.modules.request.GetLikeListRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author miaohy
 * @since 2020-05-10
 */
public interface PbLikeMapper extends BaseMapper<PbLike> {

    List<LikeItem> getList(@Param("targetType")int targetType,
                           @Param("userId")int userId,
                           @Param("pageIdx")int pageIdx,
                           @Param("pageSize") int pageSize);

    int getTotalCount(@Param("type")Integer targetType,@Param("userId") Integer userId);

}
