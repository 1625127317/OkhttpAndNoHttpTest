package com.example;

import android.app.Application;

import com.example.zsk.okhttpandnohttptest.greendao.GreenDaoManager;

/**
 * @author ZSK
 * @date 2017/12/9
 * @function
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        GreenDaoManager.getInstance();
    }
}
