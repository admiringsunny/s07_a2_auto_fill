package com.acadgild.s07a2autofill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView actv;

    DB db = new DB(this);
    List<Pojo > pojoList;
    List<String> namesList;
    ArrayAdapter<String > adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db.onUpgrade(db.getWritableDatabase(), 1, 2);

        db.createAnEntry(new Pojo("Apple iPhone"));
        db.createAnEntry(new Pojo("Samsung Note 7"));
        db.createAnEntry(new Pojo("Dell Inspiron 1545"));
        db.createAnEntry(new Pojo("Nikon Coolpix 1100"));
        db.createAnEntry(new Pojo("HP Inkjet Printer"));
        db.createAnEntry(new Pojo("Amul Butter"));


        pojoList = db.getAllEntries();
        namesList = new ArrayList<>();
        for (Pojo pojo: pojoList) {
            namesList.add(pojo.getName());
        }


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namesList);


        actv = (AutoCompleteTextView) findViewById(R.id.auto_complete_text_view);
        actv.setThreshold(1); // will start from 1st character
        actv.setAdapter(adapter);

    }


}
