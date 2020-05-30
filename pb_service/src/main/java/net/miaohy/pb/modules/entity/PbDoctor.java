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
 * @since 2020-04-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PbDoctor对象", description="")
public class PbDoctor extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "医生等级")
    private Integer level;

    private Integer hospitalId;

    @ApiModelProperty(value = "医院id")
    private Integer departmentId;

    @ApiModelProperty(value = "擅长疾病")
    private String goodDisease;

    private String introduction;

    private String imageUrl;

    private String hospitalName;
}
