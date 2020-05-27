package net.miaohy.pb.modules.request;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbComment;

/**
 * Author : MIAOHY
 * time : 2020/5/24
 */
@Data
public class EditCommentRequest extends BaseEditRequest{
    PbComment comment;
}
