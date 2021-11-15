package com.sadek.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.sadek.api.Json.JsonPlaceHolderAPI;
import com.sadek.api.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = (TextView) findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        jsonPlaceHolderAPI.getPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "code= " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<PostModel> postModels = response.body();

                for (PostModel post : postModels){

                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "body: " + post.getBody() + "\n\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "message= " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //region method goToLink
    private void goToLink(String link) {
        Intent instagramIntent = new Intent(Intent.ACTION_VIEW);
        instagramIntent.setData(Uri.parse(link.toString()));
        startActivity(instagramIntent);
    }
    //endregion method goToLink
}