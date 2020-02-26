package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
    Ben Townsend
    Collin Turkelson

    HW5
*/
public class MainActivity extends AppCompatActivity{

    public static final int UNIT_SELECTION = 1;
    public static boolean isVolume = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText from = findViewById(R.id.from_EditText);
        EditText to = findViewById(R.id.to_EditText);

        TextView fromLabel = findViewById(R.id.from_label);
        TextView toLabel = findViewById(R.id.to_label);

        Button calculate = findViewById(R.id.calculateButton);
        Button clear = findViewById(R.id.clearButton);
        Button mode = findViewById(R.id.modeButton);
        Button settings = findViewById(R.id.settingsButton);

        boolean isVolume = false;
        double yards_to_meters = 0.9144;

        String unit = (isVolume)? "Volume" : "Length";
        updateTitleLabel(unit);

        calculate.setOnClickListener(v -> {
            if(from.length() == 0 && to.length() != 0){
                int i = Integer.parseInt(to.getText().toString());
                double conv = getConversion(toLabel.getText().toString(),fromLabel.getText().toString());
                from.setText("" + (i * conv));
            }else if(to.length() == 0 && from.length() != 0){
                int i = Integer.parseInt(from.getText().toString());
                double conv = getConversion(fromLabel.getText().toString(),toLabel.getText().toString());
                to.setText("" + (i * conv));
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
            toggleMode();
        });

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, selection_activity.class);
            startActivityForResult(intent, UNIT_SELECTION);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == UNIT_SELECTION){
            TextView fromLabel = findViewById(R.id.from_label);
            TextView toLabel = findViewById(R.id.to_label);

            fromLabel.setText(data.getStringExtra("from_unit"));
            toLabel.setText(data.getStringExtra("to_unit"));
        }
    }

    private void updateTitleLabel(String type){
        TextView title = findViewById(R.id.titleLabel);
        title.setText(type + " Converter");
    }

    private void toggleMode(){
        isVolume = !isVolume;
        String type = (isVolume)? "Volume" : "Length";
        updateTitleLabel(type);

        String defaultFromUnit = (isVolume)? "Liters" : "Meters";
        String defaultToUnit = (isVolume)? "Gallons": "Yards";
        TextView fromLabel = findViewById(R.id.from_label);
        TextView toLabel = findViewById(R.id.to_label);

        fromLabel.setText(defaultFromUnit);
        toLabel.setText(defaultToUnit);
    }

    private double getConversion(String from, String to){
        if(isVolume){
            UnitsConverter.VolumeUnits fromEnum = UnitsConverter.VolumeUnits.valueOf(from);
            UnitsConverter.VolumeUnits toEnum = UnitsConverter.VolumeUnits.valueOf(to);
            return UnitsConverter.convert(1.0,fromEnum,toEnum);
        }else{
            UnitsConverter.LengthUnits fromEnum = UnitsConverter.LengthUnits.valueOf(from);
            UnitsConverter.LengthUnits toEnum = UnitsConverter.LengthUnits.valueOf(to);
            return UnitsConverter.convert(1.0,fromEnum,toEnum);
        }
    }

}
