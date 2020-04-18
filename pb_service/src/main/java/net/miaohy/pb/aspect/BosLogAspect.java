package net.miaohy.pb.aspect;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.miaohy.pb.annotation.Log;
import net.miaohy.pb.common.utils.IpUtil;
import net.miaohy.pb.common.vo.PbUserManager;
import net.miaohy.pb.modules.entity.PbLog;
import net.miaohy.pb.modules.entity.PbUserBasic;
import net.miaohy.pb.modules.entity.PbUserIdentity;
import net.miaohy.pb.modules.service.PbLogService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 日志，切面处理类
 * @author xujinma
 */
@Slf4j
@Aspect
@Component
public class BosLogAspect {

    @Autowired
    private PbLogService pbLogServiceImpl;

    @Pointcut("@annotation(net.miaohy.pb.annotation.Log)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        PbLog pbLog = new PbLog();
        Log log = method.getAnnotation(Log.class);
        if(log != null){
            //注解上的描述
            pbLog.setOperation(log.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        pbLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try{
            String params = JSON.toJSONString(args[0]);
            pbLog.setParams(params);
        }catch (Exception e){

        }

        //设置IP地址
        String requestIp = IpUtil.getRequestIp();
        pbLog.setIp(requestIp);

        //用户名
        PbUserBasic user = PbUserManager.getBosUser();
        if(ObjectUtil.isNotNull(user)){
            pbLog.setUserName(user.getNickName());
            pbLog.setUserId(user.getId());
        }
        pbLog.setUpdateTime(new Date());
        pbLog.setCreateTime(new Date());
        //保存系统日志
        pbLogServiceImpl.save(pbLog);
    }

}
