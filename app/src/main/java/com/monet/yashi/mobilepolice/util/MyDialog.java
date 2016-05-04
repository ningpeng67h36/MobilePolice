package com.monet.yashi.mobilepolice.util;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/5/4.
 */
public class MyDialog {

    public static void  createDialog (Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", null);
        builder.create().show();
    }

    public static void showMessage(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
