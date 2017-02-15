package com.cn.util;

import android.content.Context;
import android.os.Process;
import android.util.Log;

/**
 * Created by wuchunhua on 2015/12/21.
 */
public class CnLogUtil {

    public static Context ctx;

    public static void printLogError(String sb) {
        printLog(true, sb);
    }

    public static void printLogInfo(String sb) {
        printLog(false, sb);
    }

    private static void printLog(boolean error, String sb) {

        sb = "Pid(" + Process.myPid() + "), Tid(" + Process.myTid() + "), " + sb;
        if (sb.length() > 4000) {
            int chunkCount = sb.length() / 4000; // integer division
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= sb.length()) {
                    print(error, sb.substring(4000 * i));
                } else {
                    print(error, sb.substring(4000 * i, max));
                }
            }
        } else {
            print(error, sb.toString());
        }
    }

    private static void print(boolean error, String val) {
        if (isLoggble()) {
            String tag = "Logger";
            if (ctx != null) {
                tag = ctx.getPackageName();
            }
            if (error) {
                Log.e(tag, val);
            } else {
                Log.i(tag, val);
            }
        }
    }

    public static boolean isLoggble() {
        return true;
//		return Log.isLoggable("Logger", Log.VERBOSE);
    }

    public static void printStack() {

        boolean DEBUG_PRINT_STACK = true;

        if (DEBUG_PRINT_STACK) {
            Throwable ex = new Throwable();

            StackTraceElement[] stackElements = ex.getStackTrace();
            if (stackElements != null) {
                StringBuffer sb = new StringBuffer();

                sb.append("PID(" + Process.myPid() + "), TID(" + Process.myTid() + ")");
                if (ctx != null) {
                    sb.append("PackageName(" + ctx.getPackageName() + ")");
                }
                sb.append("\n");

                for (int i = 0; i < stackElements.length; i++) {
                    sb.append("文件名:" + stackElements[i].getFileName()
                            + ", 类名:" + stackElements[i].getClassName() + ", 方法名:"
                            + stackElements[i].getMethodName() + ", 行:("
                            + stackElements[i].getLineNumber() + ")\n");
                }
                printLogInfo(sb.toString());
            }
        }
    }
}
