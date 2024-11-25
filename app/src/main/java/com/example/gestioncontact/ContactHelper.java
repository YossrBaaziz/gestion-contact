package com.example.gestioncontact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactHelper extends SQLiteOpenHelper {

    public static final String table_produit="Produits";
    public static final String col_id="ID";
    public static final String col_intitule="Intitule";
    public static final String col_designation="Designation";
    public static final String col_prix="Prix";
    public static final String col_quantite="Quantite";

    String requete="create table "+table_produit+" ("
            +col_id+" Integer Primary key Autoincrement,"+
            col_intitule+" Text not null,"+
            col_designation+" Text not null,"+
            col_prix+" Reel, "+
            col_quantite+" Integer)";

    public ContactHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(requete);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(" drop table "+table_produit);
        onCreate(sqLiteDatabase);

    }
}
