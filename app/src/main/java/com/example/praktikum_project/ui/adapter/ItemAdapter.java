package com.example.praktikum_project.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.praktikum_project.R;
import com.example.praktikum_project.data.Item;
import com.example.praktikum_project.ui.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.praktikum_project.ui.DetailActivity.EXTRA_ID;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    // deklarasi list yang akan ditampilkan
    private ArrayList<Item> items = new ArrayList<>();
    // mengisi data list yang akan di tampilkan
    public void setItems(ArrayList<Item> items) {
        if (items != null) {
            this.items.clear();
            this.items.addAll(items);
        }
        // update tampilan jika terdapat perubahan data
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // mengkoneksikan layout item kedalam kelas adapter ini
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // mengirimkan data tiap satuan item ke kelas ViewHolder untuk ditampilkan
        holder.bind(items.get(position));
    }

    // mengambil panjang list yang ada
    @Override
    public int getItemCount() {
        return items.size();
    }

    // membuat kelas sebagai kontrol item di dalam view (list_item.xml) agar dapat dimanipulasi
    public class ViewHolder extends RecyclerView.ViewHolder {
        // membuat variabel konstanta untuk melengkapi link yang kurang
        private static final String IMAGE_URL = "https://www.thesportsdb.com/images/media/league/badge/";

        // deklarasi variabel view sesuai dengan layout xml
        private TextView tvTitle;
        private ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // koneksikan variabel dengan view berdasarkan id
            tvTitle = itemView.findViewById(R.id.strLeague);
            ivImage = itemView.findViewById(R.id.strBadge);
        }

        public void bind(Item item) {
            // menampilkan text ke dalam view item
            tvTitle.setText(item.getStrLeague());
            // mengambil dan menampilkan image berdasarkan link ke dalam view item
            Glide.with(itemView.getContext())
                    .load(item.getStrBadge())
                    .into(ivImage);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                // mengirim data id ke detail dengan key tertentu (EXTRA_ID)
                intent.putExtra(EXTRA_ID, item.getIdLeague());
                // memulai pindah halaman dengan bantuan context dari itemView
                itemView.getContext().startActivity(intent);
            });

        }
    }
}

