package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class selection_activity extends AppCompatActivity {

    int mode = 1;
    private String fromSelection = (mode == 1)? "Gallons" : "Meters";
    private String toSelection = (mode == 1)? "Gallons" : "Meters";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("from_unit",fromSelection);
                intent.putExtra("to_unit",toSelection);
                setResult(MainActivity.UNIT_SELECTION,intent);
                finish();
            }
        });

        Spinner fromSpinner = findViewById(R.id.from_spinner);
        Spinner toSpinner = findViewById(R.id.to_spinner);

        ArrayAdapter<CharSequence> length_adapter = ArrayAdapter.createFromResource(this,
                R.array.length_units,android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> volume_adapter = ArrayAdapter.createFromResource(this,
                R.array.volume_units,android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter = (mode == 1)? volume_adapter : length_adapter;

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromSelection = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        toSpinner.setAdapter(adapter);
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toSelection = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
