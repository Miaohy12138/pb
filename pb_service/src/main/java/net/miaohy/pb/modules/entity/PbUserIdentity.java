package net.miaohy.pb.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value="BosuserIdentity对象", description="")
public class PbUserIdentity {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String password;

    private Date createTime;

    private Date updateTime;

    private String mobile;

    @ApiModelProperty(value = "1:自己人 2:兼职小编")
    private Integer userType;

    @ApiModelProperty(value = "逻辑删除")
    private Integer isDeleted;

    private String email;


}
