package net.miaohy.pb.modules.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.miaohy.pb.common.vo.BaseEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="BosuserIdentityVo对象", description="BosuserIdentity返回参数")
public class BosuserIdentityQueryResponse extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @NotNull
    private String userName;

    private String password;

    private String mobile;

    @ApiModelProperty(value = "1:自己人 2:兼职小编")
    private Integer userType;

    @ApiModelProperty(value = "email")
    private String email;


}
