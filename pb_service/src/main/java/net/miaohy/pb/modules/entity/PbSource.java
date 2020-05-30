package net.miaohy.pb.modules.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.miaohy.pb.common.vo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author miaohy
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PbSource对象", description="")
public class PbSource extends BaseEntity {

    private static final long serialVersionUID=1L;

    private String hospitalId;

    private String doctorId;

    private String departmentId;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "日期")
    private Date time;

    @ApiModelProperty(value = "1、上午 2、下午 0 全天")
    private String day;

    @ApiModelProperty(value = "0、初始态 1、发布 2、占用  ")
    private Integer status;

    private Integer total;

    private Integer amount;

    private Double price;




}
