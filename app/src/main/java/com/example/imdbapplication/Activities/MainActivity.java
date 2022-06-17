package com.example.imdbapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.imdbapplication.R;

public class MainActivity extends Activity {

    private String searchContentName;

    private EditText search_content_edit_text;
    private Button search_content_button;

    private Button show_watch_list_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_content_edit_text = findViewById(R.id.search_content_edit_text);

        searchContentName = GetSearchContentNameFromLocalDataSource();

        if(searchContentName.length() >= 1)
        {
            search_content_edit_text.setText(GetSearchContentNameFromLocalDataSource());
        }

        search_content_button = findViewById(R.id.search_content_button);
        search_content_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSearchContentNameToLocalDataSource(search_content_edit_text.getText().toString());
                NavigateSearchResultListActivty();
            }
        });

        show_watch_list_button = findViewById(R.id.show_watch_list_button);
        show_watch_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigateMyWatchListActivty();
            }
        });

    }



    private void SaveSearchContentNameToLocalDataSource(String searchContentName)
    {
        this.searchContentName = searchContentName;
        String CONST_DATA = "SEARCH_CONTENT_NAME";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CONST_DATA,String.valueOf(searchContentName));
        editor.apply();
    }

    private String GetSearchContentNameFromLocalDataSource()
    {
        String result;
        String CONST_DATA = "SEARCH_CONTENT_NAME";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        result = preferences.getString(CONST_DATA, "");

        return result;
    }

    private void NavigateSearchResultListActivty()
    {
        Intent searchResultListIntent = new Intent(MainActivity.this, SearchResultListActivity.class);
        searchResultListIntent.putExtra("searchKey", searchContentName);
        startActivity(searchResultListIntent);

    }

    private void NavigateMyWatchListActivty()
    {
        Intent myWatchListIntent = new Intent(MainActivity.this, MyWatchListActivity.class);
        startActivity(myWatchListIntent);
    }
}