package com.example.smartfarming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectLocation extends AppCompatActivity {
    private Button GPSbtn;
    private Button MANUALLYbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);
        GPSbtn = (Button) findViewById(R.id.GPSbtn);
        MANUALLYbtn = (Button) findViewById(R.id.MANUALLYbtn);

        GPSbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openGps();
            }
        });

        MANUALLYbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              openManually();
            }
        });
    }

    public void openGps(){
        Intent intent = new Intent(this, Gps.class);
        startActivity(intent);

        Toast toast = Toast.makeText(SelectLocation.this,"You selected GPS mode.",Toast.LENGTH_SHORT);
        toast.show();
    }

    public void openManually(){
        Intent intent = new Intent(this, Manually.class);
        startActivity(intent);

        Toast toast = Toast.makeText(SelectLocation.this,"You selected MANUALLY mode.",Toast.LENGTH_SHORT);
        toast.show();
    }
}