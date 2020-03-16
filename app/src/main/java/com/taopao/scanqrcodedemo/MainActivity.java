package com.taopao.scanqrcodedemo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.taopao.scanqrcode.ScanQRCodeUtils;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sao(View view){
        ScanQRCodeUtils.ScanQRCode(this,0,"1212212121");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ScanQRCodeUtils.onResultQRCode(requestCode, resultCode, data, new ScanQRCodeUtils.CallBack() {
            @Override
            public void onResult(final String result) {
                Log.e("===", "onResult: "+result);
                Toast.makeText(MainActivity.this,"扫码结果："+result,Toast.LENGTH_LONG).show();

            }
        });
    }
}
