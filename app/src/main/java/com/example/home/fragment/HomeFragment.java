package com.example.home.fragment;

import android.drm.ProcessedData;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.example.base.BaseFragment;
import com.example.daoshousong.R;
import com.example.home.adapter.HomeFragmentAdapter;
import com.example.home.bean.ResultBeanData;
import com.example.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;
import okhttp3.Request;

import static android.content.ContentValues.TAG;

public class HomeFragment extends BaseFragment {    //主页面Fragment

    private RecyclerView rvHome;
    private ImageView ib_top;
    private Button btn_search_home;
    private HomeFragmentAdapter adapter;
    private ResultBeanData.ResultBean resultBean;   //返回的数据

    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        btn_search_home = (Button) view.findViewById(R.id.btn_search_home);
        //设置点击事件
        initListener();
        return view;
    }
    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "主页数据被初始化了");
        //联网请求主页的数据
        getDataFromNet();
    }

    private void getDataFromNet(){
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    //当请求失败的时候回调
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败" + e.getMessage());
                    }

                    //当联网成功的时候回调，response:请求成功的数据
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"首页请求成功"+response);
                        //解析数据
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json,ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        if(resultBean != null){
            //有数据，设置适配器
            adapter = new HomeFragmentAdapter(mContext,resultBean);
            rvHome.setAdapter(adapter);
        }else{
            //没有数据
        }
        Log.e(TAG,"解析成功" + resultBean.getHot_info().get(0).getName());

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
        btn_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
