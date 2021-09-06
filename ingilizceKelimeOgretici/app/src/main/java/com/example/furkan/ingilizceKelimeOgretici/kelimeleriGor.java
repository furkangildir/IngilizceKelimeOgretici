package com.example.furkan.ingilizceKelimeOgretici;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class kelimeleriGor extends AppCompatActivity {
    private Veritabani vtKelimeler;
    ArrayList<String> basliklistesi;
    ListView basliklar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelimeleri_gor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         //yapılandırıcıyla beraber nesne oluşturduk.
        basliklar=(ListView)findViewById(R.id.lwKelimeler);
        Cursor c=kayitcek();
        kayitlist(c);

        Button btnTumKelimeleriSil=(Button)findViewById(R.id.btnTumKelimeleriSil);
btnTumKelimeleriSil.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        SQLiteDatabase db= vtKelimeler.getReadableDatabase();
        db.execSQL("delete from tblKelimeler");
        db.execSQL("delete from sqlite_sequence where name='tblKelimeler'");
        Cursor c=kayitcek();
        kayitlist(c);
    }
});

}
    protected Cursor kayitcek() {
        vtKelimeler=new Veritabani(this);
        SQLiteDatabase db=vtKelimeler.getReadableDatabase();
        Cursor c=null;
        c=db.query("tblKelimeler",null, null, null, null, null, null);
        Log.d("Kayıt Getir", "Hata alındı");
        return c;
    }
    public void kayitlist(Cursor cursor)
    {
        basliklistesi=new ArrayList<String>();
        String ingilizceKelime="";
        String turkceKelime="";
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            ingilizceKelime = cursor.getString(cursor.getColumnIndex("ingKel"));
            turkceKelime = cursor.getString(cursor.getColumnIndex("turKel"));
            basliklistesi.add(id+". "+ingilizceKelime+" : "+turkceKelime);

        }

        basliklar.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, basliklistesi));//listview içerisine aktardık.
    }
}
