package com.example.praktikum_project.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.praktikum_project.R;
import com.example.praktikum_project.data.Item;
import com.example.praktikum_project.data.service.ItemApi;
import com.example.praktikum_project.data.service.ItemListener;
import com.example.praktikum_project.ui.MainActivity;
import com.example.praktikum_project.ui.ProfileActivity;
import com.example.praktikum_project.ui.adapter.ItemAdapter;


import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    // deklarasi variabel view sesuai dengan layout xml
    private TextView welcome;
    private RecyclerView rvItem;

    // deklarasi adapter yang telah dibuat
    private ItemAdapter itemAdapter;
    // inisialisasi Callback
    private ItemListener<ArrayList<Item>> listItemListener = new ItemListener<ArrayList<Item>>() {
        // menerima data ketika berhasil
        @Override
        public void onSuccess(ArrayList<Item> body) {
            // memasukkan data ke dalam list
            itemAdapter.setItems(body);
        }

        // menerima data ketika gagal
        @Override
        public void onFailed(String message) {
            // menampilkan notif message
            Toast.makeText(HomeActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // mengganti tulisan di action bar (paling atas)
        getSupportActionBar().setTitle("Home");

        // koneksikan variabel dengan view berdasarkan id
        welcome = findViewById(R.id.tv_welcome);
        rvItem = findViewById(R.id.rv_item_list);

        // menerima kiriman data dari halaman sebelumnya menggunakan kuncinya (EXTRA_USERNAME)
        String username = getIntent().getStringExtra(MainActivity.EXTRA_USERNAME);

        // menampilkan text ke dalam view
        welcome.setText("Welcome, " + username);

        // menyambungkan adapter yang telah dibuat ke dalam variabel
        itemAdapter = new ItemAdapter();
        // memberi jenis leyout yang akan ditampilkan ke view
        rvItem.setLayoutManager(new LinearLayoutManager(this));
        // memasukkan adapter ke dalam view
        rvItem.setAdapter( itemAdapter);
        // inisialisasi API
        ItemApi itemApi = new ItemApi();
        // memanggil method getListItem untuk request data API
        // dengan menggunakan Callback / jembatan listItemListener
        itemApi.getListItem(listItemListener);

    }

    // memunculkan tombol yang ada di action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menghubungkan tombol-tombol dengan menu yang telah dibuat sebelumnya
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // membuat aksi pada tombol yang ada di action bar (paling atas)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // identifikasi tombol mana yang diklik berdasarkan id
        switch (item.getItemId()) {
            // tombol profile
            case R.id.action_profile:
                // deklarasi intent seperti biasa
                Intent intent = new Intent(this, ProfileActivity.class);
                // memulai pindah halaman
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}



