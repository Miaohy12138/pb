package net.miaohy.pb.modules.request;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbVisitPerson;

@Data
public class EditVisitPersonRequest {
    /**
     * 1、添加 2、删除 3、修改
     */
    private int actionType;

    private PbVisitPerson visitPerson;

}
