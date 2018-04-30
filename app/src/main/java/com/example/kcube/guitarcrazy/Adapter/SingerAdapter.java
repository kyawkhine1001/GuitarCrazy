package com.example.kcube.guitarcrazy.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kcube.guitarcrazy.Model.ChordModel;
import com.example.kcube.guitarcrazy.Model.LyricModel;
import com.example.kcube.guitarcrazy.Model.SingerModel;
import com.example.kcube.guitarcrazy.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Asus on 12/23/2016.
 */
public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.MyViewHolder> {
    private Context context;
    private List<SingerModel> list = Collections.emptyList();
    private LayoutInflater inflater;

    public SingerAdapter(Context context, List<SingerModel> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.singer_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        Collections.sort(list, new Comparator<SingerModel>() {
            @Override
            public int compare(SingerModel o1, SingerModel o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.singername.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView singername;

        public MyViewHolder(View itemView) {
            super(itemView);
            singername = (TextView) itemView.findViewById(R.id.singername);
        }
    }
}
