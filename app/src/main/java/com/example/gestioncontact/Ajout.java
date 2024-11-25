package com.example.gestioncontact;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ajout extends AppCompatActivity {
    EditText ednom, edps, ednum;

    Button btnaj, btnquitter;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajout);

        btnaj=findViewById(R.id.btnajouter_ajout);
        btnquitter=findViewById(R.id.btnquitter_ajout);
        ednom=findViewById(R.id.ednom_aj);
        ednum=findViewById(R.id.ednum_aj);
        edps=findViewById(R.id.edps_aj);

        //events
        btnquitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ajout.this.finish();
            }
        });

        // Initialize the database helper
        databaseHelper = new DatabaseHelper(this);


        btnaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nom= ednom.getText().toString();
                String pseudo=edps.getText().toString();
                String numero=ednum.getText().toString();


                if (nom.isEmpty() || pseudo.isEmpty() || numero.isEmpty()) {
                    Toast.makeText(Ajout.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact(nom, pseudo, numero);
                    databaseHelper.addContact(contact); // Save to database

                    Log.d("Ajout", "Contact saved: " + contact.toString());

                    // Clear input fields
                    ednom.setText("");
                    edps.setText("");
                    ednum.setText("");
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}