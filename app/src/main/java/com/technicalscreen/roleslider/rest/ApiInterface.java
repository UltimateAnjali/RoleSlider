package com.technicalscreen.roleslider.rest;

import com.technicalscreen.roleslider.model.PhotoResponse;
import com.technicalscreen.roleslider.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anjali desai on 12-01-2018.
 */

public interface ApiInterface {

    @GET("photos")
    Call<PhotoResponse> fetchPhotos(@Query(Constants.FEATURE) String feature,
                                    @Query(Constants.EXCLUDE_PHOTOS) String exclude,
                                    @Query(Constants.IMAGE_SIZE) String imagesize,
                                    @Query(Constants.PAGE) int page,
                                    @Query(Constants.CONSUMER_KEY_SECTION) String consumer_key);
}
