package net.miaohy.pb.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import net.miaohy.pb.common.model.ResultCode;
import net.miaohy.pb.exception.PbException;


import java.util.List;

/**
 * 数据校验
 * @author xujinma
 */
public abstract class Assert {

    /**
     * 抛出异常
     * @param message
     */
    public static void throwException(int code, String message) {
        throw new PbException(code,  message);
    }
    /**
     * 字符串是否为空白 空白的定义如下： <br>
     * 1、为null <br>
     * 2、为不可见字符（如空格）<br>
     * 3、""<br>
     *
     * @param str 被检测的字符串
     * @param message 返回消息
     * @return 是否为空
     */
    public static void isBlank(String str, String message) {
        if (StrUtil.isBlank(str)) {
            throw new PbException(ResultCode.PARAMETER_EXCEPTION.getCode(),message);
        }
    }
    
    /**
     * 判断对象为null值
     * @param object
     * @param message
     */
    public static void isNull(Object object, String message) {
        if (ObjectUtil.isNull(object)) {
            throw new PbException(ResultCode.PARAMETER_EXCEPTION.getCode(),message);
        }
    }

    /**
     * 判断对象不为null值
     * @param object
     * @param message
     */
    public static void isNotNull(Object object, String message) {
        if(ObjectUtil.isNotNull(object)){
            throw new PbException(ResultCode.PARAMETER_EXCEPTION.getCode(),message);
        }
    }

    /**
     * 集合是否为空
     *
     * @param list 集合
     * @param message 返回消息
     * @return 是否为空
     */
    public static void isCollEmpty(List<?> list, String message) {
        if(CollUtil.isEmpty(list)){
            throw new PbException(ResultCode.PARAMETER_EXCEPTION.getCode(),message);
        }
    }

    /**
     * 是true
     *
     * @param condition 条件
     * @param message 返回消息
     * @return
     */
    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new PbException(ResultCode.PARAMETER_EXCEPTION.getCode(),message);
        }
    }

    /**
     * 是false
     *
     * @param condition 条件
     * @param message 返回消息
     * @return
     */
    public static void isFalse(boolean condition, String message) {
        if (condition) {
            throw new PbException(ResultCode.PARAMETER_EXCEPTION.getCode(),message);
        }
    }

    /**
     * 大于O
     * @param num
     * @param message 返回消息
     * @return
     */
    public static void gtZero(Integer num, String message) {
        if (num == null || num <= 0) {
            throw new PbException(ResultCode.PARAMETER_EXCEPTION.getCode(),message);
        }
    }

    /**
     * 大于等于O
     * @param num
     * @param message 返回消息
     */
    public static void geZero(Integer num, String message) {
        if (num == null || num < 0) {
            throw new PbException(ResultCode.PARAMETER_EXCEPTION.getCode(),message);
        }
    }

    /**
     * num1大于num2
     * @param num1
     * @param num2
     * @param message 返回消息
     */
    public static void gt(Integer num1, Integer num2, String message) {
        if (num1 <= num2) {
            throw new PbException(ResultCode.PARAMETER_EXCEPTION.getCode(),message);
        }
    }

    /**
     * num1大于等于num2
     * @param num1
     * @param num2
     * @param message 返回消息
     */
    public static void ge(Integer num1, Integer num2, String message) {
        if (num1 < num2) {
            throw new PbException(ResultCode.PARAMETER_EXCEPTION.getCode(),message);
        }
    }

    /**
     * obj1 eq obj2
     * @param obj1
     * @param obj2
     * @param message 返回消息
     */
    public static void eq(Object obj1, Object obj2, String message) {
        if (!obj1.equals(obj2)) {
            throw new PbException(ResultCode.PARAMETER_EXCEPTION.getCode(),message);
        }
    }
}
