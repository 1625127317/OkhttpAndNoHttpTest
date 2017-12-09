package com.example.zsk.okhttpandnohttptest;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author ZSK
 * @date 2017/12/7
 * @function 封装可以运行在UI线程的CallBack
 */

public abstract class UICallBack implements Callback {

    //拿到主线程的handler
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onFailure(Call call, IOException e) {
        onFailureUI(call,e);
    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        //判断是否响应成功
        if (!response.isSuccessful()) {
            throw  new IOException("error code:"+response.code());
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                onResponseUI(call,response);
            }
        });
    }

    public abstract void onFailureUI(Call call,IOException e);
    public abstract void onResponseUI(Call call,Response Response);
}
