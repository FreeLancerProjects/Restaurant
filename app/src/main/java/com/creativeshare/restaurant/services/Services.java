package com.creativeshare.restaurant.services;



import com.creativeshare.restaurant.Model.Catogry_Model_Slide;
import com.creativeshare.restaurant.Model.Sub_Catogry_Model_Slide;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface Services {







    @GET("api/all-categories")
    Call<Catogry_Model_Slide> getcateogries(
    );

@FormUrlEncoded
    @POST("api/sub-categories/cat")
    Call<Sub_Catogry_Model_Slide>getsubcatogries(
            @Field("cat_id") Integer cat_id

);
}
