package com.example.zsk.okhttpandnohttptest.greendao;

import android.database.sqlite.SQLiteDatabase;

import com.example.MyApplication;
import com.example.okhttpandnohttptest.greendao_gen.DaoMaster;
import com.example.okhttpandnohttptest.greendao_gen.DaoSession;

/**
 * @author ZSK
 * @date 2017/12/9
 * @function
 */

public class GreenDaoManager {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private SQLiteDatabase db;
    private static GreenDaoManager mInstance;  //单例

    /**
     * 配置数据库
     */
    private GreenDaoManager() {
        if (mInstance == null) {
            //创建数据库
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.getInstance(),"user.db",null);
            db = helper.getWritableDatabase();
            mDaoMaster = new DaoMaster(db);
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster () {
        return mDaoMaster;
    }

    public DaoSession getSession(){
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
