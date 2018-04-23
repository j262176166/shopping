package app.gl.com.shopping.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/4/23.
 */

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //当系统被创建时候回回调
        mContext = getActivity();
    }

    //
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initview();
    }

    //抽象类，由孩子实现多态
    protected abstract View initview();

    //当activity创建了后回调
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    //当子类需要联网请求数据时候，可以重写该方法。
    public void initData() {
    }
}
