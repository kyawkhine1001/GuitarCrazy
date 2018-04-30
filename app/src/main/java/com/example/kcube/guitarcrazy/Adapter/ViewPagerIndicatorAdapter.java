package com.example.kcube.guitarcrazy.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.kcube.guitarcrazy.R;

/**
 * Created by Asus on 9/15/2016.
 */
public class ViewPagerIndicatorAdapter extends PagerAdapter implements View.OnClickListener {

    private Context mContext;
    private int[] mResources;
    int width,height;

    public ViewPagerIndicatorAdapter(Context mContext, int[] mResources, int width, int height) {
        this.mContext = mContext;
        this.mResources = mResources;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        //get width and height of screen
        Bitmap m_d1 = BitmapFactory.decodeResource(mContext.getResources(),mResources[position]);
        Bitmap resizedBitmap1 = Bitmap.createScaledBitmap(m_d1, width, width,
                true);
        imageView.setImageBitmap(resizedBitmap1);
//        imageView.setImageResource(mResources[position]);
//        imageView.setMaxZoom(4f);
        imageView.setOnClickListener(this);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    @Override
    public void onClick(View v) {

    }
}