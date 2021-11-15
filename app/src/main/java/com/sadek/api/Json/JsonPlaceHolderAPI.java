package com.sadek.api.Json;

import com.sadek.api.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    @GET("posts")
    Call<List<PostModel>> getPosts();
}
