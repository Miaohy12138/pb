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
 * @since 2020-04-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PbDepartment对象", description="")
public class PbDepartment extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "科室名称")
    private String name;

    @ApiModelProperty(value = "父级节点")
    private String pid;

    @ApiModelProperty(value = "是否有子节点")
    private String hasChild;

}
