package com.rd.pflog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruand on 2017/4/27.
 * 日志文件管理类
 */

public class LogFileManager {
    //上次删除日期
    private static final String SP_NAME = "logManager";
    private static final String LAST_DELETE_TIME = "last_delete_time";
    private static final String LOG_DIR = "apm_log";//日志目录名
    private static final String BH_LOG_FILE_NAME = "BHLog";//性能日志文件名
    private static String LOG_PATH;//异常日志文件名

    /**
     * 创建日志文件根目录
     *
     * @return
     */
    public static void createLogDir(Context context) {
        String path = context.getFilesDir().getPath() + "/" + LOG_DIR + "/";
        boolean isSDCardExist = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        boolean isRootDirExist = Environment.getExternalStorageDirectory().exists();
        if (isSDCardExist && isRootDirExist) {
            path = context.getExternalFilesDir(null) + "/" + LOG_DIR + "/";
        }
        //创建日志目录
        File dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();
        LOG_PATH = path;
    }

    /**
     * 创建性能日志文件
     */
    public static File createPFLogFile() {
        File file = new File(LOG_PATH + BH_LOG_FILE_NAME + DeviceInfoCollecter.getCurrentDate() + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 获取性能日志
     *
     * @return 文件
     */
    public static List<File> getPFLogFiles() {
        File logRootDir = new File(LOG_PATH);//日志文件根目录
        List<File> keyFiles = new ArrayList<>();
        if (logRootDir.exists()) {
            File[] logFiles = logRootDir.listFiles();
            for (File logFile : logFiles) {
                if (logFile.getName().contains(BH_LOG_FILE_NAME)) {
                    keyFiles.add(logFile);
                }
            }
            return keyFiles;
        }
        return null;
    }

    /**
     * 自动删除
     *
     * @param context  上下文
     * @param delEvery 删除每隔
     */
    public static void autoDelete(Context context, int delEvery) {
        SharedPreferences sp = context
                .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        String lastTime = sp.getString(LAST_DELETE_TIME, "20500101");
        String currentTime = DeviceInfoCollecter.getCurrentDate();
        double duration = DeviceInfoCollecter.getDays(lastTime, currentTime);
        if (duration >= delEvery) {
            File logRootDir = new File(LOG_PATH);//日志文件根目录
            if (logRootDir.exists()) {
                File[] logFiles = logRootDir.listFiles();
                for (File logFile : logFiles) {
                    if (logFile.getName().contains(BH_LOG_FILE_NAME)) {
                        logFile.delete();
                    }
                }

            }
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(LAST_DELETE_TIME, currentTime);
            editor.commit();//提交修改
        }

    }



    public static String readLogFileContent(File file) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "" + sb;
    }

    /**
     * 写文件，从文件开始处写
     *
     * @param sb 写入内容
     * @return 写入成功
     */
    public static boolean write(String sb, File dest) {
        boolean result = false;
        FileChannel dstChannel = null;
        try {
            dstChannel = new FileOutputStream(dest).getChannel();
            dstChannel.write(ByteBuffer.wrap(sb.getBytes()));
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        try {
            dstChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写文件,在文件原内容下添加
     *
     * @param sb 写入内容
     * @return 写入成功
     */
    public static boolean writeAdd(String sb, File dest) {
        boolean result = false;
        FileChannel dstChannel = null;
        try {
            dstChannel = new FileOutputStream(dest, true).getChannel();
            dstChannel.write(ByteBuffer.wrap(sb.getBytes()));
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        try {
            dstChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
