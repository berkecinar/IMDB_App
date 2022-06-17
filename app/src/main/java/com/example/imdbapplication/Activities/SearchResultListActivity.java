package com.example.imdbapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.imdbapplication.Adapters.SearchResultAdapter;
import com.example.imdbapplication.Entity.Result;
import com.example.imdbapplication.Entity.SearchResult;
import com.example.imdbapplication.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SearchResultListActivity extends Activity {

    private RecyclerView search_result_recyclerview;

    private String searchContentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_list);

        search_result_recyclerview = findViewById(R.id.search_result_recyclerview);

        Bundle bundle = getIntent().getExtras();

        searchContentName = "";

        if(bundle != null)
        {
            searchContentName = bundle.getString("searchKey");

            searchContentName = searchContentName.replace(" ", "%20");
        }

        GetContentDataFromNetwork();
    }

    private void GetContentDataFromNetwork()
    {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://imdb-api.com/en/API/SearchMovie/k_ewamc78o/" + searchContentName)
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e)
            {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException
            {
                final String responseBody = response.body().string();
                SearchResult searchResult = new Gson().fromJson(responseBody, SearchResult.class);

                SearchResultListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SetAdapterRecyclerView(searchResult.getResults());
                    }
                });
            }
        });
    }

    private void SetAdapterRecyclerView(List<Result> resultList)
    {
        SearchResultAdapter adapter = new SearchResultAdapter(resultList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        search_result_recyclerview.setLayoutManager(mLayoutManager);
        search_result_recyclerview.setAdapter(adapter);
    }
}