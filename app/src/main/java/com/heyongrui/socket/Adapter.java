package com.heyongrui.socket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by haiyuan1995 on 2016/10/12.
 */
public class Adapter extends BaseAdapter {
    private List<User> mlist;
    private Context context;
    private User user;

    public Adapter(Context context, List<User> mlist) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        user = mlist.get(position);
        if (user.getNum1() != null & user.getNum2() == null) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        int type = getItemViewType(position);
        switch (type) {
            case 0:
                convertView = LayoutInflater.from(context).inflate(R.layout.right, null);
                holder = new Holder();
                holder.metextview = (TextView) convertView.findViewById(R.id.me);
                convertView.setTag(holder);
                break;
            case 1:
                convertView = LayoutInflater.from(context).inflate(R.layout.left, null);
                holder = new Holder();
                holder.metextview = (TextView) convertView.findViewById(R.id.he);
                convertView.setTag(holder);
                break;
        }


        switch (type) {
            case 0:
                holder = (Holder) convertView.getTag();
                holder.metextview.setText(user.getNum1());
                break;
            case 1:
                holder = (Holder) convertView.getTag();
                holder.metextview.setText(user.getNum2());
                break;
        }
        return convertView;
    }

    class Holder {
        TextView metextview;
    }

}
