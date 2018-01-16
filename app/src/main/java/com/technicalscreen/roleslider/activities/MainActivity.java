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

    private static final int PAGE_START = 0;
    private boolean loadingFlag = false;
//    private boolean permanentFlag = true;
    private int TOTAL_PAGES = Constants.OFF_SCREEN_PAGE_LIMIT;
    private int currentPage = 0;
    int lastItem;

    List<Photos> photosList = new ArrayList<>();
    PhotoResponse photoResponse;
    int i=0,j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mapping the view items
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        //Setting the layout manager and item animator for recycler view
        final GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),2);
        final RecyclerView.LayoutManager mLayoutManager = manager;
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Getting the width of screen for equal spacing
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();

        //Initializing the adapter
        adapter = new ImageAdapter(getApplicationContext(),dataImages, (metrics.widthPixels)/2);

        //Setting the adapter to recycler view
        recyclerView.setAdapter(adapter);

        //Calling the method to get the response
        GetData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                lastItem = manager.findLastVisibleItemPosition();

                //Check if data needs to be loaded
                if(loadingFlag && dataImages.size() - lastItem < 5){
                    loadingFlag=false;
                    currentPage++;
                    GetData();
                }
            }
        });
    }

    public void GetData(){
        //Creating the Api client using Api Interface
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Toast.makeText(getApplicationContext(),"currentPage"+currentPage,Toast.LENGTH_SHORT).show();

        try{
            //Calling the method in Api Interface to pass the query parameters
            Call<PhotoResponse> call = apiService
                    .fetchPhotos("popular",
                                "Nude,People",
                                Constants.IMAGE_SIZE_1600,
                                currentPage,
                                getString(R.string.CONSUMER_KEY));
            call.enqueue(new Callback<PhotoResponse>() {
                @Override
                public void onResponse(Call<PhotoResponse> call, Response<PhotoResponse> response) {

                    //Setting the visibilities of elements
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    photoResponse = response.body();
                    photosList.addAll(photoResponse.getPhotos());

                    for(j=0; j < photosList.size(); j++){
                        for(i=0;i < photosList.get(j).getImages().size(); i++){

                            //Getting the images at specified position
                            image = photosList.get(j).getImages().get(i);
                            image.setName(photosList.get(j).getName());
                            dataImages.add(image);

                            //Notifying the data set change to the adapter
                            adapter.notifyDataSetChanged();
                            loadingFlag=true;
                        }
                    }
                    photosList.clear();
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
