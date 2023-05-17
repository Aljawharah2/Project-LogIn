package com.example.project_login;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Addme extends AppCompatActivity {

        TextView textView, textView3, textView4, textView5, textView6;
        //ImageView imageView;
        //FloatingActionButton addPhoto;
        //ImageButton pickImg ;
        Button add, view, backme;
        ImageButton back;
        EditText Title, price, size, desc;
        ListView lv_customerList;
        /* Uri uri;
         Bitmap imageToStore;*/
    ArrayAdapter itemArrayAdapter;

    DataBaseHelper dataBaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addme);

        textView = findViewById(R.id.textView);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        add = findViewById(R.id.add);
        view = findViewById(R.id.view);
        Title = findViewById(R.id.Title);
        price = findViewById(R.id.price);
        size = findViewById(R.id.size);
        desc = findViewById(R.id.desc);
        lv_customerList = findViewById(R.id.lv_customerList);
        back = findViewById(R.id.back);
        backme = findViewById(R.id.backme);
        dataBaseHelper = new DataBaseHelper(Addme.this);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddModel addMod = null;

                try {
                    addMod = new AddModel(-1, Title.getText().toString(), desc.getText().toString(), Integer.parseInt(price.getText().toString()), size.getText().toString());
                    Toast.makeText(Addme.this, addMod.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(Addme.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(Addme.this);
                boolean b = dataBaseHelper.addOne(addMod);
                Toast.makeText(Addme.this, "SUCCESS= " + b, Toast.LENGTH_SHORT).show();
                itemArrayAdapter = new ArrayAdapter<AddModel>(Addme.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
                lv_customerList.setAdapter(itemArrayAdapter);
                // ShowItemsOnListView(dataBaseHelper);

            }
        });
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                extracted();
                DataBaseHelper dataBaseHelper = new DataBaseHelper(Addme.this);

            }
        });


        backme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), profileActivity.class);
                startActivity(intent2);
            }
        });
        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private void ShowitemsOnListView(DataBaseHelper dataBaseHelper) {
                itemArrayAdapter = new ArrayAdapter<AddModel>(Addme.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
                lv_customerList.setAdapter(itemArrayAdapter);
            }
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                AddModel ClickedStudent = (AddModel) adapterView.getItemAtPosition(i);
                dataBaseHelper.DeleteOne(ClickedStudent);
                ShowitemsOnListView(dataBaseHelper);
                Toast.makeText(Addme.this, "Deleted" + ClickedStudent.toString(), Toast.LENGTH_SHORT).show();
            }
        });





    }

    private void extracted() {
        itemArrayAdapter = new ArrayAdapter<AddModel>(Addme.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        lv_customerList.setAdapter(itemArrayAdapter);
    }



}

