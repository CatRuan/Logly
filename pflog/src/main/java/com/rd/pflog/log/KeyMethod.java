package com.rd.pflog.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ruand on 2017/5/21.
 * 关键方法
 */

@Target(ElementType.METHOD)//注解作用域：方法
@Retention(RetentionPolicy.RUNTIME)//生命周期：运行时可存在
//@Retention(RetentionPolicy.CLASS)//生命周期：运行时可存在
@Documented//生成doc时包含注解信息
@Inherited//可以被继承
public @interface KeyMethod {
}
