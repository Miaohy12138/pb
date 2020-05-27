package net.miaohy.pb.modules.response;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbLike;
import net.miaohy.pb.modules.entity.item.LikeItem;

import java.util.List;

/**
 * Author : MIAOHY
 * time : 2020/5/10
 */
@Data
public class GetLikeListResponse {
    private List<LikeItem> likeList ;
    private int totalCount;
}
