package com.rd.pflog.log;

import java.util.Map;

/**
 * Created by ruand on 2017/5/25.
 * 性能消息收集实体
 */

public interface PFInfoCollector {

    /**
     * 返回性能信息
     *
     * @return 性能信息集合
     */
    Map<String, Object> returnInfo();


}
