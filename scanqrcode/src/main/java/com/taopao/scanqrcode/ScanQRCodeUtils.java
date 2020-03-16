package com.taopao.scanqrcode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.taopao.scanqrcode.activity.ScanQRCodeActivity;
public class ScanQRCodeUtils {
    public static final String RESULT_TYPE = "result_type";
    public static final String RESULT_STRING = "result_string";
    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_FAILED = 2;
    public static final int REQUEST_CODE = 1002;
    /**
     * @param context
     * @param scanType // 默认为0，二者都扫  1，扫描二维码， 2，扫描条形码
     * @param tips 提示
     */
    public static void ScanQRCode(Context context, int scanType, String tips) {
        Intent intent = new Intent(context, ScanQRCodeActivity.class);
        intent.putExtra("scanType", scanType);
        if (tips != null && !tips.isEmpty()) {
            intent.putExtra("tips", tips);
        }
        ((Activity) context).startActivityForResult(intent, REQUEST_CODE);
    }
    public static void onResultQRCode(int requestCode, int resultCode, Intent data,CallBack callBack) {
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                String result = bundle.getString(RESULT_STRING);
                callBack.onResult(result==null?false:true,result);
            }
        }
    }
    public interface CallBack{
        void onResult(boolean isSuccess,String result);
    }
}