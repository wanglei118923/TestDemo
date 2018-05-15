package com.lt.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.caption.netmonitorlibrary.netStateLib.NetUtils;
import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.lt.test.base.BaseActivity;
import com.lt.test.fragment.TabFragment1;
import com.lt.test.fragment.TabFragment2;
import com.lt.test.fragment.TabFragment3;
import com.lt.test.fragment.TabFragment4;
import com.lt.test.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.vp_content)
    CustomViewPager vpContent;
    @BindView(R.id.bbl)
    BottomBarLayout bbl;

    private List<Fragment> mFragmentList = new ArrayList<>();
    private RotateAnimation mRotateAnimation;
    private Handler mHandler = new Handler();
    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
    }
    @Override
    protected void initData() {
        super.initData();
        TabFragment1 homeFragment = new TabFragment1();
        mFragmentList.add(homeFragment);

        TabFragment2 videoFragment = new TabFragment2();
        mFragmentList.add(videoFragment);

        TabFragment3 microFragment = new TabFragment3();
        mFragmentList.add(microFragment);

        TabFragment4 meFragment = new TabFragment4();
        mFragmentList.add(meFragment);
    }
    @Override
    protected void setListener() {
        super.setListener();
        vpContent.setAdapter(new MyAdapter(getSupportFragmentManager()));
        bbl.setViewPager(vpContent);
        //设置是否可以滑动
        vpContent.setScroll(true);
        //设置预加载数
        vpContent.setOffscreenPageLimit(3);
        bbl.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final BottomBarItem bottomBarItem, int previousPosition, final int currentPosition) {
                Log.i("MainActivity", "position: " + currentPosition);
                if (currentPosition == 0) {
                    //如果是第一个，即首页
                    if (previousPosition == currentPosition) {
                        //如果是在原来位置上点击,更换首页图标并播放旋转动画
                        bottomBarItem.setIconSelectedResourceId(R.mipmap.tab_loading);//更换成加载图标
                        bottomBarItem.setStatus(true);

                        //播放旋转动画
                        if (mRotateAnimation == null) {
                            mRotateAnimation = new RotateAnimation(0, 360,
                                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                                    0.5f);
                            mRotateAnimation.setDuration(800);
                            mRotateAnimation.setRepeatCount(-1);
                        }
                        ImageView bottomImageView = bottomBarItem.getImageView();
                        bottomImageView.setAnimation(mRotateAnimation);
                        bottomImageView.startAnimation(mRotateAnimation);//播放旋转动画

                        //模拟数据刷新完毕
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                boolean tabNotChanged = bbl.getCurrentItem() == currentPosition; //是否还停留在当前页签
                                bottomBarItem.setIconSelectedResourceId(R.mipmap.tab_home_selected);//更换成首页原来选中图标
                                bottomBarItem.setStatus(tabNotChanged);//刷新图标
                                cancelTabLoading(bottomBarItem);
                            }
                        }, 3000);
                        return;
                    }
                }
                vpContent.setCurrentItem(currentPosition);
                //如果点击了其他条目
                BottomBarItem bottomItem = bbl.getBottomItem(0);
                bottomItem.setIconSelectedResourceId(R.mipmap.tab_home_selected);//更换为原来的图标

                cancelTabLoading(bottomItem);//停止旋转动画
            }
        });
        bbl.setUnread(0, 20);//设置第一个页签的未读数为20
        bbl.setUnread(1, 1001);//设置第二个页签的未读数
        bbl.showNotify(2);//设置第三个页签显示提示的小红点
        bbl.setMsg(3, "NEW");//设置第四个页签显示NEW提示文字
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }
    /**
     * 停止首页页签的旋转动画
     */
    private void cancelTabLoading(BottomBarItem bottomItem) {
        Animation animation = bottomItem.getImageView().getAnimation();
        if (animation != null) {
            animation.cancel();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }


}
