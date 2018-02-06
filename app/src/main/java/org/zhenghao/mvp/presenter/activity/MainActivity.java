package org.zhenghao.mvp.presenter.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import org.zhenghao.R;
import org.zhenghao.application.MyApplication;
import org.zhenghao.application.UserManager;
import org.zhenghao.mvp.model.PublicModel;
import org.zhenghao.mvp.model.bean.BaseEntity;
import org.zhenghao.mvp.model.bean.UserBean;
import org.zhenghao.mvp.presenter.fragment.HomeFragment;
import org.zhenghao.mvp.presenter.fragment.MineFragment;
import org.zhenghao.mvp.presenter.fragment.NoticeFragment;
import org.zhenghao.mvp.view.MainActivityDelegate;
import org.zhenghao.utils.SharedPreferencesUtil;
import org.zhenghao.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static org.zhenghao.constants.Constants.LoginType.IS_VISITOR;
import static org.zhenghao.constants.Constants.USER_PHONE;
import static org.zhenghao.constants.Constants.USER_PWD;


public class MainActivity extends ActivityPresenter<MainActivityDelegate> {
    private ViewPager viewPager;
    private BottomNavigationView navigation;
    private Boolean canFinish = false;//按两次退出APP的标志
    private TimerTask task;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferencesUtil.saveAppStatus(true);
        initUserStatus();
    }

    private void initUserStatus() {
        if (UserManager.getInstance().getUserStatus() == null || UserManager.getInstance().getUserStatus().equals(IS_VISITOR)) {
            initView();
        } else {
            Map<String, String> map = SharedPreferencesUtil.getPhonePwd();
            if (map != null && map.containsKey(USER_PHONE)) {
                PublicModel.getInstance().autoLogin(map.get(USER_PHONE), map.get(USER_PWD), new PublicModel.ReLoginListener() {
                    @Override
                    public void success(BaseEntity<UserBean> userBean) {
                        UserManager.getInstance().setUserBean(userBean.getResult());
                        MyApplication.getAppContext().setAlisa("bsc" + userBean.getResult().getUuid());
                        initView();
                    }

                    @Override
                    public void error() {
                        UserManager.getInstance().setUserNull();
                        initView();
                    }

                    @Override
                    public void serverException(BaseEntity<UserBean> userBean) {
                        ToastUtil.s(userBean.getMsg());
                        UserManager.getInstance().setUserNull();
                        initView();
                    }
                });
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return doubleClickToQuit(keyCode, event);
    }

    private boolean doubleClickToQuit(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (!canFinish) {
                canFinish = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        task = new TimerTask() {
                            @Override
                            public void run() {
                                canFinish = false;
                            }
                        };
                        timer.schedule(task, 2500);
                    }
                }).start();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (task != null) {
            task.cancel();
        }
        SharedPreferencesUtil.saveAppStatus(false);
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return false;
    }

    @Override
    public Class<MainActivityDelegate> getDelegateClass() {
        return MainActivityDelegate.class;
    }

    private void initView() {
        viewPager = viewDelegate.get(R.id.content_pager);
        viewPager.setOffscreenPageLimit(2);
        mFragmentPagerAdapter mFragmentPagerAdapter = new mFragmentPagerAdapter(getSupportFragmentManager(), getFragments());
        viewPager.setAdapter(mFragmentPagerAdapter);
        navigation = viewDelegate.get(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Menu menu = navigation.getMenu();
                menu.getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getBoolean("from_notification")) {
            Bundle bundle1 = new Bundle();
            bundle1.putInt("notice_id", bundle.getInt("notice_id"));
            startMyActivity(NoticeDetailActivity.class, bundle1);
        }

    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new NoticeFragment());
        fragments.add(new MineFragment());
        return fragments;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (viewPager == null)
                        return false;
                    else {
                        viewPager.setCurrentItem(0);
                        return true;
                    }
                case R.id.navigation_notice:
                    if (viewPager == null)
                        return false;
                    else {
                        viewPager.setCurrentItem(1);
                        return true;
                    }
                case R.id.navigation_mine:
                    if (viewPager == null)
                        return false;
                    else {
                        viewPager.setCurrentItem(2);
                        return true;
                    }
            }
            return false;
        }
    };


    public class mFragmentPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragments;

        public mFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> pFragments) {
            super(fm);
            this.mFragments = pFragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments == null ? 0 : mFragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }
    }
}
