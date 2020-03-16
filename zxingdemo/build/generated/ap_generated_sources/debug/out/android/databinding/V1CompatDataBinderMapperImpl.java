
package android.databinding;
import cn.bingoogolapple.qrcode.zxingdemo.BR;
import android.util.SparseArray;
class V1CompatDataBinderMapperImpl extends android.databinding.DataBinderMapper {
    public V1CompatDataBinderMapperImpl() {
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        if (layoutId == cn.bingoogolapple.baseadapter.R.layout.bga_baseadapter_item_databinding_dummy) {
            final Object tag = view.getTag();
            if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
            if ("layout/bga_baseadapter_item_databinding_dummy_0".equals(tag)) {
                return new cn.bingoogolapple.baseadapter.databinding.BgaBaseadapterItemDatabindingDummyBinding(bindingComponent, view);
            }
            throw new java.lang.IllegalArgumentException("The tag for bga_baseadapter_item_databinding_dummy is invalid. Received: " + tag);
        }
        return null;
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        return null;
    }
    @Override
    public int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case -2069377230: {
                if(tag.equals("layout/bga_baseadapter_item_databinding_dummy_0")) {
                    return cn.bingoogolapple.baseadapter.R.layout.bga_baseadapter_item_databinding_dummy;
                }
                break;
            }
        }
        return 0;
    }
    @Override
    public String convertBrIdToString(int id) {
        final String value = InnerBrLookup.sKeys.get(id);
        return value;
    }
    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>();
        static {
                sKeys.put(0, "_all");
                sKeys.put(0, "_all");
                sKeys.put(1, "itemEventHandler");
                sKeys.put(2, "model");
                sKeys.put(3, "viewHolder");
        }
    }
}