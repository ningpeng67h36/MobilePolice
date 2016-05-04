package com.monet.yashi.mobilepolice.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/5/4.
 */
public class StreamTools {

    /**
     * 从输入流中获取数据
     * @param inputStream
     * @return byte[] 二进制节数组
     */

    public static byte[] readInputStream(InputStream inputStream) {
        ByteArrayOutputStream outputStream = null;
        try
        {
             outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            return  outputStream.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
