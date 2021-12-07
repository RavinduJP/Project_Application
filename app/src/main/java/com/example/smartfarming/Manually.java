 package com.example.smartfarming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
//import android.view.View;
//import android.widget.AdapterView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
//import java.util.List;

 public class Manually extends AppCompatActivity {
    TextView ProvinceView, DistrictView, GNDivisionView;
    ArrayList<String> province, district, gndivision;
    Dialog dialog;
    //CharSequence charSequence;
    Button ManuallySelectbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manually);

        ProvinceView = findViewById(R.id.ProvinceView);
        DistrictView = findViewById(R.id.DistrictView);
        GNDivisionView = findViewById(R.id.GNDivisionView);
        ManuallySelectbutton = findViewById(R.id.ManuallySelectbutton);

//province array list
        province = new ArrayList<>();
            province.add("Nothern");
            province.add("Central");
            province.add("North Western");
            province.add("Sabaragamuwa");
            province.add("North Central");
            province.add("Uva");
            province.add("Southern");
            province.add("Western");
            province.add("Eastern");

//district array list
        district = new ArrayList<>();
            district.add("Ampara"); district.add("Anuradhapura"); district.add("Badulla"); district.add("Batticaloa"); district.add("Colombo");
            district.add("Galle"); district.add("Gampaha"); district.add("Hambantota"); district.add("Jaffna"); district.add("Kalutara");
            district.add("Kandy"); district.add("Kegalle"); district.add("Kilinochchi"); district.add("Kurunegala"); district.add("Mannar");
            district.add("Matale"); district.add("Matara"); district.add("Moneragala"); district.add("Mullaitivu"); district.add("Nuwara Eliya");
            district.add("Polonnaruwa"); district.add("Puttalam"); district.add("Ratnapura"); district.add("Trincomalee"); district.add("Vavuniya");

//gn division array list
        gndivision = new ArrayList<>();
            gndivision.add("Buddhangala"); gndivision.add("Urewa"); gndivision.add("Wahalkada D 6");
            gndivision.add("Elikibulagala"); gndivision.add("Kubukwewa"); gndivision.add("Wahalkada D 5");
            gndivision.add(" Abayapura"); gndivision.add("Ruwanpura"); gndivision.add("Wahalkada D 4");
            gndivision.add("Maithreepura"); gndivision.add("Bogahawewa"); gndivision.add("Kahatagollewa");
            gndivision.add("Parakramapura"); gndivision.add("Padaviya"); gndivision.add("Thammannawa");
            gndivision.add("'B' yaya"); gndivision.add("Halmillawatiya"); gndivision.add("Kunchuttuwa");
            gndivision.add("Bisokotuwa"); gndivision.add("Kanugahawewa");
            gndivision.add("Sudharshanagama"); gndivision.add("Herathhalmillewa");
            gndivision.add("Balayawewa"); gndivision.add("Bellankadawala");
            gndivision.add("Mahasenpura"); gndivision.add("Punchimudagama");


//province dropdown menu
        ProvinceView.setOnClickListener(view -> {

            dialog = new Dialog(Manually.this);
            dialog.setContentView(R.layout.dialog_searchable_spinner);

            dialog.getWindow().setLayout(750,950);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            EditText editText = dialog.findViewById(R.id.edit_text);
            ListView listView = dialog.findViewById(R.id.list_view);

            final ArrayAdapter<String> adapter =new ArrayAdapter<>(Manually.this
                    ,android.R.layout.simple_list_item_1,province );
            listView.setAdapter(adapter);
            editText.addTextChangedListener(new TextWatcher() {


                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    adapter.getFilter().filter(charSequence);

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            listView.setOnItemClickListener((adapterView, view1, start, l) -> {
                ProvinceView.setText(adapter.getItem(start));
                dialog.dismiss();
            });

        });

//district dropdown menu
        DistrictView.setOnClickListener(view -> {

            dialog = new Dialog(Manually.this);
            dialog.setContentView(R.layout.dialog_searchable_spinner_1);

            dialog.getWindow().setLayout(750,950);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            EditText editText = dialog.findViewById(R.id.edit_text1);
            ListView listView = dialog.findViewById(R.id.list_view1);

            final ArrayAdapter<String> adapter =new ArrayAdapter<>(Manually.this
                    ,android.R.layout.simple_list_item_1,district );
            listView.setAdapter(adapter);
            editText.addTextChangedListener(new TextWatcher() {


                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    adapter.getFilter().filter(charSequence);

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            listView.setOnItemClickListener((adapterView, view12, start, l) -> {
                DistrictView.setText(adapter.getItem(start));
                dialog.dismiss();
            });

        });

//GNDivision dropdown menu
        GNDivisionView.setOnClickListener(view -> {

            dialog = new Dialog(Manually.this);
            dialog.setContentView(R.layout.dialog_searchable_spinner_2);

            dialog.getWindow().setLayout(750,950);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            EditText editText = dialog.findViewById(R.id.edit_text2);
            ListView listView = dialog.findViewById(R.id.list_view2);

            final ArrayAdapter<String> adapter =new ArrayAdapter<>(Manually.this
                    ,android.R.layout.simple_list_item_1,gndivision );
            listView.setAdapter(adapter);
            editText.addTextChangedListener(new TextWatcher() {


                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    adapter.getFilter().filter(charSequence);

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            listView.setOnItemClickListener((adapterView, view13, start, l) -> {
                GNDivisionView.setText(adapter.getItem(start));
                dialog.dismiss();
            });

        });

        ManuallySelectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Manually.this, UploadImage.class);
                startActivity(intent);
                Toast toast = Toast.makeText(Manually.this,"Location Uploaded Successfully",Toast.LENGTH_SHORT);
                toast.show();

            }
        });

    }
}