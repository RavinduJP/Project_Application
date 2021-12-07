package com.example.smartfarming;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class UploadImage extends AppCompatActivity {

    ImageButton camerabtn, gallerybtn;
    ImageView photoDisplay;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        camerabtn = findViewById(R.id.camerabtn);
        gallerybtn = findViewById(R.id.gallerybtn);
        photoDisplay = findViewById(R.id.photoDisplay);
        button = findViewById(R.id.button);

        if (ContextCompat.checkSelfPermission(UploadImage.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(UploadImage.this,
                    new String[] {
                            Manifest.permission.CAMERA
                    },
                    100);
        }

        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("daata");

            photoDisplay.setImageBitmap(captureImage);
        }
    }
}