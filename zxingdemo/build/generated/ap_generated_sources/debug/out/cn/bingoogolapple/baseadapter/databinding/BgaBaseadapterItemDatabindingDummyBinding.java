package cn.bingoogolapple.baseadapter.databinding;
import cn.bingoogolapple.baseadapter.R;
import cn.bingoogolapple.baseadapter.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class BgaBaseadapterItemDatabindingDummyBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.view.View mboundView0;
    // variables
    @Nullable
    private java.lang.Object mModel;
    @Nullable
    private java.lang.Object mItemEventHandler;
    @Nullable
    private cn.bingoogolapple.baseadapter.BGABindingViewHolder mViewHolder;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public BgaBaseadapterItemDatabindingDummyBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 1, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.view.View) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.model == variableId) {
            setModel((java.lang.Object) variable);
        }
        else if (BR.itemEventHandler == variableId) {
            setItemEventHandler((java.lang.Object) variable);
        }
        else if (BR.viewHolder == variableId) {
            setViewHolder((cn.bingoogolapple.baseadapter.BGABindingViewHolder) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setModel(@Nullable java.lang.Object Model) {
        this.mModel = Model;
    }
    @Nullable
    public java.lang.Object getModel() {
        return mModel;
    }
    public void setItemEventHandler(@Nullable java.lang.Object ItemEventHandler) {
        this.mItemEventHandler = ItemEventHandler;
    }
    @Nullable
    public java.lang.Object getItemEventHandler() {
        return mItemEventHandler;
    }
    public void setViewHolder(@Nullable cn.bingoogolapple.baseadapter.BGABindingViewHolder ViewHolder) {
        this.mViewHolder = ViewHolder;
    }
    @Nullable
    public cn.bingoogolapple.baseadapter.BGABindingViewHolder getViewHolder() {
        return mViewHolder;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static BgaBaseadapterItemDatabindingDummyBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BgaBaseadapterItemDatabindingDummyBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<BgaBaseadapterItemDatabindingDummyBinding>inflate(inflater, cn.bingoogolapple.baseadapter.R.layout.bga_baseadapter_item_databinding_dummy, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static BgaBaseadapterItemDatabindingDummyBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BgaBaseadapterItemDatabindingDummyBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(cn.bingoogolapple.baseadapter.R.layout.bga_baseadapter_item_databinding_dummy, null, false), bindingComponent);
    }
    @NonNull
    public static BgaBaseadapterItemDatabindingDummyBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static BgaBaseadapterItemDatabindingDummyBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/bga_baseadapter_item_databinding_dummy_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new BgaBaseadapterItemDatabindingDummyBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): model
        flag 1 (0x2L): itemEventHandler
        flag 2 (0x3L): viewHolder
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}