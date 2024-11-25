package com.example.gestioncontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactManager {

    SQLiteDatabase db=null;

    Context con;
    ContactManager(Context con){
        this.con=con;
    }

    public void ouvrir(){

        ContactHelper helper =new ContactHelper(con,"mabase.db",null,2);
        db=helper.getWritableDatabase();
    }

    public long ajout(String intit, String des, double pr, int qte){

        long a=0;
        ContentValues values= new ContentValues();
        values.put(ContactHelper.col_intitule,intit);
        values.put(ContactHelper.col_quantite,qte);
        values.put(ContactHelper.col_designation,des);
        values.put(ContactHelper.col_prix,pr);

        a = db.insert(ContactHelper.table_produit,null,values);

        return a;


    }
    public ArrayList<Contact> getAllProduit(){
        ArrayList<Contact> l=new ArrayList<Contact>();
        Cursor cr=db.query(ContactHelper.table_produit,
                new String[]{ContactHelper.col_id, ContactHelper.col_intitule,
                ContactHelper.col_designation,
                ContactHelper.col_prix,
                ContactHelper.col_quantite},null,null,null,null,null
                );

        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            int i1 = cr.getInt(0);
            String i2 = cr.getString(1);
            String i3 = cr.getString(2);


          //  l.add(new Contact(i1, i2, i3, i4, i5));
            cr.moveToNext();
        }
        return l;

    }

    public void supprimer(){

    }

    public void fermer(){

    }

}
