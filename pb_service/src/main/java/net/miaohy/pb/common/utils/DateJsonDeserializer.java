package net.miaohy.pb.common.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;


public class DateJsonDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        // 传入的json串为10位.6位的double格式
        double inputQueryTime = p.getDoubleValue();

        // 整数部分精确到秒级别
        inputQueryTime *= 1000;

        // 强转为long型，丢失微妙精度
        long outPutMillseconds = (long) inputQueryTime;

        Date date = new Date(outPutMillseconds);

        // 返回Date类型
        return date;
    }

}
