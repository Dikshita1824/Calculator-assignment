package com.example.assignment4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;



public class MainActivity extends AppCompatActivity {

    TextView input,output;
    Button all_clear,clear;
    Button equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);



            input = findViewById(R.id.input);
            output = findViewById(R.id.output);
            all_clear = findViewById(R.id.btnB);
            clear = findViewById(R.id.btnA);
            equal = findViewById(R.id.btnI);

            equal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String data = input.getText().toString();
                    Context context = Context.enter();
                    context.setOptimizationLevel(-1);
                    Scriptable scriptable = context.initSafeStandardObjects();
                    String result = context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
                    output.setText(result);
                }
            });

            all_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    input.setText("");
                    output.setText("");
                }
            });

            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = input.getText().toString();
                    if (text.length()>0){
                        input.setText(text.substring(0, text.length()-1));
                    }
                }
            });


            return insets;
        });
    }
    public void getvalue(View view){
        Button btn = (Button) view;
        input.setText(input.getText().toString() + btn.getText().toString());
    }
}