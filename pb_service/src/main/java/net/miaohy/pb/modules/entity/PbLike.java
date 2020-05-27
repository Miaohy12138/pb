package net.miaohy.pb.modules.entity;

import java.util.Date;
import net.miaohy.pb.common.vo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author miaohy
 * @since 2020-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PbLike对象", description="")
public class PbLike extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "收藏id")
    private Integer targetId;

    @ApiModelProperty(value = "1,医院 2,医生")
    private Integer targetType;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

}
