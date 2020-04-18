package net.miaohy.pb.modules.request;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbUserBasic;

@Data
public class EditUserRequest {
    /**
     * 1、修改
     */
    private int actionType;

    private PbUserBasic userBasic;
}
