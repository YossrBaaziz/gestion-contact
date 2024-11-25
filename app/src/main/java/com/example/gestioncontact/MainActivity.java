package com.example.gestioncontact;

import android.content.Intent;
import android.content.SharedPreferences;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnval,btnquitter;
    EditText edpwd;
    EditText ednom,edmp;

    private CheckBox checkBoxRememberMe;  // Declare CheckBox
    SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "PrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mettre un xml sur l'ecran
        //R hya ressource
        setContentView(R.layout.activity_main);

        //recuperation des composantes
        btnval=findViewById(R.id.btnvalider_auth);
        btnquitter=findViewById(R.id.btnquitter_auth);
        ednom=findViewById(R.id.ednom_auth);
        edpwd=findViewById(R.id.edpwd_auth);

        checkBoxRememberMe = findViewById(R.id.checkbox_remember_me);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Check if preferences were saved before
        String savedUser = sharedPreferences.getString("user", null);
        if (savedUser != null) {
            Intent i = new Intent(MainActivity.this, Accueil.class);
            i.putExtra("USER", savedUser);
            startActivity(i);
            finish();  // Close the login activity
        }
        //evennements
        btnquitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });
        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chntesti 3l nom w pwd ken s7a7 ou nn
                String nom=ednom.getText().toString();
                String mp=edpwd.getText().toString();
                if(nom.equalsIgnoreCase("asma") && mp.equals("000"))
                {
                    Intent i = new Intent(MainActivity.this, Accueil.class);
                    i.putExtra("USER", nom);
                    startActivity(i);
                    finish();  // Close the login activity so the user can't go back

                    // Save login state if "Remember Me" is checked
                    if (checkBoxRememberMe.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user", nom);
                        editor.apply();  // Save user
                    }

                } else {
                    Toast.makeText(MainActivity.this, "valeur non valide", Toast.LENGTH_LONG).show();
                }
            }
        });
}}