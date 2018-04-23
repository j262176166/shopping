package app.gl.com.shopping.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import app.gl.com.shopping.R;
import app.gl.com.shopping.base.BaseFragment;
import app.gl.com.shopping.home.Adapter.HomeFragmentAdapter;
import app.gl.com.shopping.home.bean.ResultBeanData;
import app.gl.com.shopping.utils.Constants;
import okhttp3.Call;

/**
 * Created by Administrator on 2018/4/23.
 * 主页面的fragment
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private ResultBeanData.ResultBean resultBean;//返回的数据
    private HomeFragmentAdapter adapter;

    @Override
    protected View initview() {
        Log.e(TAG, "主页视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView)
                view.findViewById(R.id.tv_search_home);
        tv_message_home = view.findViewById(R.id.tv_message_home);
        //设置点击事件
        initListener();
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "主页数据被初始化了");
        getDataFromNet();//获得联网主页的数据
    }

    //获得联网主页的数据
    public void getDataFromNet() {
        String url = Constants.HOME_URl;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /**
                     * 当联网失败时候回调
                     *
                     * @param call 请求成功的数据
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "首页请求失败==" + e.getMessage());
                    }

                    /**
                     * 当联网成功时候回调
                     *
                     * @param response 请求成功的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "首页请求成功==" + response);
                        //解析数据
                        processData(response);
                    }
                });
    }

    //得到返回的数据
    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json,ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        Log.e(TAG,resultBean.getHot_info().get(0).getName());
        if(resultBean != null){
            //有数据，设置适配器
            adapter = new HomeFragmentAdapter(mContext,resultBean);
            rvHome.setAdapter(adapter);
            //GridLayout不仅需要设置适配器还要设置布局管理者
            rvHome.setLayoutManager(new GridLayoutManager(mContext,1));
        } else {

        }
    }

    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                rvHome.scrollToPosition(0);
            }
        });
        //搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索",Toast.LENGTH_SHORT).show();
            }
        });
        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });
    }
}