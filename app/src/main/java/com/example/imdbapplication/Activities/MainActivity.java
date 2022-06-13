package com.example.imdbapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.imdbapplication.R;

public class MainActivity extends AppCompatActivity {

    private EditText search_content_edit_text;

    private Button search_content_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_content_edit_text = findViewById(R.id.search_content_edit_text);

        search_content_button = findViewById(R.id.search_content_button);

        search_content_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigateSearchResultListActivty();
            }
        });
    }

    private void NavigateSearchResultListActivty()
    {
        //take content name from edit text add bundle
        //call intent with bundle
    }
}