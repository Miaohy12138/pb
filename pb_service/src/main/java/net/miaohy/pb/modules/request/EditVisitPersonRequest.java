package net.miaohy.pb.modules.request;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbVisitPerson;
/**
 * @author miaohy
 * @since 2020-04-19
 */
@Data
public class EditVisitPersonRequest extends BaseEditRequest{
    private PbVisitPerson visitPerson;
}
