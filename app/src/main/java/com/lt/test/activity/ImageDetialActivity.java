package com.lt.test.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.caption.netmonitorlibrary.netStateLib.NetUtils;
import com.lt.test.R;
import com.lt.test.base.BaseActivity;
import com.lt.test.glide.options.GlideOptions;
import com.lt.test.glide.options.GlideOptionsFactory;
import com.lt.test.glide.options.GlideUtil;
import com.lt.test.glide.transformation.GlideCircleTransform;
import com.lt.test.glide.transformation.GlideRoundTransform;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDetialActivity extends BaseActivity {
    @BindView(R.id.iv_comm22)
    ImageView ivComm22;
    @BindView(R.id.iv_comm2)
    ImageView ivComm2;
    @BindView(R.id.iv_comm1)
    ImageView ivComm1;
    @BindView(R.id.iv_comm3)
    ImageView ivComm3;
    @BindView(R.id.iv_comm4)
    ImageView ivComm4;
    @BindView(R.id.iv_comm5)
    ImageView ivComm5;

    private String url = "http://short.im.rockhippo.cn/uploads/msg/201703/20170309/1485/1489068660846.jpg";
    private String url1 = "https://s3.51cto.com/wyfs02/M01/89/BA/wKioL1ga-u7QnnVnAAAfrCiGnBQ946_middle.jpg";
    private String url2 = "http://img0.bdstatic.com/img/image/touxiang01.jpg";
    @Override
    protected int setLayoutId() {
        return R.layout.activity_imagedetail;
    }

    @Override
    protected void initView() {
        super.initView();

        common_user();
        //3//平铺整个控件
        GlideUtil.display(ImageDetialActivity.this, ivComm3, url, GlideOptionsFactory.get(GlideOptionsFactory.Type.RADIUS));
        //4圆形图片（占位图）
        GlideOptions glideOptions = new GlideOptions(R.drawable.pic_head, R.drawable.pic_head, 1000);
        GlideUtil.display(ImageDetialActivity.this, ivComm4, url2, glideOptions);
//        Glide.with(context).load(ratings.getSku_image()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(viewHolder.iv_imgurl);
        //5正常加载图片
        GlideUtil.display(ImageDetialActivity.this, ivComm5, url2);
    }
    /**
     * 常见的glide加载图片
     * */
    private void common_user() {
        Glide.with(this).load(url2).into(ivComm1);
        // Glide.with(this).clear(iv_comm1);
        //只在拖动和静止时加载，自动滑动时不加载。
        /*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        Glide.with(MainActivityGlide.this).resumeRequests();
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        Glide.with(MainActivityGlide.this).pauseRequests();
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        Glide.with(MainActivityGlide.this).resumeRequests();
                        break;
                }
            }
        });*/
        //自定义
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .transform(new GlideCircleTransform());
        Glide.with(this).load(url2).apply(options).into(ivComm2);

        RequestOptions options2 = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .transform(new GlideRoundTransform());
        Glide.with(this).load(url2).apply(options2).into(ivComm22);


    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
