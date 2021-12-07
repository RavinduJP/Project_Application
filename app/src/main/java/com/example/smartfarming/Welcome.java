package com.example.smartfarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button welcomebtn = (Button) findViewById(R.id.welcomebtn);
        welcomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSelectLocation();
            }
        });
    }

    public void openSelectLocation() {
        Intent intent = new Intent(this, SelectLocation.class);
        startActivity(intent);
    }
}