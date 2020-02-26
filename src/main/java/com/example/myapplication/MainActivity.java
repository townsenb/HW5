package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/*
    Ben Townsend
    Collin Turkelson

    HW5
*/
public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText from = findViewById(R.id.from_EditText);
        EditText to = findViewById(R.id.to_EditText);

        Button calculate = findViewById(R.id.calculateButton);
        Button clear = findViewById(R.id.clearButton);
        Button mode = findViewById(R.id.modeButton);


        double yards_to_meters = 0.9144;

        calculate.setOnClickListener(v -> {
            if(from.length() == 0 && to.length() != 0){
                int i = Integer.parseInt(to.getText().toString());
                from.setText("" + (i / yards_to_meters));
            }else if(to.length() == 0 && from.length() != 0){
                int i = Integer.parseInt(from.getText().toString());
                to.setText("" + (i * yards_to_meters));
            }else{
                to.setText("");
                from.setText("");
            }
        });

        clear.setOnClickListener(v -> {
            to.setText("");
            from.setText("");
        });

        mode.setOnClickListener(v -> {

        });

    }
}
