package com.example.smartfarming;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartfarming.ml.ModelQ;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class UploadImage extends AppCompatActivity {

    ImageButton camerabtn, gallerybtn;
    ImageView photoDisplay;
    Button predict;
    Bitmap img;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        camerabtn = findViewById(R.id.camerabtn);
        gallerybtn = findViewById(R.id.gallerybtn);
        photoDisplay = findViewById(R.id.photoDisplay);
        predict = findViewById(R.id.predict);
        tv = findViewById(R.id.tv);

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

        gallerybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);

            }
        });

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img = Bitmap.createScaledBitmap(img, 224,224, true);

                try {
                    ModelQ model = ModelQ.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);

                    TensorImage tensorImage = new TensorImage(DataType.UINT8);
                    tensorImage.load(img);
                    ByteBuffer byteBuffer = tensorImage.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    ModelQ.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    // Releases model resources if no longer used.
                    model.close();

                    if (outputFeature0.getFloatArray()[0] >  outputFeature0.getFloatArray()[1] ){
                        tv.setText("Black Loam \n" + "Predicted value: " + ((outputFeature0.getFloatArray()[0] / 255)* 100) +"%");
                    }else {
                        tv.setText("Red Laterite \n" + "Predicted value: " + ((outputFeature0.getFloatArray()[1] / 255)* 100) +"%");
                    }
//                    tv.setText(outputFeature0.getFloatArray()[0] + "\n" + outputFeature0.getFloatArray()[1]);
                } catch (IOException e) {
                    // TODO Handle the exception
                }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            photoDisplay.setImageBitmap(captureImage);

            photoDisplay.setImageURI(data.getData());

            Uri uri = data.getData();
            try{
                img = MediaStore.Images.Media.getBitmap((this.getContentResolver()), uri);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}