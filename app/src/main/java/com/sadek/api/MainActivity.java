package com.sadek.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sadek.api.Json.JsonPlaceHolderAPI;
import com.sadek.api.model.CommentModel;
import com.sadek.api.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private ProgressBar progressbarActivityMain;
    private JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIdes();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

//        getData();
//        getPostsById();
        getPostByQuery();
    }

    private void findViewByIdes() {
        textViewResult = (TextView) findViewById(R.id.text_view_result);
        progressbarActivityMain = (ProgressBar) findViewById(R.id.progressbarActivityMain);
    }

    //region method goToLink
    private void goToLink(String link) {
        Intent instagramIntent = new Intent(Intent.ACTION_VIEW);
        instagramIntent.setData(Uri.parse(link.toString()));
        startActivity(instagramIntent);
    }
    //endregion method goToLink

    private void getData() {
        progressbarActivityMain.setVisibility(View.VISIBLE);

        jsonPlaceHolderAPI.getPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                //في حال الاستجابة
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "code= " + response.code(), Toast.LENGTH_SHORT).show();
                    progressbarActivityMain.setVisibility(View.GONE);
                    return;
                }

                List<PostModel> postModels = response.body();

                for (PostModel post : postModels) {

                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "body: " + post.getBody() + "\n\n";

                    textViewResult.append(content);
                }
                progressbarActivityMain.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                progressbarActivityMain.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "message= " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPostsById() {
        progressbarActivityMain.setVisibility(View.VISIBLE);

        jsonPlaceHolderAPI.getPostsById(5).enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(Call<List<CommentModel>> call, Response<List<CommentModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "code= " + response.code(), Toast.LENGTH_SHORT).show();
                    progressbarActivityMain.setVisibility(View.GONE);
                    return;
                }

                List<CommentModel> commentModels = response.body();

                for (CommentModel commentModel : commentModels) {

                    String content = "";
                    content += "postId: " + commentModel.getPostId() + "\n";
                    content += "id: " + commentModel.getId() + "\n";
                    content += "name: " + commentModel.getName() + "\n";
                    content += "body: " + commentModel.getBody() + "\n";
                    content += "email: " + commentModel.getEmail() + "\n\n";

                    textViewResult.append(content);
                }
                progressbarActivityMain.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<CommentModel>> call, Throwable t) {
                progressbarActivityMain.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "message= " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPostByQuery() {
        progressbarActivityMain.setVisibility(View.VISIBLE);

        jsonPlaceHolderAPI.getPostByQuery(1).enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(Call<List<CommentModel>> call, Response<List<CommentModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "code= " + response.code(), Toast.LENGTH_SHORT).show();
                    progressbarActivityMain.setVisibility(View.GONE);
                    return;
                }

                List<CommentModel> commentModels = response.body();

                for (CommentModel commentModel : commentModels) {

                    String content = "";
                    content += "postId: " + commentModel.getPostId() + "\n";
                    content += "id: " + commentModel.getId() + "\n";
                    content += "name: " + commentModel.getName() + "\n";
                    content += "body: " + commentModel.getBody() + "\n";
                    content += "email: " + commentModel.getEmail() + "\n\n";

                    textViewResult.append(content);
                }
                progressbarActivityMain.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<CommentModel>> call, Throwable t) {
                progressbarActivityMain.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "message= " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}