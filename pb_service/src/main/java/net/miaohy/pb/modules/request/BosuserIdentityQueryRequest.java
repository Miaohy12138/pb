package net.miaohy.pb.modules.request;

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

/**
 * <p>
 * 
 * </p>
 *
 * @author jinma.xu
 * @since 2020-02-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="BosuserIdentityQuery对象", description="BosuserIdentity查询参数")
public class BosuserIdentityQueryRequest extends BaseEntity {

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
