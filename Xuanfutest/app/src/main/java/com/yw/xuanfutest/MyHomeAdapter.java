package com.yw.xuanfutest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/5/31.
 */
public class MyHomeAdapter extends RecyclerView.Adapter<MyHomeAdapter.MyViewHolder> {
    private Context context;
    private List<String> list;

    public MyHomeAdapter(Context context, List<String> list) {
        super();
        this.context = context;
        this.list = list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.recyclerview_item, parent,
                false));
        return holder;
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends ViewHolder {

        TextView tv;
        LinearLayout linearLayout;
        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.textview_item);
            linearLayout = (LinearLayout) view.findViewById(R.id.linear_item);
            linearLayout.setClickable(false);
            linearLayout.setEnabled(false);
            linearLayout.setFocusable(false);
            tv.setClickable(false);
        }
    }
}

