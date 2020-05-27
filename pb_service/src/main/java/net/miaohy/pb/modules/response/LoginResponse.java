package net.miaohy.pb.modules.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("登录返回值")
public class LoginResponse {

    private String token;
    private Integer userId;

}
