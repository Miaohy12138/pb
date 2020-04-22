package net.miaohy.pb.modules.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.miaohy.pb.common.vo.BaseId;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="BosLog对象", description="")
public class PbLog extends BaseId {

    private static final long serialVersionUID=1L;
    @TableField("userName")
    @ApiModelProperty(value = "账号")
    private String userName;
    @TableField("userId")
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "操作")
    private String operation;

    @ApiModelProperty(value = "方法")
    private String method;

    @ApiModelProperty(value = "参数")
    private String params;

    @ApiModelProperty(value = "ip")
    private String ip;


}
