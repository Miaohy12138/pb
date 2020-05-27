package net.miaohy.pb.modules.entity.item;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.miaohy.pb.modules.entity.PbLike;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Author : MIAOHY
 * time : 2020/5/12
 */
@Data
public class LikeItem extends PbLike {
    private String name;
    private int level;
}
