package net.miaohy.pb.modules.response;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbVisitPerson;

@Data
public class GetVisitPersonDetailResponse {
    private PbVisitPerson visitPerson;
}