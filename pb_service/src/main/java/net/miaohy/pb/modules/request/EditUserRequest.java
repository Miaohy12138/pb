package net.miaohy.pb.modules.request;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbUserBasic;
/**
 * @author miaohy
 * @since 2020-04-19
 */
@Data
public class EditUserRequest {
    /**
     * 1、修改
     */
    private int actionType;

    private PbUserBasic userBasic;
}
