package com.example.zsk.okhttpandnohttptest;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okhttpandnohttptest.greendao_gen.DaoSession;
import com.example.okhttpandnohttptest.greendao_gen.DateEntityDao;
import com.example.zsk.okhttpandnohttptest.greendao.DateEntity;
import com.example.zsk.okhttpandnohttptest.greendao.GreenDaoManager;

import org.greenrobot.greendao.query.QueryBuilder;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.editText)
    EditText userEdit;
    @BindView(R.id.editText2)
    EditText passEdit;
    @BindView(R.id.editText3)
    EditText idEdit;
    @BindView(R.id.editText4)
    EditText findEdit;
    @BindView(R.id.button3)
    Button findButton;
    @BindView(R.id.textView)
    TextView textView;

    private Call call;
    private File file;

    DateEntityDao dateEntityDao;

    private DaoSession daoSession;
    private QueryBuilder<DateEntity> qb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        String path = Environment.getExternalStorageDirectory().toString() + "/ZSK";
        file = new File(path, "test");
        if (!file.exists()) {
            file.mkdirs();
        }
        daoSession = GreenDaoManager.getInstance().getSession();
        dateEntityDao = daoSession.getDateEntityDao();
    }

    @OnClick(R.id.button)
    public void okhttpListener() {
        call = InterClient.getInterClient().upLoadFile();
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                Log.e("请求失败", "请求失败");
            }

            @Override
            public void onResponseUI(Call call, Response response) {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String code = jsonObject.get("code").toString();
                    String message = jsonObject.get("data").toString();
                    String[] address = message.split(":");
                    long totalCount = response.body().contentLength();

                    is = response.body().byteStream();

                    //  is = (InterAPI.ADDRESS + address[1].substring(1)).byteS
                    is = new FileInputStream(InterAPI.ADDRESS + address[1].substring(1));
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    //测试一下
    //测试GreenDao
    @OnClick(R.id.button3)
    public void testGreenDao() {
        String userName = userEdit.getText().toString();
        String passWord = passEdit.getText().toString();
        String id = idEdit.getText().toString();
        qb = dateEntityDao.queryBuilder();
        dateEntityDao.insert(new DateEntity(Long.parseLong(id), userName, passWord));
    }

    int i = 0;

    @OnClick(R.id.button4)
    public void testFindButton() {
        String findStr = findEdit.getText().toString();
        Long findLong = Long.parseLong(findStr);
        List<DateEntity> lists = dateEntityDao.queryBuilder()
                .where(DateEntityDao.Properties.Id.eq(findLong))
                .list();
        for (DateEntity list : lists) {
            Log.e("数据集合：", "第" + (i++) + "个是：" + list.getUsername());
        }
    }

    @OnClick(R.id.button5)
    public void testAppActivity() {
        //启动另一个App中的activiyt
        Intent intent = new Intent();
        String packageName = "com.example.zsk.myapplication";
        String className = "com.example.zsk.myapplication.MainActivity";
        intent.setClassName(packageName, className);
        intent.putExtra("test", "hahah");
        // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            textView.setText(data.getStringExtra("test"));
        }
    }
}

