package com.example.seccion_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        /*btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Button clicked!", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(MainActivity.this, "Button clicked!", Toast.LENGTH_LONG).show();
    }

}