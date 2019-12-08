package com.example.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.base.BaseFragment;
import com.example.daoshousong.R;
import com.example.foodcircle.fragment.FoodcircleFragment;
import com.example.home.fragment.HomeFragment;
import com.example.order.fragment.OrderFragment;
import com.example.user.fragment.UserFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;  //装多个Fragment的集合
    private int position = 0;
    private Fragment tempFragment;  //上次显示的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife和当前Activity绑定
        ButterKnife.bind(this);
        initFragment();
        initListener();
        int id = getIntent().getIntExtra("id", 0);
        if (id == 4) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_main, new UserFragment());
            transaction.commit();
        }
    }

    private void initListener(){
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home://主页
                        position = 0;
                        break;
                    case R.id.rb_foodcircle://美食圈
                        position = 1;
                        break;
                    case R.id.rb_order://订单
                        position = 2;
                        break;
                    case R.id.rb_user://我的
                        position = 3;
                        break;
                }
                //根据位置取不同的Fragment
                BaseFragment baseFragment = getFragment(position);

                switchFragment(tempFragment,baseFragment);
            }
        });
        rgMain.check(R.id.rb_home);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new FoodcircleFragment());
        fragments.add(new OrderFragment());
        fragments.add(new UserFragment());
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction =
                        getSupportFragmentManager().beginTransaction();
                //判断 nextFragment 是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        } }
}
