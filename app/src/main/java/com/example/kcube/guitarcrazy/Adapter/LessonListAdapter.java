package com.example.kcube.guitarcrazy.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kcube.guitarcrazy.Model.LessonModel;
import com.example.kcube.guitarcrazy.R;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;

import static com.example.kcube.guitarcrazy.Other.RetrofitHelper.PHOTO_BASE_URL;

/**
 * Created by Asus on 12/23/2016.
 */
public class LessonListAdapter extends RecyclerView.Adapter<LessonListAdapter.MyViewHolder> {
    Context context;
    List<LessonModel> list = Collections.emptyList();
    LayoutInflater inflater;

    public LessonListAdapter(Context context, List<LessonModel> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lesson_list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.lessontitle.setText(list.get(position).getTitle());
        holder.lessoncontent.setText(list.get(position).getContent());
        MixedArray imagearray = Pherialize.unserialize(list.get(position).getImage()).toArray();
        Glide.with(context).load(PHOTO_BASE_URL + imagearray.getString(0)).into(holder.lessonimage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lessontitle;
        TextView lessoncontent;
        ImageView lessonimage;

        public MyViewHolder(View itemView) {
            super(itemView);
            lessonimage = (ImageView) itemView.findViewById(R.id.ivLesson);
            lessontitle = (TextView) itemView.findViewById(R.id.lessontitle);
            lessoncontent = (TextView) itemView.findViewById(R.id.lessoncontent);
        }
    }
}
