package net.miaohy.pb.modules.response;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbVisitPerson;

import java.util.List;
/**
 * @author miaohy
 * @since 2020-04-19
 */
@Data
public class GetVisitPersonListResponse {
    private List<PbVisitPerson> visitPersons;
    private int totalCount;
}
