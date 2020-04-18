package net.miaohy.pb.modules.request;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbUserBasic;

@Data
public class RegisterRequest {
    private PbUserBasic userBasic;
}
