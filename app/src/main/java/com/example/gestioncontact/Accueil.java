package com.example.gestioncontact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Accueil extends AppCompatActivity {


    TextView tvusername;
    Button btnajout, btnaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_accueil);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        tvusername=findViewById(R.id.tvuser_acc);
        btnajout=findViewById(R.id.btnajout_acc);
        btnaff=findViewById(R.id.btnaff_acc);

        Intent x=this.getIntent();
        Bundle b=x.getExtras();
        String u=b.getString("USER");

        tvusername.setText("Accueil de " +u);

        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Accueil.this,Ajout.class);
                startActivity(i);


            }
        });

        btnaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Accueil.this,Affiche.class);
                startActivity(i);
            }
        });
    }
}