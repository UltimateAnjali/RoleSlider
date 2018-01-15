package com.technicalscreen.roleslider.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.technicalscreen.roleslider.R;
import com.technicalscreen.roleslider.model.Images;

import java.util.List;

/**
 * Created by anjali desai on 12-01-2018.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder>{

    private Context mContext;
    private List<Images> mImages;
    private int viewWidth;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image_view);
            textView = (TextView)itemView.findViewById(R.id.caption);
        }
    }

    public ImageAdapter(Context context, List<Images> allImages, int displayWidth){
        mContext = context;
        mImages = allImages;
        viewWidth = displayWidth;
//        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
//        viewWidth = metrics.widthPixels;
//        Toast.makeText(mContext, viewWidth,Toast.LENGTH_SHORT).show();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Images imageAtPosition = mImages.get(position);

        Picasso.with(mContext)
                .load(Uri.parse(imageAtPosition.getImageUrl()))
                .resize(viewWidth,viewWidth)
                .centerCrop()
                .noFade()
                .into(holder.imageView);

        holder.textView.setText(imageAtPosition.getImageFormat());
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }


}
