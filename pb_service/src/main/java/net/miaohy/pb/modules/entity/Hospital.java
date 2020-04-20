package net.miaohy.pb.modules.entity;

import java.util.Date;
import net.miaohy.pb.common.vo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-04-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Hospital对象", description="")
public class Hospital extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "院名")
    private String name;

    @ApiModelProperty(value = "医院地址")
    private String address;

    @ApiModelProperty(value = "医院号码")
    private String telephone;

    @ApiModelProperty(value = "医院介绍")
    @TableField("Introduction")
    private String Introduction;

    @ApiModelProperty(value = "医院等级")
    private Integer level;

    @ApiModelProperty(value = "医院图片")
    private String coverUrl;

}
