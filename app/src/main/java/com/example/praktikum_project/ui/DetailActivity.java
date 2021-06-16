package com.example.praktikum_project.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.praktikum_project.R;
import com.example.praktikum_project.data.Item;
import com.example.praktikum_project.data.service.ItemApi;
import com.example.praktikum_project.data.service.ItemListener;


public class DetailActivity extends AppCompatActivity {

    // deklarasi kunci / key untuk pengiriman data id
    public static final String EXTRA_ID = "extra_id";

    // membuat variabel konstanta untuk melengkapi link yang kurang
    private static final String IMAGE_URL = "https://www.thesportsdb.com/images/media/league/badge/";

    // deklarasi variabel view sesuai dengan layout xml
    private TextView tvTitle, tvOriginalTitle, tvReleaseDate, tvOverview;
    private ImageView ivPoster;

    // inisialisasi Callback
    private ItemListener<Item> detailItemListener = new ItemListener<Item>() {
        // menerima data ketika berhasil
        @Override
        public void onSuccess(Item body) {
            // memanggil method untuk menampilkan data ke dalam view (tampilan)
            // dengan parameter data hasil dari request API
            populateView(body);
        }

        // menerima data ketika gagal
        @Override
        public void onFailed(String message) {
            // menampilkan notif message
            Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // mengganti tulisan di action bar (paling atas)
        getSupportActionBar().setTitle("Detail");
        // memunculkan tombol back di action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // menerima id yang dikirim dari HomeActivity
        String idLeague = getIntent().getStringExtra(EXTRA_ID);

        // koneksikan variabel dengan view berdasarkan id
        tvTitle = findViewById(R.id.tv_detail_title);
        tvOriginalTitle = findViewById(R.id.tv_detail_original_title);
        tvReleaseDate = findViewById(R.id.tv_detail_release);
        tvOverview = findViewById(R.id.tv_detail_overview_text);
        ivPoster = findViewById(R.id.iv_detail_poster);

        // mengambil data dari data sementara (data dummy) berdasarkan id
        // inisialisasi API
        ItemApi itemApi = new ItemApi();
        // memanggil method getDetailMovie untuk request data API
        // dengan menggunakan Callback / jembatan detailMovieListener
        // dan mengirimkan id yang spesifik
        itemApi.getDetailItem(detailItemListener,idLeague);

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

    // untuk menampilkan data ke dalam view (tampilan)
    private void populateView(Item item) {
        // menampilkan text ke dalam view item
        tvTitle.setText(item.getStrLeague());
       // tvOriginalTitle.setText(item.getOriginalTitle());
        tvReleaseDate.setText("Release on " + item.getStrCurrentSeason());
        tvOverview.setText(item.getStrDescriptionEN());

        // mengambil dan menampilkan image berdasarkan link ke dalam view item
        Glide.with(this)
                .load(IMAGE_URL)
                .into(ivPoster);
    }
}
