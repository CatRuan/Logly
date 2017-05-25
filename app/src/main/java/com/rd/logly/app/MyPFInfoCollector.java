package com.rd.logly.app;

import com.rd.pflog.log.PFInfoCollector;

import java.util.Map;

/**
 * Created by ruand on 2017/5/25.
 * 性能信息收集器
 */

public class MyPFInfoCollector implements PFInfoCollector {

    private Map<String, Object> mPfInfos;

    public MyPFInfoCollector(Map<String, Object> mPfInfos) {
        this.mPfInfos = mPfInfos;
    }


    @Override
    public Map<String, Object> returnInfo() {

        return mPfInfos;
    }


}
