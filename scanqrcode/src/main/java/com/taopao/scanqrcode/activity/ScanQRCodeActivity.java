package com.taopao.scanqrcode.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.taopao.scanqrcode.R;
import com.taopao.scanqrcode.ScanQRCodeUtils;

import cn.bingoogolapple.qrcode.core.BarcodeType;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanQRCodeActivity extends AppCompatActivity implements QRCodeView.Delegate {
    private static final String TAG = ScanQRCodeActivity.class.getSimpleName();
    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;

    private ZXingView mZXingView;
    private int mScanType;
    private String mTips="将二维码/条码放入框内，即可自动扫描";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
        mZXingView = findViewById(R.id.zxingview);

        mScanType = getIntent().getExtras().getInt("scanType");
        if ( getIntent().hasExtra("tips")){
            mTips = getIntent().getStringExtra("tips");
        }

        mZXingView.setDelegate(this);
    }

    ////////////////////声明周期//////////////////////////////

    @Override
    protected void onStart() {
        super.onStart();
        mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
        if (mScanType == 0) {
            mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
            mZXingView.setType(BarcodeType.ALL, null); // 识别所有类型的码
        } else if (mScanType == 1) {
            mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
            mZXingView.setType(BarcodeType.TWO_DIMENSION, null); // 只识别二维条码
        } else {
            mZXingView.changeToScanBarcodeStyle(); // 切换成扫描条码样式
            mZXingView.setType(BarcodeType.ONE_DIMENSION, null); // 只识别一维条码
        }
        //设置提示
        mZXingView.getScanBoxView().setTipText(mTips);
        mZXingView.getScanBoxView().setAutoZoom(true);
        mZXingView.getScanBoxView().setOnlyDecodeScanBoxArea(true); // 识别整个屏幕中的码
        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }

    @Override
    protected void onStop() {
        mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        mZXingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }
    //////////////////////////////////////////////////
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    //////////////////////////////////////////////////
    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        setTitle("扫描结果为：" + result);

        vibrate();
        Intent resultIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(ScanQRCodeUtils.RESULT_TYPE, ScanQRCodeUtils.RESULT_SUCCESS);
        bundle.putString(ScanQRCodeUtils.RESULT_STRING, result);
        resultIntent.putExtras(bundle);
        this.setResult(RESULT_OK, resultIntent);
        this.finish();


//        mZXingView.startSpot(); // 开始识别
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        String tipText = mZXingView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                mZXingView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                mZXingView.getScanBoxView().setTipText(tipText);
            }
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }


    ///////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
//            final String picturePath = BGAPhotoPickerActivity.getSelectedPhotos(data).get(0);
            // 本来就用到 QRCodeView 时可直接调 QRCodeView 的方法，走通用的回调
//            mZXingView.decodeQRCode(picturePath);

            /*
            没有用到 QRCodeView 时可以调用 QRCodeDecoder 的 syncDecodeQRCode 方法

            这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
            请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github
            .com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
             */
//            new AsyncTask<Void, Void, String>() {
//                @Override
//                protected String doInBackground(Void... params) {
//                    return QRCodeDecoder.syncDecodeQRCode(picturePath);
//                }
//
//                @Override
//                protected void onPostExecute(String result) {
//                    if (TextUtils.isEmpty(result)) {
//                        Toast.makeText(TestScanActivity.this, "未发现二维码", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(TestScanActivity.this, result, Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }.execute();
        }
    }

}
