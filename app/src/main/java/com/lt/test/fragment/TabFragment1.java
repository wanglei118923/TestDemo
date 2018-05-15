package com.lt.test.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.caption.netmonitorlibrary.netStateLib.NetUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.lt.test.R;
import com.lt.test.activity.ImageDetialActivity;
import com.lt.test.base.BaseLazyFragment;
import com.lt.test.constant.Permission;
import com.lt.test.utils.DrawableUtil;
import com.lt.test.utils.GifSizeFilter;
import com.lt.test.utils.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

/**
 * Created by geyifeng on 2017/5/12.
 */

public class TabFragment1 extends BaseLazyFragment implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar idToolbar;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private  boolean isFirstEnter = true;
    private Context mContext;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setTitleBar(getActivity(), idToolbar);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_tab1;
    }

    @Override
    protected void initView() {
        super.initView();
        mContext=getContext();
        DrawableUtil.setTextSolidTheme(btn1, 1, 15);
        DrawableUtil.setTextSolidTheme(btn2, 1, 15);
        DrawableUtil.setTextSolidTheme(btn3, 1, 15);
        DrawableUtil.setTextSolidTheme(btn4, 1, 15);
        DrawableUtil.setTextSolidTheme(btn5, 1, 15);
        DrawableUtil.setTextSolidTheme(btn6, 1, 15);
        DrawableUtil.setTextSolidTheme(btn7, 1, 15);

    }

    @Override
    protected void initData() {
        super.initData();
        if (isFirstEnter) {
            isFirstEnter = false;
            refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
        }
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    @Override
    protected void setListener() {
        super.setListener();
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .navigationBarColor(R.color.red)
                .init();
    }

    @Override
    protected boolean isLazyLoad() {
        return super.isLazyLoad();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                startActivity(new Intent(getActivity(), ImageDetialActivity.class));
                break;
            case R.id.btn2:
                RxPermissions rxPermissions = new RxPermissions(getActivity());
                rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Observer<Boolean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }
                            @Override
                            public void onNext(Boolean aBoolean) {
                                if (aBoolean) {
                                    //同意权限
                                    Camera();
                                } else {
                                    Toast.makeText(mActivity,"拒绝请求权限", Toast.LENGTH_LONG)
                                            .show();
                                }
                            }
                            @Override
                            public void onError(Throwable e) {
                            }
                            @Override
                            public void onComplete() {
                            }
                        });
                break;

        }
    }


    /**
     * 调用相册拍照
     */
    private void Camera() {
        Matisse.from(getActivity())
                .choose(MimeType.ofAll())
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "com.lt.test.fileprovider"))
                .maxSelectable(9)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                        getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(23);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==23&&resultCode==RESULT_OK){
            LogUtils.e("进入这里");
            for (int i = 0; i <Matisse.obtainPathResult(data).size() ; i++) {
                LogUtils.e(Matisse.obtainPathResult(data).get(i));
            }

        }
    }



}
