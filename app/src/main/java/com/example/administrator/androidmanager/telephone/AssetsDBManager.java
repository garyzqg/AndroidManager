package com.example.administrator.androidmanager.telephone;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/7/13.
 */
public class AssetsDBManager{

    public void copyAssetsFileToFile(Context context,File file,String path) throws IOException {

        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // context.getAssets()直接获取assets文件夹的内容,所以路径直接写"db/commonnum.db"
            //open()返回一个字节输入流
            inputStream = assetManager.open(path);
            //利用缓冲输入/输出流对文件进行读和写,完成复制本地Assets中的db文件到指定目录中
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(new FileOutputStream(file,false));
            byte[] buff = new byte[1024];
            int len = 0;
            while((len = bis.read(buff)) != -1 ){
                bos.write(buff,0,buff.length);
            }
            bos.flush();

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            bis.close();

            bos.close();
        }


    }


}
