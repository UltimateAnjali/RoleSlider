package com.technicalscreen.roleslider.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    //Constructor of adapter with parameters
    public ImageAdapter(Context context, List<Images> allImages, int displayWidth){
        mContext = context;
        mImages = allImages;
        viewWidth = displayWidth;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Setting the layout as custom view layout
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Getting the object of Image class at specified position
        Images imageAtPosition = mImages.get(position);

        //Setting the image view with the url from the Images object using Picasso library
        Picasso.with(mContext)
                .load(Uri.parse(imageAtPosition.getImageUrl()))
                .resize(viewWidth,viewWidth)
                .centerCrop()
                .noFade()
                .into(holder.imageView);

        //Setting the textview with the caption from Images object
        holder.textView.setText(imageAtPosition.getImageFormat());
    }

    @Override
    public int getItemCount() {
        //Return the size of the list of images
        return mImages.size();
    }

}
