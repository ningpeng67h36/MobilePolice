package com.monet.yashi.mobilepolice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.LinearLayout;

import com.monet.yashi.mobilepolice.adapter.ImageAdapter;
import com.monet.yashi.mobilepolice.util.MyDialog;
import com.monet.yashi.mobilepolice.util.StringTools;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout = null;     //layout布局对象
    private Gallery gallery = null;     //菜单控件
    private ImageAdapter imageAdapter = null; //菜单数据适配器

    private SharedPreferences preferences = null; //默认的应用程序配置文件
    private SharedPreferences.Editor editor = null;
    private Activity activity = null;   //当前的Activity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getApplication().getSharedPreferences("myConfig", Context.MODE_PRIVATE);
        editor = preferences.edit();
        activity = this;
        //登录对话框
        login(this);
    }

    private void login(MainActivity mainActivity) {
        final boolean state = preferences.getBoolean("State", false);     //  记录是否首次登录
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("输入登录密码");
        builder.setIcon(R.drawable.ic_launcher);
        final EditText editText = new EditText(this);
        editText.setHint("第一次登录时输入的密码");
        editText.setSingleLine(true);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD); //设置为文本|密码

        builder.setView(editText);
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() { //登录按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String password = editText.getText().toString();
                TelephonyManager telephonyManager = (TelephonyManager) activity.getApplicationContext()
                        .getSystemService(Context.TELECOM_SERVICE); //

                if (StringTools.isStringEmpty(password)) {  //判断密码是否为空
                    if (!state) //判断是否是第一次登录，如果是设置密码
                    {
                        editor.putString("Password", password);
                        editor.putBoolean("State", true);
                        editor.commit(); //保存提交

                        if (telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY)
                        { //判断手机SIM卡是否正常
                            //记录手机序列号
                            editor.putString("LocalPhone", telephonyManager.getSimSerialNumber());
                        }
                        //初始化界面
                        initView();
                    }
                    else
                    {                             //不是第一次登录
                        if (password.equals(preferences.getString("Password", ""))) //比较password
                        {
                            if (telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY)
                            { //判断手机SIM卡是否正常
                                //记录手机序列号
                                editor.putString("LocalPhone", telephonyManager.getSimSerialNumber());
                                editor.commit();//保存提交
                            }
                            //初始化界面
                            initView();

                        }
                        else
                        {
                            MyDialog.createDialog(activity, "提示", "请输入密码");
                        }
                    }

                }
                else
                {
                    MyDialog.createDialog(activity, "无法登陆", "密码错误");
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.create().show();
    }

    /**
     * 初始化登录成功后页面
     */
    private void initView() {
        setContentView(R.layout.index);
        
    }

}
