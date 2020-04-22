package net.miaohy.pb.modules.mapper;

import net.miaohy.pb.modules.entity.Hospital;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author miaohy
 * @since 2020-04-19
 */
public interface HospitalMapper extends BaseMapper<Hospital> {

    List<Hospital> getHospitalList(@Param("level") int level,@Param("name") String name,@Param("pageIdx")int pageIdx, @Param("pageSize")int pageSize);

    int getCountByName(@Param("level") int level,@Param("name")String name);
}
