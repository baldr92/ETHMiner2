package com.example.fire.ethminer;

import com.example.fire.ethminer.models.Request;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 22.11.2017.
 */

public interface RetrofitInterfaceGraph {
    @GET("/data/histoday")
    Call<Request> getPosts(@Query("fsym") String fsym, @Query("tsum") String tsum,
                           @Query("limit") int limit);
    }
