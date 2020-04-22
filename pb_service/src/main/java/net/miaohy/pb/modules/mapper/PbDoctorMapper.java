package net.miaohy.pb.modules.mapper;

import net.miaohy.pb.modules.entity.PbDoctor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author miaohy
 * @since 2020-04-20
 */
public interface PbDoctorMapper extends BaseMapper<PbDoctor> {

    List<PbDoctor> getList(@Param("name") String name,@Param("pageIdx") int pageIdx,@Param("pageSize") int pageSize);

    int getCount(@Param("name")String name);
}
