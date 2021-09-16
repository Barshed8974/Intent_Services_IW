package com.example.intentservices;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class DownloadServices extends IntentService {
    public DownloadServices() {
        super("async");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Ali","onCreate");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        downloadFile();
        Log.d("Ali","onHandleIntent");

    }
    private void downloadFile()
    {
        try {
            File directory=new File(getFilesDir()+File.separator+"vogas");
            if(!directory.exists())
            {
                directory.mkdir();
            }
            File file=new File(directory,"vogas.html");
            if(!file.exists())
            {
                file.createNewFile();
            }

            URL url=new URL("https://www.vogella.com/index.html");

            InputStream inputStream=url.openConnection().getInputStream();

            InputStreamReader reader=new InputStreamReader(inputStream);
            FileOutputStream write=new FileOutputStream(file,true);
            int data= reader.read();
            while(data!=-1)
            {
                char c=(char) data;
                write.write(c);
                data=reader.read();
            }
            FileInputStream fileInputStream=new FileInputStream(file);
            int fileData=fileInputStream.read();
            StringBuffer stringBuffer=new StringBuffer();
            while (fileData!=-1)
            {
                char ch=(char) fileData;
                stringBuffer=stringBuffer.append(ch);
                fileData=fileInputStream.read();
            }
        }
        catch (Exception e) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Ali","onDestroy");
    }
}