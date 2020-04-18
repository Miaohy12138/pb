package net.miaohy.pb.modules.response;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbVisitPerson;

import java.util.List;

@Data
public class GetVisitPersonListResponse {
    private List<PbVisitPerson> visitPersons;
    private int totalCount;
}
