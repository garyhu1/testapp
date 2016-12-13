package com.garyhu.testappdemo;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private final MovieBean bean;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<MovieBean.DataBean.ComingBean> mData;

    public MyRecyclerViewAdapter(Context mContext, MovieBean bean) {
        this.mContext = mContext;
        this.bean = bean;
        mLayoutInflater = LayoutInflater.from(mContext);
        init();
    }

    public void init(){
        if(bean!=null){
            mData = bean.getData().getComing();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.data_item, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyViewHolder myholder = (MyViewHolder) holder;
        myholder.setData(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mv_name;
        private TextView mv_dec;
        private TextView mv_date;
        private SimpleDraweeView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mv_name = (TextView) itemView.findViewById(R.id.mv_name);
            mv_dec = (TextView) itemView.findViewById(R.id.mv_dec);
            mv_date = (TextView) itemView.findViewById(R.id.mv_date);
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.image);
        }

        public void setData(int position) {
            MovieBean.DataBean.ComingBean movie = mData.get(position);

            String name = movie.getNm();
            mv_name.setText(name);

            String date = movie.getShowInfo();
            mv_date.setText(date);

            String dec = movie.getScm();
            mv_dec.setText(dec);

            //注：当你发下图片无法打开是，做个字符串替换即可
            String imagUrl = movie.getImg();
            String newImagUrl = imagUrl.replaceAll("w.h", "50.80");

            //使用Fresco加载图片
            imageView.setImageURI(Uri.parse(newImagUrl));
        }
    }
}
