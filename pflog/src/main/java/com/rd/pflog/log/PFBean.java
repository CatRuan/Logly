package com.rd.pflog.log;

/**
 * Created by ruand on 2017/4/27.
 * 用户行为实体
 */

public class PFBean {
    //private String model;//模块 登录/待办／审批单／委托
    private String startTime;
    private String endTime;
    private String content;//类名+方法名
    private int netState;//网络状态
    private String jsonData;//用户+业务组件（*）

    public PFBean(String startTime, String endTime, String content, int netState, String jsonData) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.content = content;
        this.netState = netState;
        this.jsonData = jsonData;
    }
}
