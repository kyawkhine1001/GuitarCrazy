package com.example.kcube.guitarcrazy.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kcube.guitarcrazy.Model.LyricModel;
import com.example.kcube.guitarcrazy.Model.SingerModel;
import com.example.kcube.guitarcrazy.Other.Database_Handler;
import com.example.kcube.guitarcrazy.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Asus on 12/23/2016.
 */
public class LyricAdapter extends RecyclerView.Adapter<LyricAdapter.MyViewHolder> {
    private Context context;
    private List<LyricModel> list = Collections.emptyList();
    private LayoutInflater inflater;
    private Database_Handler database_handler;
    private String fav;
    private int id;

    public LyricAdapter(Context context, List<LyricModel> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void SwapList(List<LyricModel> lyriclist) {
        this.list = lyriclist;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lyric_list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        database_handler = new Database_Handler(context);
        Collections.sort(list, new Comparator<LyricModel>() {
            @Override
            public int compare(LyricModel o1, LyricModel o2) {
                return o1.getTitle().compareToIgnoreCase(o2.getTitle());
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.lyricname.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lyricname;
        ImageView favicon;

        public MyViewHolder(View itemView) {
            super(itemView);
            lyricname = (TextView) itemView.findViewById(R.id.lyricname);
            favicon = (ImageView) itemView.findViewById(R.id.favicon);
        }
    }
}
