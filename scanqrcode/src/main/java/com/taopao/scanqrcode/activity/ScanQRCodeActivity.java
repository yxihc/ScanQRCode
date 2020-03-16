package com.taopao.scanqrcode.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.taopao.scanqrcode.R;
import com.taopao.scanqrcode.ScanQRCodeUtils;

import java.util.List;

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

        initView();
        mScanType = getIntent().getExtras().getInt("scanType");
        if ( getIntent().hasExtra("tips")){
            mTips = getIntent().getStringExtra("tips");
        }

        mZXingView.setDelegate(this);
    }

    private void initView() {
        mZXingView = findViewById(R.id.zxingview);
        findViewById(R.id.iv_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(ScanQRCodeActivity.this)
                        .openGallery(PictureMimeType.ofImage())///全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .minSelectNum(1)// 最小选择数量 int
                        .imageSpanCount(3)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片 true or false
                        .isCamera(false)// 是否显示拍照按钮 true or false
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        });
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
        }

        if (resultCode == RESULT_OK) {
            List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
            if (localMedias.size()>0){
                LocalMedia localMedia = localMedias.get(0);
                mZXingView.decodeQRCode(localMedia.getPath());
            }
        }
    }

}
