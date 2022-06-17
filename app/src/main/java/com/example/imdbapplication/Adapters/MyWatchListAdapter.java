package com.example.imdbapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imdbapplication.Activities.MyWatchListActivity;
import com.example.imdbapplication.Entity.Result;
import com.example.imdbapplication.R;


import java.util.List;

public class MyWatchListAdapter extends RecyclerView.Adapter<MyWatchListAdapter.ViewHolder>
{
    private List<Result> myWatchList;

    public MyWatchListAdapter(List<Result> myWatchList)
    {
        this.myWatchList = myWatchList;
    }

    @Override
    public MyWatchListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_watch_list_item, viewGroup, false);
        MyWatchListAdapter.ViewHolder holder = new MyWatchListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyWatchListAdapter.ViewHolder holder, final int position) {
        Result result = myWatchList.get(position);

        holder.my_watch_list_title_textview.setText(result.getImage());
        holder.my_watch_list_description_textview.setText(result.getTitle());

        String imageUrl = result.getDescription();
        Glide.with(holder.itemView)
                .load(imageUrl)
                .into(holder.my_watch_list_image_image_view);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return myWatchList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView my_watch_list_image_image_view;

        private TextView my_watch_list_title_textview;
        private TextView my_watch_list_description_textview;


        public ViewHolder(View v) {
            super(v);

            my_watch_list_image_image_view = v.findViewById(R.id.my_watch_list_image_image_view);
            my_watch_list_title_textview = v.findViewById(R.id.my_watch_list_title_textview);
            my_watch_list_description_textview = v.findViewById(R.id.my_watch_list_description_textview);


        }
    }
}
