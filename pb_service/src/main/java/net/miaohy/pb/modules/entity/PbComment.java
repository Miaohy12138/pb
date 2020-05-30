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
 * @since 2020-05-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PbComment对象", description="")
public class PbComment extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "评价对象id")
    private Integer targetId;

    @ApiModelProperty(value = "0,医院1,医生")
    private Integer targetType;

    private String targetName;

    @ApiModelProperty(value = "评分")
    private Float score;

    private String content;

    private Integer orderId;
}
