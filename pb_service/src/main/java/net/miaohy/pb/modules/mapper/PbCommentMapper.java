package net.miaohy.pb.modules.mapper;

import net.miaohy.pb.modules.entity.PbComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author miaohy
 * @since 2020-05-24
 */
public interface PbCommentMapper extends BaseMapper<PbComment> {

    List<PbComment> getList(@Param("id") Integer id,
                            @Param("type") Integer type,
                            @Param("pageIdx") Integer pageIdx,
                            @Param("pageSize") Integer pageSize);


}
