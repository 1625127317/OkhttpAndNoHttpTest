package com.example.zsk.okhttpandnohttptest;

import android.util.Log;

import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author ZSK
 * @date 2017/12/7
 * @function 网络模块客户端
 */

public class InterClient {

    private static InterClient interClient;

    private Gson gson;

    private OkHttpClient okHttpClient;

    private InterClient() {
        gson = new Gson();
        okHttpClient = new OkHttpClient();
    }

    public static InterClient getInterClient() {
        if (interClient == null) {
            interClient = new InterClient();
        }
        return interClient;
    }

    //下载文件
    public Call upLoadFile() {
        Log.e("请求地址:",InterAPI.ADDRESS);
        RequestBody requestBody = new FormBody.Builder()
                .add("imei",InterAPI.PARMERA)
                .build();
        Request request = new Request.Builder()
                .url(InterAPI.ADDRESS)
                .post(requestBody)
                .build();
        return okHttpClient.newCall(request);
    }
}
