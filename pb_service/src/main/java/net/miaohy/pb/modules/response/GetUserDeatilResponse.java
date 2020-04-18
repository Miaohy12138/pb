package net.miaohy.pb.modules.response;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbUserBasic;

@Data
public class GetUserDeatilResponse {
    private PbUserBasic userBasic;
}
