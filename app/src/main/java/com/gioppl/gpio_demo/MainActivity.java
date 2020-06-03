package com.gioppl.gpio_demo;

import android.os.Bundle;
import android.util.Log;

import com.jackiepenghe.serialportlibrary.OnSerialPortDataChangedListener;
import com.jackiepenghe.serialportlibrary.SerialPortManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String allDevices[]= SerialPortManager.getAllDevices();
        for (String s:allDevices){
            Log.i("device ports",s);
        }
        String allDevicesPath[]=SerialPortManager.getAllDevicesPath();
        for (String s:allDevicesPath){
            Log.i("device paths",s);
        }
        SerialPortManager.openSerialPort("/dev/ttyS1",115200);
        SerialPortManager.writeData("open*id=001#");
        Log.i("device status",SerialPortManager.isOpened()+"");
        SerialPortManager.setOnSerialPortDataChangedListener(new OnSerialPortDataChangedListener() {
            @Override
            public void serialPortDataReceived(byte[] data, int size) {
                String originalData=new String(data,0,size);
                String afterData=originalData.substring(6,14);
                Log.i("device response",afterData);
            }
        });
    }
}
