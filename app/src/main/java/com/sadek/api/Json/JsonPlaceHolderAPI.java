package com.sadek.api.Json;

import com.sadek.api.model.CommentModel;
import com.sadek.api.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderAPI {

    @GET("posts")
    Call<List<PostModel>> getPosts();

    @GET("posts/{postId}/comments")
    Call<List<CommentModel>> getPostsById(@Path("postId") int postId);

    @GET("comments")
    Call<List<CommentModel>> getPostByQuery(@Query("postId") int postId);
}
