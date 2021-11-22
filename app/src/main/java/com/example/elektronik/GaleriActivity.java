package com.example.elektronik;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import model.Elektronik;

import java.util.List;

public class GaleriActivity extends AppCompatActivity {
    List<Elektronik> elektroniks;
    int indeksTampil = 0;
    String jenisElextronik;
    Button btnSebelumnya,btnBerikutnya;
    TextView txJenis,txName,txDeskripsi,txJudul;
    ImageView ivFotoElektronik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        jenisElextronik = intent.getStringExtra(MainActivity.JENIS_GALERI_KEY);
        elektroniks = DataProvider.getElektroniksByTipe(this, jenisElextronik);
        inisialisasiView();
        tampilkanProfil();
    }

    private void inisialisasiView() {

        btnSebelumnya = findViewById(R.id.btnSebelumnya);
        btnBerikutnya = findViewById(R.id.btnBerikutnya);
        btnSebelumnya.setOnClickListener(view -> elektronikSebelumnya());
        btnBerikutnya.setOnClickListener(view -> elektronikBerikutnya());

        txJenis = findViewById(R.id.txJenis);
        txName = findViewById(R.id.txName);
        txDeskripsi = findViewById(R.id.txDeskripsi);
        ivFotoElektronik = findViewById(R.id.gambarElektronik);

        txJudul = findViewById(R.id.txJudul);
        txJudul.setText("Elektronik "+jenisElextronik);
    }

    private void tampilkanProfil() {
        Elektronik k = elektroniks.get(indeksTampil);
        Log.d("Televisi","Menampilkan Elektronik "+k.getElektronik());
        txJenis.setText(k.getElektronik());
        txName.setText(k.getNama());
        txJenis.setText(k.getJenis());
        txDeskripsi.setText(k.getDeskripsi());
        ivFotoElektronik .setImageDrawable(this.getDrawable(k.getDrawableRes()));
    }

    private void elektronikBerikutnya() {
        if (indeksTampil == elektroniks.size() - 1) {
            Toast.makeText(this,"Sudah di posisi terakhir",Toast.LENGTH_SHORT).show();
            return;
        } else {
            indeksTampil++;
            tampilkanProfil();
        }
    }

    private void elektronikSebelumnya() {
        if (indeksTampil == 0) {
            Toast.makeText(this,"Sudah di posisi pertama",Toast.LENGTH_SHORT).show();
            return;
        } else {
            indeksTampil--;
            tampilkanProfil();
        }
    }
}
