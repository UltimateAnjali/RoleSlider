package com.technicalscreen.roleslider.rest;

import com.technicalscreen.roleslider.model.PhotoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anjali desai on 12-01-2018.
 */

public interface ApiInterface {

    //String url = "https://api.500px.com/v1/photos?feature=popular&exclude=Nude,People
    // &consumer_key=AJHjroGYgXAl3OabA1SjMw180lakiWUOIyJBIodC";

    @GET("photos")
    Call<PhotoResponse> fetchPhotos(@Query("feature") String feature,
                                    @Query("exclude") String exclude,
                                    @Query("image_size") String imagesize,
                                    @Query("consumer_key") String consumer_key);
//    Observable<List<Photos>> fetchPhotos(String imageSize, boolean nextPage);
//
//    interface ApiRepo{
//        @GET(Constants.ENDPOINT_PATH + Constants.EXCLUDE_PHOTOS +
//                Constants.CONSUMER_KEY_SECTION)
//        Observable<PhotoResponse> fetchPhotos(@Query(Constants.IMAGE_SIZE) String imageSize,
//                                              @Query(Constants.PAGE) int page);
//    }
//    @GET(Constants.ENDPOINT_PATH + Constants.EXCLUDE_PHOTOS)
//    Call<PhotoResponse> getAllPhotos(@Query(Constants.CONSUMER_KEY_SECTION) String key);
}
