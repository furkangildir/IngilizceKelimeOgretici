package com.example.furkan.ingilizceKelimeOgretici;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class ingilizceKelimeOgretici extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btnEkle=(Button)findViewById(R.id.btnKelimeEkle);
        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent niyetOgren = new Intent(ingilizceKelimeOgretici.this, kelimeEkle.class);
                startActivity(niyetOgren);
            }
        });
        Button btnGoster=(Button)findViewById(R.id.btnKelimeOgren);
        btnGoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent niyetOgren1=new Intent(ingilizceKelimeOgretici.this,KelimeGoster.class);
                startActivity(niyetOgren1);
            }
        });


        Button btnKelimeleriGor=(Button)findViewById(R.id.btnKelimeleriGör);

        btnKelimeleriGor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent niyetGor=new Intent(ingilizceKelimeOgretici.this,kelimeleriGor.class);
                startActivity(niyetGor);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deneme, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
