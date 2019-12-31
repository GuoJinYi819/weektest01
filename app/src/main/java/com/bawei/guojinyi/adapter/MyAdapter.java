package com.bawei.guojinyi.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.guojinyi.App;
import com.bawei.guojinyi.R;
import com.bawei.guojinyi.bean.SmsesBean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: weektest01
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2019/12/31 9:12
 * @Description: 用途：完成特定功能
 */
public class MyAdapter extends BaseAdapter {
    private List<SmsesBean> smses = new ArrayList<>();

    public MyAdapter(List<SmsesBean> smses) {
        this.smses.addAll(smses);
    }

    @Override
    public int getCount() {
        return smses.size();
    }



    @Override
    public int getItemViewType(int position) {
        SmsesBean smsesBean = smses.get(position);
        int isread = smsesBean.getIsread();
        if(isread==1){
            return 0;
        }else{
            return 1;
        }
    }
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @Override
    public Object getItem(int position) {
        return smses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //优化
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if(type==0){
            ViewHolder holder = null;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = View.inflate(App.context, R.layout.item1_layout,null);
                holder.content = convertView.findViewById(R.id.content_1);
                holder.from = convertView.findViewById(R.id.from_1);
                holder.time = convertView.findViewById(R.id.time_1);
                convertView.setTag(holder);
            }else {
                holder  = (ViewHolder) convertView.getTag();
            }
            SmsesBean smsesBean = smses.get(position);
            String content = smsesBean.getContent();
            holder.content.setText(content);
            String from = smsesBean.getFrom();
            holder.from.setText(from);
            String time = smsesBean.getTime();
            holder.time.setText(time);
            return convertView;
        }else {
            ViewHolder holder = null;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = View.inflate(App.context, R.layout.item_layout,null);
                holder.content = convertView.findViewById(R.id.content_1);
                holder.from = convertView.findViewById(R.id.from_1);
                holder.time = convertView.findViewById(R.id.time_1);
                convertView.setTag(holder);
            }else {
                holder  = (ViewHolder) convertView.getTag();
            }
            SmsesBean smsesBean = smses.get(position);
            String content = smsesBean.getContent();
            holder.content.setText(content);
            String from = smsesBean.getFrom();
            holder.from.setText(from);
            String time = smsesBean.getTime();
            holder.time.setText(time);
            return convertView;
        }

    }
    //容器
    class ViewHolder{
        private TextView from,content,time;
    }
}
