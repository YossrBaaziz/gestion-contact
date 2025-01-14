package com.example.gestioncontact;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;


import java.util.ArrayList;

public class Affiche extends AppCompatActivity {

    RecyclerView rvAff;
    private DatabaseHelper databaseHelper;
    MyContactRecyclerAdaptor adapter;

    private SearchView searchView;
    ArrayList<Contact> contactsList= new ArrayList<Contact>();

    private static final int CALL_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_affiche);

        // Check for call permission
        if (ContextCompat.checkSelfPermission(
                this,
           Manifest.permission.CALL_PHONE
        )
                != PackageManager.PERMISSION_GRANTED) {

            // Request permission
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.CALL_PHONE

                    }, CALL_PERMISSION_REQUEST_CODE);
        }

        rvAff = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchView);
        databaseHelper = new DatabaseHelper(this);

        // Set LayoutManager for RecyclerView
        rvAff.setLayoutManager(new LinearLayoutManager(this));

        // Fetch all contacts and display them
        displayContacts();

        // Set up SearchView listener for dynamic searching
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Optionally handle search submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter contacts as the user types
                filterContacts(newText);
                return true;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    //    rvAff=Affiche.this.findViewById(R.id.rv);

       // ContactManager manager=new ContactManager(Affiche.this);
       // manager.ouvrir();

       //manager.ajout("abc", "efg","10" );
      //  data.add(new Contact(3, "bbb", "mmm","33"));


        //data=manager.getAllProduit();
      ;  //ArrayAdapter ad = new ArrayAdapter(Affiche.this, android.R.layout.simple_list_item_1, data );

       // MyContactRecyclerAdapter ad=
       //         new MyContactRecyclerAdapter(Affiche.this, data);

     /*   MyContactRecyclerAdaptor ad= new MyContactRecyclerAdaptor(Affiche.this,data);

        //MonAdapter ad = new MonAdapter(Affiche.this,  data );

        rvAff.setAdapter(ad);

        //affichage
        LinearLayoutManager layoutmanager=new LinearLayoutManager(Affiche.this,
                LinearLayoutManager.VERTICAL,true);
        rvAff.setLayoutManager(layoutmanager);

        GridLayoutManager layoutmanager2=new GridLayoutManager(Affiche.this,2,
                GridLayoutManager.VERTICAL,true);

        rvAff.setLayoutManager(layoutmanager2);

*/
    }


    private void displayContacts() {
        contactsList = new ArrayList<>(databaseHelper.getAllContacts());  // Fetch contacts from DB

        adapter = new MyContactRecyclerAdaptor(this, contactsList);  // Set contacts to adapter
        rvAff.setAdapter(adapter);  // Set adapter to RecyclerView
    }

    // Method to filter contacts based on user input
    private void filterContacts(String query) {
        ArrayList<Contact> filteredContacts = new ArrayList<>();
        for (Contact contact : contactsList) {
            if (contact.getNom().toLowerCase().contains(query.toLowerCase()) ||
                    contact.getPseudo().toLowerCase().contains(query.toLowerCase()) ||
                    contact.getNumero().contains(query)) {
                filteredContacts.add(contact);  // Add matching contacts to the filtered list
            }
        }

        adapter.filterList(filteredContacts);  // Update adapter with the filtered list
    }

    // Handle the permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can now make phone calls
                Toast.makeText(this, "Permission granted. You can now make calls.", Toast.LENGTH_SHORT).show();
            } else {
                // Permission denied
                Toast.makeText(this, "Permission denied. You cannot make calls.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

 /*
    @Override
    protected void onStart(){
        super.onStart();
    }

}*/