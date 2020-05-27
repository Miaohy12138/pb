package net.miaohy.pb.modules.entity.item;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbSource;

/**
 * Author : MIAOHY
 * time : 2020/5/20
 */
@Data
public class SourceItem extends PbSource{
    private String hospitalName;
    private String departMentName;
    private String doctorName;
}
