package com.example.imdbapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.imdbapplication.DB.DB;
import com.example.imdbapplication.Entity.Result;
import com.example.imdbapplication.R;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private List<Result> searchResultList;

    public SearchResultAdapter(List<Result> searchResultList) {
        this.searchResultList = searchResultList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_result_list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Result result = searchResultList.get(position);

        holder.search_result_title_textview.setText(result.getTitle());
        holder.search_result_description_textview.setText(result.getDescription());

        holder.add_wishlist_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add sql lite here
                DB.getInstance(view.getContext()).AddNewWatchList(result.getId(),result.getResultType(),result.getTitle(),result.getDescription(),result.getImage());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return searchResultList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView search_result_image_view;

        private TextView search_result_title_textview;
        private TextView search_result_description_textview;

        private Button add_wishlist_button;

        public ViewHolder(View v) {
            super(v);

            search_result_image_view = v.findViewById(R.id.search_result_image_view);
            search_result_title_textview = v.findViewById(R.id.search_result_title_textview);
            search_result_description_textview = v.findViewById(R.id.search_result_description_textview);
            add_wishlist_button = v.findViewById(R.id.add_wishlist_button);

        }
    }

}
