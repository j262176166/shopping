package app.gl.com.shopping.community.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import app.gl.com.shopping.base.BaseFragment;

/**
 * Created by Administrator on 2018/4/23.
 * 发现的fragment
 */

public class CommunityFragment extends BaseFragment {
    private static final String TAG ="数据";
    private TextView textView;
    @Override
    protected View initview() {
        Log.e(TAG,"发现的Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText("发现内容");
        Log.e(TAG,"发现的Fragment的数据被初始化了");
        super.initData();
    }
}
