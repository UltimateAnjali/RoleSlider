package com.technicalscreen.roleslider.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.technicalscreen.roleslider.R;
import com.technicalscreen.roleslider.adapter.ImageAdapter;
import com.technicalscreen.roleslider.model.Images;
import com.technicalscreen.roleslider.model.PhotoResponse;
import com.technicalscreen.roleslider.model.Photos;
import com.technicalscreen.roleslider.rest.ApiClient;
import com.technicalscreen.roleslider.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageAdapter adapter;
    List<Images> dataImages = new ArrayList<>();
    Images image;
   // public static final String API_KEY = MainActivity.this.getString(R.string.CONSUMER_KEY);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        Toast.makeText(getApplicationContext(),metrics.widthPixels+"",Toast.LENGTH_SHORT).show();
        adapter = new ImageAdapter(getApplicationContext(),dataImages, (metrics.widthPixels)/2);

        recyclerView.setAdapter(adapter);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<PhotoResponse> call = apiService.fetchPhotos("popular","Nude,People","1600",getString(R.string.CONSUMER_KEY));
        call.enqueue(new Callback<PhotoResponse>() {
            @Override
            public void onResponse(Call<PhotoResponse> call, Response<PhotoResponse> response) {
                PhotoResponse resp = response.body();
                List<Photos> photos = resp.getPhotos();
                for(int j = 0; j < photos.size(); j++){
                    for(int i = 0;i < photos.get(j).getImages().size(); i++){
                        image = photos.get(j).getImages().get(i);
                        dataImages.add(image);
                        adapter.notifyDataSetChanged();
                    }
                }

                //Toast.makeText(getApplicationContext(),"Got it"+photos.size(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<PhotoResponse> call, Throwable t) {
                Log.e("Hello folks","Task failed");
            }
        });

    }
}
