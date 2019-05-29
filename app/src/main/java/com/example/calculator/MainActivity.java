package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    TextView text1;
    TextView text2;
    TextView textRes;
    Button button;
    String add = "Addition";
    String sub = "Substraction";
    String mult = "Multiplication";
    String div = "Division";
    String num1, num2;
    Integer int1, int2;
    Long result;
    Boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operators, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        text1 = findViewById(R.id.input1);
        text2 = findViewById(R.id.input2);
        textRes = findViewById(R.id.textRes);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = text1.getText().toString();
                num2 = text2.getText().toString();
                valid = true;
                if(num1.equals("")){
                    Toast.makeText(getBaseContext(), "Input 1 must be filled", Toast.LENGTH_SHORT).show();
                    valid = false;
                }else{
                    try{
                        int1 = Integer.parseInt(num1);
                    }catch(Exception e){
                        int1 = 0;
                        valid = false;
                        Toast.makeText(getBaseContext(), "Input 1 out of bound", Toast.LENGTH_SHORT).show();
                    }
                }
                if(num2.equals("")){
                    Toast.makeText(getBaseContext(), "Input 2 must be filled", Toast.LENGTH_SHORT).show();
                    valid = false;
                }else{
                    try{
                        int2 = Integer.parseInt(num2);
                    }catch(Exception e){
                        int2 = 0;
                        valid = false;
                        Toast.makeText(getBaseContext(), "Input 2 out of bound", Toast.LENGTH_SHORT).show();
                    }
                }
                if(valid){
                    if(spinner.getSelectedItem().toString().contains("+")){
                        result = (long) int1 + int2;
                        textRes.setText(String.valueOf(result));
                        Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();
                    }else if(spinner.getSelectedItem().toString().contains("-")){
                        result = (long) int1 - int2;
                        textRes.setText(String.valueOf(result));
                        Toast.makeText(getBaseContext(), sub, Toast.LENGTH_SHORT).show();
                    }else if(spinner.getSelectedItem().toString().contains("*")){
                        result = (long) int1 * int2;
                        textRes.setText(String.valueOf(result));
                        Toast.makeText(getBaseContext(), mult, Toast.LENGTH_SHORT).show();
                    }else if(spinner.getSelectedItem().toString().contains("/")){
                        if(Integer.parseInt(num2) == 0){
                            Toast.makeText(getBaseContext(), "Cannot divide by 0", Toast.LENGTH_SHORT).show();
                        }else{
                            result = (long) int1 / int2;
                            textRes.setText(String.valueOf(result));

                            if(int1 % int2 == 0){
                                Toast.makeText(getBaseContext(), div, Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getBaseContext(), div + " (Result is rounded down)", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
