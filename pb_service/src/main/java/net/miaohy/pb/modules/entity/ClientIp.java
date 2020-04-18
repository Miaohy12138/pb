package net.miaohy.pb.modules.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.miaohy.pb.common.vo.BaseId;

/**
 * <p>
 * 
 * </p>
 *
 * @author jinma.xu
 * @since 2020-02-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ClientIp对象", description="")
public class ClientIp extends BaseId {

    private static final long serialVersionUID=1L;

    private String clientId;

    private String userId;

    @ApiModelProperty(value = "1:医生 2:病人")
    private Integer userType;

    private String ip;

    private String country;

    private String countryId;

    private String area;

    private String areaId;

    private String region;

    private String regionId;

    private String city;

    private String cityId;

    private String county;

    private String countyId;

    private String isp;

    private String ispId;


}
