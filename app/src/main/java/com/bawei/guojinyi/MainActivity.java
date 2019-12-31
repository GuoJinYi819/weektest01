package com.bawei.guojinyi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.guojinyi.adapter.MyAdapter;
import com.bawei.guojinyi.bean.DataBean;
import com.bawei.guojinyi.bean.SmsesBean;
import com.bawei.guojinyi.contract.ContractClass;
import com.bawei.guojinyi.mvp.presenter.Presenter;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ContractClass.ContractView {

    private XListView list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //解析数据
        String path = "http://blog.zhaoliang5156.cn/api/news/smsbak.json";
        Presenter presenter = new Presenter();
        presenter.attachView(path,this);
        list_view.setPullLoadEnable(true);
    list_view.setXListViewListener(new XListView.IXListViewListener() {
        @Override
        public void onRefresh() {
            list_view.stopRefresh();
        }

        @Override
        public void onLoadMore() {
            list_view.stopLoadMore();
        }
    });
    }

    private void initView() {
        list_view = findViewById(R.id.list_view);
    }

    @Override
    public void viewSuccess(String json) {
        //解析
        Gson gson = new Gson();
        DataBean dataBean = gson.fromJson(json, DataBean.class);
        List<SmsesBean> smses = dataBean.getSmses();
        //设置适配器
        MyAdapter myAdapter = new MyAdapter(smses);
        list_view.setAdapter(myAdapter);
    }

    @Override
    public void viewFailed(String failed) {
        Toast.makeText(this, ""+failed, Toast.LENGTH_SHORT).show();
    }
}
