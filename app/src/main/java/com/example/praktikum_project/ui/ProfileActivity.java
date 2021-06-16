package com.example.praktikum_project.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.praktikum_project.R;

public class ProfileActivity extends AppCompatActivity {

    // inisialisasi key pada data yang akan dikirim lewat intent
    private Button btnInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // mengganti tulisan di action bar (paling atas)
        getSupportActionBar().setTitle("Profile");

        // memunculkan tombol back di action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // koneksikan variabel dengan view berdasarkan id
        btnInstagram = findViewById(R.id.btn_instagram);

        // inisialisasi fungsi klik pada tombol
        btnInstagram.setOnClickListener(v -> {
            // deklarasi intent untuk pindah halaman ke link yang disertakan
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/jerrycho_yudhistira25/"));
            // memulai pindah halaman
            startActivity(intent);
        });
    }

    // membuat aksi pada tombol yang ada di action bar (paling atas)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // identifikasi tombol mana yang diklik berdasarkan id
        switch (item.getItemId()) {
            // tombol back pada action bar
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
