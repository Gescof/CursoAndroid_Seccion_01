package com.example.seccion_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class ThirdActivity extends AppCompatActivity {
    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imageButtonPhone;
    private ImageButton imageButtonWeb;
    private ImageButton imageButtonCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextWeb = findViewById(R.id.editTextWeb);
        imageButtonPhone = findViewById(R.id.imageButtonPhone);
        imageButtonWeb = findViewById(R.id.imageButtonWeb);
        imageButtonCam = findViewById(R.id.imageButtonCam);

        imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = editTextPhone.getText().toString();
                if(phoneNumber != null) {
                    Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                    startActivity(intentCall);
                }
            }
        });
    }
}