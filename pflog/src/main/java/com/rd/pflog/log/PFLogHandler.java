package com.rd.pflog.log;


import android.util.Log;

import com.rd.pflog.DeviceInfoCollecter;
import com.rd.pflog.PFLog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Map;


/**
 * Created by ruand on 2017/5/21.
 * 用户行为日志管理
 */


@Aspect
public class PFLogHandler {
    String startTime;//开始时间


    @Before("call(@com.rd.pflog.log.KeyMethod * *(..))")
    public void beforeAddRecord(JoinPoint joinPoint) {
        Log.i("pflog", "beforeAddRecord");
        startTime = DeviceInfoCollecter.getCurrentTime();//开始时间
    }


    @After("call(@com.rd.pflog.log.KeyMethod * *(..))")
    public void afterAddRecord(JoinPoint joinPoint) {
        Log.i("pflog", "afterAddRecord");
        String endTime = DeviceInfoCollecter.getCurrentTime();//结束时间
        String content = joinPoint.getSignature().toString();//类名+方法名
        int netState = DeviceInfoCollecter.getNetworkState(PFLog.mContext);//获取网络状态
        //String funModule = getKeyData(joinPoint);
        PFInfoCollector pfInfoCollector = PFLog.getInstance().getPFInfoCollecter();
        if (null != pfInfoCollector) {//信息收集器不为空
            Map<String, Object> pfInfos = pfInfoCollector.returnInfo();
            if (null != pfInfos) {//信息不为空
                PFUtils.addPfInfo(startTime, endTime, content, netState, PFUtils.mapToJson(pfInfos));
                pfInfos.clear();
            } else {
                PFUtils.addPfInfo(startTime, endTime, content, netState);
            }
        } else {
            PFUtils.addPfInfo(startTime, endTime, content, netState);
        }

    }


    /**
     * 获取注解值
     *
     * @param joinPoint 切入点
     * @return 注解值
     */
    public static String getKeyData(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

//        if (method != null) {
//            return method.getKeyData(KeyMethod.class).value();
//        }
        return null;
    }

}
