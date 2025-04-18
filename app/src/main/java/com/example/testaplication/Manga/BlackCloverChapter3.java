package com.example.testaplication.Manga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testaplication.API.APIBlackClover;
import com.example.testaplication.API.GetImageAPI;
import com.example.testaplication.Adapter.RecycleViewAdapter;
import com.example.testaplication.R;
import com.example.testaplication.Adapter.Image;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BlackCloverChapter3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_clover_chapter3);
        recyclerView = findViewById(R.id.recyleView);
        getImage();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        textBack = findViewById(R.id.imgback);
        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BlackCloverChapter3.this, Chap2BlackClover.class);
                startActivity(intent);
                finish();
            }
        });
        imgNext = findViewById(R.id.imgnext);
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(BlackCloverChapter3.this, Chapter4BlackClover.class);
               startActivity(intent);
            }
        });
        setChap = findViewById(R.id.setChap);
        setChap.setText("Chapter 3");
    }
    private void getImage(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        GetImageAPI apiGet = new Retrofit.Builder()
                .baseUrl("https://api.jsonbin.io/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(GetImageAPI.class);

        Call<APIBlackClover> call = apiGet.getBlackCloverImages();
        call.enqueue(new Callback<APIBlackClover>() {
            @Override
            public void onResponse(Call<APIBlackClover> call, Response<APIBlackClover> response) {
                if (response.isSuccessful()) {
                    APIBlackClover blackCloverData = response.body();
                    for(int i = 0;i<blackCloverData.getChapter3().size();i++){
                        chapter3.add(new Image(blackCloverData.getChapter3().get(i)));
                    }
                    RecycleViewAdapter adapter = new RecycleViewAdapter(chapter3);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(BlackCloverChapter3.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIBlackClover> call, Throwable t) {

            }
        });
    }
    private List<Image> chapter3 = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageView imgNext;
    private Spinner spinner;
    private ImageView textBack;
    private TextView setChap;
}