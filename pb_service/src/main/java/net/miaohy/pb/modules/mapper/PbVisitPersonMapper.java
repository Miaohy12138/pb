package net.miaohy.pb.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.miaohy.pb.modules.entity.PbVisitPerson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author miaohy
 * @since 2020-04-17
 */
public interface PbVisitPersonMapper extends BaseMapper<PbVisitPerson> {
    List<PbVisitPerson> getList(@Param("userId")Integer userId,@Param("pageIdx") int pageIdx,@Param("pageSize") int pageSize);
}
