package app.gl.com.shopping.home.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

import app.gl.com.shopping.R;
import app.gl.com.shopping.home.bean.ResultBeanData;
import app.gl.com.shopping.utils.Constants;

/**
 * Created by Administrator on 2018/4/23.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    public static final int BANNER = 0;//banner图
    public static final int CHANNEL = 1;//频道
    public static final int ACT = 2;//活动
    public static final int SECKILL = 3;//秒杀
    public static final int RECOMMEND = 4;//推荐
    public static final int HOT = 5;//热卖
    private final Context mContext;
    private final ResultBeanData.ResultBean resultBean;
    private LayoutInflater mLayoutInflater;
    /**
     * 当前类型
     */
    private int currentType = BANNER;

    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater =  LayoutInflater.from(mContext);
    }

    /**
     * 相当于getView，创建viewHolder部分代码
     * 创建ViewHolder
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER){
            return new BannerViewHolder(mContext,mLayoutInflater.inflate(R.layout.banner_viewpager,null));
        }
        return null;
    }

    /**
     * 相当于getView中的绑定数据模块
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER){
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        }
    }

    //创建数据
    class BannerViewHolder extends RecyclerView.ViewHolder{

        private Context mContext;
        private View itemView;
        private Banner banner;
        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.banner = itemView.findViewById(R.id.banner);
        }

        //
        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //得到图片地址
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0 ; i<banner_info.size();i++){
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }
            //设置循环指示
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置banner效果
            banner.setBannerAnimation(Transformer.CubeIn);
            //设置banner的数据
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //联网请求图片-Glide
                    Glide.with(mContext).load(Constants.BASE_IMAGE_URL+url).into(view);
                }
            });
            //设置item的点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext,"position=="+position,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 得到类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    //总共有多少个item
    @Override
    public int getItemCount() {
        //开发过程中要从1开始慢慢到6
        return 1;
    }
}
