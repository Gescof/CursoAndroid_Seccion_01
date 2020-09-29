package com.example.seccion_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView1 = findViewById(R.id.textViewMain);

        // Tomar los datos del Intent
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getString("greeter") != null) {
            String greeter = bundle.getString("greeter");
            Toast.makeText(SecondActivity.this, greeter, Toast.LENGTH_LONG);
            textView1.setText(greeter);
        } else {
            Toast.makeText(SecondActivity.this, "It is empty!", Toast.LENGTH_LONG);
        }
    }

}