package com.example.seccion_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    private EditText editTextPhone;
    private EditText editTextWeb;
    private EditText editTextMail;
    private ImageButton imageButtonPhone;
    private ImageButton imageButtonWeb;
    private ImageButton imageButtonAgenda;
    private ImageButton imageButtonEmail;
    private ImageButton imageButtonCam;

    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Activar flecha para ir atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextWeb = findViewById(R.id.editTextWeb);
        editTextMail = findViewById(R.id.editTextTextEmailAddress);
        imageButtonPhone = findViewById(R.id.imageButtonPhone);
        imageButtonWeb = findViewById(R.id.imageButtonWeb);
        imageButtonAgenda = findViewById(R.id.imageButtonAgenda);
        imageButtonEmail = findViewById(R.id.imageButtonEmail);
        imageButtonCam = findViewById(R.id.imageButtonCam);

        // Acciones del botón para llamada
        imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = editTextPhone.getText().toString();
                if(phoneNumber != null && !phoneNumber.isEmpty()) {
                    // Comprobar versión actual de Android que esté ejecutando
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[] {Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        olderVersions(phoneNumber);
                    }
                } else {
                    Toast.makeText(ThirdActivity.this, "Insert a phone number", Toast.LENGTH_LONG).show();
                }
            }

            private void olderVersions(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if(checkPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "You declined the access", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Acciones del botón para web
        imageButtonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editTextWeb.getText().toString();
                if(url != null && !url.isEmpty()) {
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + url));
                    startActivity(intentWeb);
                } else {
                    Toast.makeText(ThirdActivity.this, "Insert a web URL", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Acciones del botón para contactos
        imageButtonAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentContacts = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
                startActivity(intentContacts);
            }
        });

        // Acciones del botón para email
        imageButtonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextMail.getText().toString();
                if(email != null && !email.isEmpty()) {
                    // Email rápido
                    Intent intentMailTo = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
                    // Email mas completo
                    Intent intentMailToAdv = new Intent(Intent.ACTION_SEND, Uri.parse(email));
                    intentMailToAdv.setType("plain/text");
                    intentMailToAdv.putExtra(Intent.EXTRA_SUBJECT, "Mail's title");
                    intentMailToAdv.putExtra(Intent.EXTRA_TEXT, "Hi there, this is a form app...");
                    intentMailToAdv.putExtra(Intent.EXTRA_EMAIL, new String[] {"prueba@gmail.com", "test@outlook.com"});
                    startActivity(Intent.createChooser(intentMailToAdv, "Choose an email client"));
                } else {
                    Toast.makeText(ThirdActivity.this, "Insert an email", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Acciones del botón para cámara
        imageButtonCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCam = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intentCam);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            // Caso de la petición de permisos de teléfono
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];
                if(permission.equals(Manifest.permission.CALL_PHONE)) {
                    // Comprobar si ha sido aceptada o denegada la petición de permiso
                    if(result == PackageManager.PERMISSION_GRANTED) {
                        // Permiso concedido
                        String phoneNumber = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                        /* Telefono mejorado
                        Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                         */
                        startActivity(intentCall);
                    } else {
                        // No concedido
                        Toast.makeText(ThirdActivity.this, "You declined the access", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private boolean checkPermission(String permission) {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

}