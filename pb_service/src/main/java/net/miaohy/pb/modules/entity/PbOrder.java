package net.miaohy.pb.modules.entity;

import java.util.Date;
import net.miaohy.pb.common.vo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author miaohy
 * @since 2020-05-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PbOrder对象", description="")
public class PbOrder extends BaseEntity {

    private static final long serialVersionUID=1L;

    private Integer userId;

    private Float amount;

    private Integer sourceId;

    @ApiModelProperty(value = "0 未支付 1 已支付")
    private Integer isPay;

    private Integer isFirst;

    private String cardId;

    @ApiModelProperty(value = "0 未支付 1 进行中 2 待评价 3 已完成")
    private Integer status;

    private Integer visitId;

}
