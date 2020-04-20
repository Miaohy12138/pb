package net.miaohy.pb.modules.response;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbUserBasic;
/**
 * @author miaohy
 * @since 2020-04-19
 */
@Data
public class GetUserDeatilResponse {
    private PbUserBasic userBasic;
}
