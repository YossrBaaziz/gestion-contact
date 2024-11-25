package com.example.gestioncontact;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class MonAdapter extends BaseAdapter {


    ArrayList<Contact> data;
    Context con;

    public MonAdapter(Context con,ArrayList<Contact> data) {
        this.data = data;
        this.con = con;
    }

    @Override
    public int getCount() { //nbr de views dans la list view qu'on va afficher
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        CardView l=null;

        //creer une seule view
        TextView t=new TextView(con);
        t.setText(data.get(position).toString());

        //creation view holder
        LayoutInflater inf=LayoutInflater.from(con); //convert xml to java
        View v = inf.inflate(R.layout.element,null);

        //Recuperation des holders
        TextView tvnom=v.findViewById(R.id.tvnom_contact);
        TextView tvpseudo=v.findViewById(R.id.tvpseudo_contact);
        TextView tvnumero=v.findViewById(R.id.tvnumero_contact);
        ImageView imgcall=v.findViewById(R.id.tvcall_contact);
        ImageView imgdelete=v.findViewById(R.id.ivdel_contact);
        ImageView imgedit=v.findViewById(R.id.ivedit_contact);

        //affectation holders
        Contact c=data.get(position);
        tvnom.setText(c.nom);
        tvpseudo.setText(c.pseudo);
        tvnumero.setText(c.numero);

        // EVENT
        imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.remove(position);//supprimer depuis la base
                notifyDataSetChanged();//Refresh
            }
        });


        imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setAction(Intent.ACTION_DIAL); //bch nbadloha baad ACTION8CALL w naamlou l permission
                i.setData(Uri.parse("tel:"+c.numero));
                con.startActivity(i);
            }
        });

        imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert=new AlertDialog.Builder(con);
                alert.setTitle("Modification");

                LayoutInflater inf=LayoutInflater.from(con);
                View v=inf.inflate(R.layout.view_dialog,null);
                alert.setView(v);
                alert.show();
            }
        });
        return v;
    }
}





