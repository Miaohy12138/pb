package net.miaohy.pb.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.miaohy.pb.common.constant.CommonConstant;

import java.io.Serializable;

/**
 * 分页参数
 *
 * @author jinma.xu
 * @since 2020-2-5
 */
@Data
@ApiModel("查询参数对象")
public class PageParam implements Serializable {

    @ApiModelProperty(value = "页码,默认为1", example = "1")
    private Integer current = CommonConstant.DEFAULT_PAGE_INDEX;
    @ApiModelProperty(value = "页大小,默认为10", example = "10")
    private Integer size = CommonConstant.DEFAULT_PAGE_SIZE;

    public void setCurrent(Integer current) {
        if (current == null || current <= 0) {
            this.current = CommonConstant.DEFAULT_PAGE_INDEX;
        } else {
            this.current = current;
        }
    }

    public void setSize(Integer size) {
        if (size == null || size <= 0) {
            this.size = CommonConstant.DEFAULT_PAGE_SIZE;
        } else {
            this.size = size;
        }
    }

}
