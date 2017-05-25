package com.rd.pflog.log;


import android.util.Log;

import com.rd.pflog.LogFileManager;

import java.io.File;
import java.util.Map;

/**
 * Created by ruand on 2017/4/27.
 * 用户行为日志工具类
 */

public class PFUtils {

    private static final String START_TIME = "startTime:";//日志目录名
    private static final String END_TIME = "endTime:";//日志目录名
    private static final String CONTENT = "content:";//日志目录名
    private static final String NET_STATE = "netState:";//日志目录名
    private static final String JSON_DATA = "jsonData:";//日志目录名

    /**
     * 添加基本信息
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param content   类名.方法名
     * @param netState  网络状态
     */
    public static void addPfInfo(final String startTime, final String endTime, final String content
            , final int netState) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuffer sb = new StringBuffer();
                sb.append("\r\n" + "--start--" + "\n");
                sb.append(START_TIME + startTime + ";" + "\n");
                sb.append(END_TIME + endTime + ";" + "\n");
                sb.append(CONTENT + content + ";" + "\n");
                sb.append(NET_STATE + netState + ";" + "\n");
                sb.append(JSON_DATA + "{}" + ";" + "\n");
                sb.append("--end--" + "\n");
                Log.i("pflog", "addPfInfo->" + sb);
                File file = LogFileManager.createPFLogFile();
                LogFileManager.writeAdd(sb + "", file);
            }
        }).start();
    }

    public static void addPfInfo(final String startTime, final String endTime, final String content
            , final int netState, final String customInfos) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuffer sb = new StringBuffer();
                sb.append("--start--" + "\n");
                sb.append(START_TIME + startTime + ";" + "\n");
                sb.append(END_TIME + endTime + ";" + "\n");
                sb.append(CONTENT + content + ";" + "\n");
                sb.append(NET_STATE + netState + ";" + "\n");
                sb.append(JSON_DATA + customInfos + ";" + "\n");
                sb.append("--end--" + "\n");
                Log.i("pflog", "addPfInfo->" + sb);
                File file = LogFileManager.createPFLogFile();
                LogFileManager.writeAdd(sb + "", file);
            }
        }).start();
    }

    public static String mapToJson(Map<String, Object> maps) {
        StringBuffer sb = new StringBuffer();
        int size = maps.size();
        int i = 0;
        sb.append("{");
        for (String key : maps.keySet()) {
            i++;
            sb.append("\"" + key + "\"" + ":" + "\"" + maps.get(key) + "\"");
            if (i != size) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb + "";
    }
}
