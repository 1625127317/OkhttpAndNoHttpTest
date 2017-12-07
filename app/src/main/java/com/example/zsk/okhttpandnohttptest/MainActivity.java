package com.example.zsk.okhttpandnohttptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button okhttpButton; //测试Okhttp
    private Button nohttpButton;  //测试NoHttp

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        okhttpButton = (Button) findViewById(R.id.button);

        nohttpButton = (Button) findViewById(R.id.button2);
    }
}
