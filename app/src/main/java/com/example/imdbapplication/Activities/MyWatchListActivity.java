package com.example.imdbapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.imdbapplication.Adapters.MyWatchListAdapter;
import com.example.imdbapplication.Adapters.SearchResultAdapter;
import com.example.imdbapplication.DB.DB;
import com.example.imdbapplication.Entity.Result;
import com.example.imdbapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MyWatchListActivity extends AppCompatActivity {

    private RecyclerView my_watch_list_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_watch_list);

        my_watch_list_recyclerview = findViewById(R.id.my_watch_list_recyclerview);

        SetAdapterRecyclerView();
    }


    private void SetAdapterRecyclerView()
    {
        ArrayList<Result> resultList =  DB.getInstance(this).GetWatchList();

        MyWatchListAdapter adapter = new MyWatchListAdapter(resultList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        my_watch_list_recyclerview.setLayoutManager(mLayoutManager);
        my_watch_list_recyclerview.setAdapter(adapter);
    }
}