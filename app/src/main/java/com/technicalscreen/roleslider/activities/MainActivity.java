package com.technicalscreen.roleslider.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.technicalscreen.roleslider.R;
import com.technicalscreen.roleslider.adapter.ImageAdapter;
import com.technicalscreen.roleslider.model.Images;
import com.technicalscreen.roleslider.model.PhotoResponse;
import com.technicalscreen.roleslider.model.Photos;
import com.technicalscreen.roleslider.rest.ApiClient;
import com.technicalscreen.roleslider.rest.ApiInterface;
import com.technicalscreen.roleslider.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    ImageAdapter adapter;
    List<Images> dataImages = new ArrayList<>();
    Images image;
    int START_PAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mapping the view items
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        //Setting the layout manager and item animator for recycler view
        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Getting the width of screen for equal spacing
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
//        Toast.makeText(getApplicationContext(),metrics.widthPixels+"",Toast.LENGTH_SHORT).show();

        //Initializing the adapter
        adapter = new ImageAdapter(getApplicationContext(),dataImages, (metrics.widthPixels)/2);

        //Setting the adapter to recycler view
        recyclerView.setAdapter(adapter);

        //Calling the method to get the data
        GetData(START_PAGE);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
//                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
//                Toast.makeText(getApplicationContext(),visibleItemCount+"->"+totalItemCount,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void GetData(int page){
        //Creating the Api client using Api Interface
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        try{
            //Calling the method in Api Interface to pass the query parameters
            Call<PhotoResponse> call = apiService.fetchPhotos("popular",
                    "Nude,People",
                    Constants.IMAGE_SIZE_1600,
                    page,
                    getString(R.string.CONSUMER_KEY));
            call.enqueue(new Callback<PhotoResponse>() {
                @Override
                public void onResponse(Call<PhotoResponse> call, Response<PhotoResponse> response) {
                    PhotoResponse resp = response.body();
                    List<Photos> photos = resp.getPhotos();
                    for(int j = 0; j < photos.size(); j++){
                        for(int i = 0;i < photos.get(j).getImages().size(); i++){
                            //Getting the images at specified position
                            image = photos.get(j).getImages().get(i);

                            //Adding the images to the list
                            dataImages.add(image);

                            //Notifying the data set change to the adapter
                            adapter.notifyDataSetChanged();

                            //Setting the visibilities of elements
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);


                        }
                    }
                }

                @Override
                public void onFailure(Call<PhotoResponse> call, Throwable t) {
                    Log.e("Hello folks","Task failed");
                }
            });

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Oops! Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
}
