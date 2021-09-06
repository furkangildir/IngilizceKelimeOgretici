package com.example.furkan.ingilizceKelimeOgretici;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;

public class kelimeEkle extends AppCompatActivity {
    private Veritabani vtKelimeler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelime_ekle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        vtKelimeler =new Veritabani(this);
        Button verigonder=(Button)findViewById(R.id.btnEkle);
        verigonder.setOnClickListener(new View.OnClickListener() {
            final EditText ingKelime=(EditText)findViewById(R.id.etIngKelime);
            final EditText turkKelime=(EditText)findViewById(R.id.etTurkKelime);
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = vtKelimeler.getReadableDatabase();
                Cursor cursor = db.query("tblKelimeler", null, null, null, null, null, null, null);
                String ingilizceKelime = "";
                String turkceKelime = "";
                boolean varyok = false;
                while (cursor.moveToNext()) {
                    ingilizceKelime = cursor.getString(cursor.getColumnIndex("ingKel"));
                    turkceKelime = cursor.getString(cursor.getColumnIndex("turKel"));
                    if (ingKelime.getText().toString().equals(ingilizceKelime)) {
                        varyok = true;
                        Toast.makeText(getApplicationContext(), "Girilen kelime veritabanında bulunmaktadır", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                if (varyok == false) {
                    if (!(ingKelime.getText().toString().equals("") || turkKelime.getText().toString().equals(""))) {

                        try {

                            KayitEkle(ingKelime.getText().toString(), turkKelime.getText().toString());
                            Toast.makeText(getApplicationContext(), "Kelime Eklendi!", Toast.LENGTH_SHORT).show();
                            ingKelime.setText("");
                            turkKelime.setText("");
                        } catch (Exception ex) {

                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Kelimeleri girmediniz!", Toast.LENGTH_SHORT).show();
                        vtKelimeler.close();
                    }
                }
                ingKelime.setText("");
                turkKelime.setText("");
            }
        });


    }

    private void KayitEkle(String s, String s1) {
        SQLiteDatabase db= vtKelimeler.getWritableDatabase();
        ContentValues veriler=new ContentValues();
        veriler.put("ingKel", s);
        veriler.put("turKel", s1);
        db.insertOrThrow("tblKelimeler", null, veriler);
    }
    }

