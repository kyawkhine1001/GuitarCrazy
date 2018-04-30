package com.example.kcube.guitarcrazy.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kcube.guitarcrazy.Model.CategoryItemModel;
import com.example.kcube.guitarcrazy.R;

import java.util.ArrayList;

/**
 * Created by Asus on 10/17/2017.
 */
public class CategoryAdapter extends BaseAdapter {
    Context context;
    ArrayList<CategoryItemModel> arrayList;
    int width, height;
    Typeface typeface;
    SharedPreferences sharedPreferences, sharedPreferences1;

    public CategoryAdapter(Context context, ArrayList<CategoryItemModel> arrayList, int width, int height) {
        this.context = context;
        this.arrayList = arrayList;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View v, int position) {
            imageView = (ImageView) v.findViewById(R.id.categoryimageView);
            textView = (TextView) v.findViewById(R.id.categorytextview);
            textView.setTextColor(Color.WHITE);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.category_view, parent, false);
//            DisplayMetrics metrics = new DisplayMetrics();
//            //.getDefaultDisplay().getMetrics(metrics);
//           // context.getApplicationContext().
//            int height = metrics.heightPixels;
//            int width = metrics.widthPixels;
//            LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.categoryLayout);
//            linearLayout.setMinimumWidth(width / 2);
//            linearLayout.setMinimumHeight(width/2);
            //     Log.i("height & width",height+" "+width);
            CardView cardView = (CardView) convertView.findViewById(R.id.categorycardview);
            cardView.setMinimumWidth(width / 2);
            cardView.setMinimumHeight(width / 4);
            viewHolder = new ViewHolder(convertView, position);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.imageView.setImageResource(arrayList.get(position)
//                .getImage());
        //font change uni_zawgyi
//        viewHolder.textView.setTextColor(Color.WHITE);
//        sharedPreferences = context.getSharedPreferences("uni_zaw", 01);
//        if (sharedPreferences.getInt("uni_zaw", 0) == 0) {
//            typeface = Typeface.createFromAsset(context.getAssets(), "zawgyi.ttf");
//            viewHolder.textView.setTypeface(typeface);
        viewHolder.textView.setText(arrayList.get(position).getText());
//        } else if (sharedPreferences.getInt("uni_zaw", 1) == 1) {
//            typeface = Typeface.createFromAsset(context.getAssets(), "unicode.ttf");
//            viewHolder.textView.setTypeface(typeface);
//            viewHolder.textView.setText(new uni_zaw_change().zg2uni(arrayList.get(position).getText()));
//        }
        Bitmap m_d = BitmapFactory.decodeResource(context.getResources(),
                arrayList.get(position).getImage());
        Bitmap resizedBitmap = null;
        if (m_d != null)
            resizedBitmap = Bitmap.createScaledBitmap(m_d, (width / 2), (width / 2),
                    false);
        viewHolder.imageView.setImageBitmap(resizedBitmap);
        // viewHolder.imageView.setColorFilter(Color.parseColor("#009688"), PorterDuff.Mode.SRC_IN);
//        viewHolder.imageView.setImageBitmap(arrayList.get(position).getBitmap());
//        viewHolder.imageView.setImageResource(arrayList.get(position)
//                .getImage());
        return convertView;
    }
}
