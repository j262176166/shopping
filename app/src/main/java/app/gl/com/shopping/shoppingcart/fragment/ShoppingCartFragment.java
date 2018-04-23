package app.gl.com.shopping.shoppingcart.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import app.gl.com.shopping.base.BaseFragment;

/**
 * Created by Administrator on 2018/4/23.
 * 购物车的fragment
 */

public class ShoppingCartFragment extends BaseFragment {
    private static final String TAG ="数据";
    private TextView textView;
    @Override
    protected View initview() {
        Log.e(TAG,"购物车的Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText("购物车内容");
        Log.e(TAG,"购物车的Fragment的数据被初始化了");
        super.initData();
    }
}
