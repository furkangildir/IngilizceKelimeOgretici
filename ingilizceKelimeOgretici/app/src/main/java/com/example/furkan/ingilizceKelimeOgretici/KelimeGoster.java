package com.example.furkan.ingilizceKelimeOgretici;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KelimeGoster extends AppCompatActivity {
    private Veritabani vtKelimeler;
    Button kontrolEt;
    Button verigetir;
    EditText girilenKelime;
    TextView textingKelime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelime_goster);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vtKelimeler = new Veritabani(this);
        verigetir = (Button) findViewById(R.id.btnKelimeGoster);
        final TextView kelimeAnlami = (TextView) findViewById(R.id.tvTurkceGoster);
        Cursor cursor = KayitGetir();
        KayitGoster(cursor);
        final TextView text1 = (TextView) findViewById(R.id.tvIngKelime);
        final TextView text2 = (TextView) findViewById(R.id.tvTurkceGoster);

        verigetir.setOnClickListener(new View.OnClickListener() {
            final Random rnd = new Random();
            @Override
            public void onClick(View v) {
                girilenKelime.setText("");
                textingKelime.setText("");
                kelimeAnlami.setVisibility(View.INVISIBLE);
                kontrolEt.setEnabled(true);
                try {



                    int kelimeSayisi = lsingKelime.size();
                    if(kelimeSayisi!=0) {
                        int rastgeleKelimeid = rnd.nextInt(kelimeSayisi);




                        text1.setText(lsingKelime.get(rastgeleKelimeid));
                        text2.setText(lsturKelime.get(rastgeleKelimeid));
                        lsturKelime.remove(rastgeleKelimeid);
                        lsingKelime.remove(rastgeleKelimeid);

                    }else{
                        Toast.makeText(getApplicationContext(),"Veritabanındaki kelimeler bitti", Toast.LENGTH_SHORT).show();
                        text1.setEnabled(false);
                        text2.setEnabled(false);
                        verigetir.setEnabled(false);
                        kontrolEt.setEnabled(false);
                    }







                } finally {
                    vtKelimeler.close();
                }
                verigetir.setEnabled(false);
                girilenKelime.setEnabled(true);
                textingKelime.setEnabled(true);

            }
        });

        kontrolEt = (Button) findViewById(R.id.btnKontrolEt);
        kontrolEt.setEnabled(false);
        girilenKelime = (EditText) findViewById(R.id.etKelimeGir);
        girilenKelime.setEnabled(false);

        final TextView tvDogruSayisi = (TextView) findViewById(R.id.tvDogru);
        final TextView tvYanlisSayisi = (TextView) findViewById(R.id.tvYanlis);
        textingKelime = (TextView) findViewById(R.id.tvIngKelime);
        textingKelime.setEnabled(false);
        kontrolEt.setOnClickListener(new View.OnClickListener() {
            int dogru = 0;
            int yanlis = 0;

            @Override
            public void onClick(View v) {

                String girilenKelimeString = girilenKelime.getText().toString().trim();
                String kelimeAnlamiString = kelimeAnlami.getText().toString().trim();
                if (girilenKelimeString.equals("")) {
                    Toast.makeText(getApplicationContext(), "Türkçe anlamı girmediniz", Toast.LENGTH_SHORT).show();
                } else {
                    if (girilenKelimeString.equals(kelimeAnlamiString)) {
                        Toast.makeText(getApplicationContext(), "DOĞRU", Toast.LENGTH_SHORT).show();
                        dogru += 1;
                        tvDogruSayisi.setText(String.valueOf(dogru));
                    } else {
                        Toast.makeText(getApplicationContext(), "YANLIŞ", Toast.LENGTH_SHORT).show();
                        yanlis += 1;
                        kelimeAnlami.setVisibility(View.VISIBLE);
                        tvYanlisSayisi.setText(String.valueOf(yanlis));
                    }

                    kontrolEt.setEnabled(false);
                    verigetir.setEnabled(true);
                }

            }
        });


    }

    private Cursor KayitGetir() {
        //
        SQLiteDatabase db = vtKelimeler.getReadableDatabase();
        Cursor cursor = db.query("tblKelimeler", null,null, null, null, null, null, null);
        return cursor;
    }


    List<String> lsingKelime = new ArrayList<String>();
    List<String> lsturKelime = new ArrayList<String>();


    private void KayitGoster(Cursor cursor) {

        String ingilizceKelime = "";
        String turkceKelime = "";


        while (cursor.moveToNext()) {
            ingilizceKelime = cursor.getString(cursor.getColumnIndex("ingKel"));
            turkceKelime = cursor.getString(cursor.getColumnIndex("turKel"));
            lsingKelime.add(ingilizceKelime);
            lsturKelime.add(turkceKelime);
        }




    }
}