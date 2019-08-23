package com.thok.iem.utils;

public class OwnUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        StackTraceElement[] elements = e.getStackTrace();

        StringBuilder reason =new StringBuilder(e.toString());

        if (elements !=null && elements.length >0) {

            for (StackTraceElement element : elements) {
                reason.append("\n");
                reason.append(element.toString());
            }
        }
        Util.writeFileToSD(reason.toString());
        //Log.e("zyq", reason.toString());
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
