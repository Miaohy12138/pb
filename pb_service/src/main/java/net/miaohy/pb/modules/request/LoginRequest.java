package net.miaohy.pb.modules.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("登录参数")
public class LoginRequest {

    @NotBlank(message = "请输入用户名")
    @ApiModelProperty("用户名")
    private String name;

    @NotBlank(message = "请输入密码")
    @ApiModelProperty("密码")
    private String password;

}
