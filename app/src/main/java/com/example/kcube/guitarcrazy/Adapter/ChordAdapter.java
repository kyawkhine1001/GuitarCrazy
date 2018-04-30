package com.example.kcube.guitarcrazy.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kcube.guitarcrazy.Model.ChordModel;
import com.example.kcube.guitarcrazy.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Asus on 12/23/2016.
 */
public class ChordAdapter extends RecyclerView.Adapter<ChordAdapter.MyViewHolder> {
    private Context context;
    private List<ChordModel> list = Collections.emptyList();
    private LayoutInflater inflater;

    public ChordAdapter(Context context, List<ChordModel> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.chord_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.chordname.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView chordname;

        public MyViewHolder(View itemView) {
            super(itemView);
            chordname = (TextView) itemView.findViewById(R.id.chordName);
        }
    }
}
