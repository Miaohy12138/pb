
package net.miaohy.pb.common.model;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * REST API 返回结果
 * </p>
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class Result<T> implements Serializable {
    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time =new Date();

    public Result() {

    }

    public static Result result(boolean flag){
        if (flag){
            return ok();
        }
        return fail();
    }

    public static Result result(ResultCode resultCode){
        return result(resultCode,null);
    }

    public static Result result(ResultCode resultCode, Object data){
        return result(resultCode,null,data);
    }

    public static Result result(ResultCode resultCode, String msg, Object data){
        boolean success = false;
        if (resultCode.getCode() == ResultCode.SUCCESS.getCode()){
            success = true;
        }
        String message = resultCode.getMsg();
        if (StrUtil.isNotBlank(msg)){
            message = msg;
        }
        return Result.builder()
                .code(resultCode.getCode())
                .msg(message)
                .data(data)
                .success(success)
                .time(new Date())
                .build();
    }

    public static Result ok(){
        return ok(null);
    }

    public static Result ok(Object data){
        return result(ResultCode.SUCCESS,data);
    }

    public static Result ok(Object data, String msg){
        return result(ResultCode.SUCCESS,msg,data);
    }

    public static Result okMap(String key, Object value){
        Map<String,Object> map = new HashMap<>();
        map.put(key,value);
        return ok(map);
    }

    public static Result fail(ResultCode resultCode){
        return result(resultCode,null);
    }

    public static Result fail(String msg){
        return result(ResultCode.FAIL,msg,null);

    }

    public static Result fail(ResultCode resultCode, Object data){
        if (ResultCode.SUCCESS == resultCode){
            throw new RuntimeException("失败结果状态码不能为" + ResultCode.SUCCESS.getCode());
        }
        return result(resultCode,data);

    }

    public static Result fail(String key, Object value){
        Map<String,Object> map = new HashMap<>();
        map.put(key,value);
        return result(ResultCode.FAIL,map);
    }

    public static Result fail() {
        return fail(ResultCode.FAIL);
    }
}