package net.miaohy.pb.modules.request;

import lombok.Data;
import net.miaohy.pb.modules.entity.Hospital;

import java.util.List;
/**
 * @author miaohy
 * @since 2020-04-19
 */
@Data
public class GetHospitalListRequest extends BaseListReqeust {

    /**
     * 医院名字
     */
    private String name;
    private Integer level;
    //private Integer sortBy;
//    /**
//     * 疾病
//     */
//    private String disease;
//    /**
//     * 科室
//     */
//    private String depaerment;
}
