package com.example.emride;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class mechanicActivity extends AppCompatActivity {

    private CardView cv_tyre,cv_start;
    private ImageView iv_back;
    String issue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic);

        cv_tyre=findViewById(R.id.cv_tyre);
        cv_start=findViewById(R.id.cv_start);
        iv_back=findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        cv_tyre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                issue="0";
                Intent i = new Intent(mechanicActivity.this,mapActivity.class);
                i.putExtra("issue",issue);
                startActivity(i);
            }
        });

        cv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                issue="1";
                Intent i = new Intent(mechanicActivity.this,mapActivity.class);
                i.putExtra("issue",issue);
                startActivity(i);
            }
        });

    }
}