package com.rd.pflog;

import android.content.Context;

import com.rd.pflog.log.PFInfoCollector;

import java.io.File;
import java.util.List;


/**
 * Created by ruand on 2017/4/25.
 * 性能日志管理器
 * 切面使用三方库https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx
 */

public class PFLog {

    private static PFLog instance = new PFLog();
    public static Context mContext;//上下文
    private int mDelEvery = 7;//删除每隔，默认七天
    private boolean isInited = false;//是否已初始化
    private PFInfoCollector mPFInfoCollecter;//性能消息收集实体

    private PFLog() {
    }

    public static PFLog getInstance() {
        return instance;
    }

    /**
     * 初始化日志管理器
     *
     * @param context 上下文
     * @param days    用户行为日志删除间隔时间
     */
    public void start(Context context, int days) {
        if (!isInited) {
            isInited = true;
            mContext = context;
            if (days > 0 && days < 10) {//删除用户行为日志的时间间隔
                mDelEvery = days;
            }
            LogFileManager.createLogDir(context);//创建日志文件目录
            LogFileManager.autoDelete(context, mDelEvery);//自动删除行为日志
        }

    }

    public List<File> getPFLogFile() {
        return LogFileManager.getPFLogFiles();
    }


    public void setPFInfoCollecter(PFInfoCollector mPFInfoCollecter) {
        this.mPFInfoCollecter = mPFInfoCollecter;
    }

    public PFInfoCollector getPFInfoCollecter() {
        return mPFInfoCollecter;
    }
}
