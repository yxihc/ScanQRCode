package android.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new cn.bingoogolapple.qrcode.zxingdemo.DataBinderMapperImpl());
    addMapper(new V1CompatDataBinderMapperImpl());
  }
}
