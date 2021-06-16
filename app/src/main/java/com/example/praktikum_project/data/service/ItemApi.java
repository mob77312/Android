package com.example.praktikum_project.data.service;

import android.util.Log;

import com.example.praktikum_project.data.Item;
import com.example.praktikum_project.data.ItemListResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemApi {
    public static final String BASE_URL = "https://www.thesportsdb.com/";

    // inisialisasi Retrofit sebagai library untuk mengakses API
    private Retrofit retrofit = null;

    // fungsi untuk membuat retrofit dan mengkoneksikan dengan request yang ada di ItemApiInterface
   ItemApiInterface getItemApi() {
        if (retrofit == null) {
            // membangun atau instansiasi data retrofit
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ItemApiInterface.class);
    }

    // mengambil list Item dari API
    public void getListItem(ItemListener<ArrayList<Item>> listener) {
        // eksekusi pemanggilan request ke API
        getItemApi().getListItem().enqueue(new Callback<ItemListResponse>() {
            // ketika berhasil mengambil data
            @Override
            public void onResponse(Call<ItemListResponse> call, Response<ItemListResponse> response) {
                Log.d("response",response.body().toString());
                try {
                    // menyimpan hasil pengambilan data
                    ItemListResponse itemResponse = response.body();
                    if (itemResponse != null) {
                        // menyimpan list data dari nested data yang berasal dari API
                        ArrayList<Item> listItem = itemResponse.getResults();
                        // mengirimkan data melalui Callback / jembatan listener
                        listener.onSuccess(listItem);
                    }
                } catch (Exception e) {
                    // mengirimkan pesan error melalui Callback / jembatan listener
                    listener.onFailed(e.getMessage());
                }
            }

            // ketika gagal atau error pada saat mengambil data
            @Override
            public void onFailure(Call<ItemListResponse> call, Throwable t) {
                // mengirimkan pesan error melalui Callback / jembatan listener
                Log.d("failed",t.getMessage());
                listener.onFailed(t.getMessage());
            }
        });

        }
    // mengambil detail dari API berdasarkan id
    public void getDetailItem(ItemListener<Item> listener, String idLeague) {
        // eksekusi pemanggilan request ke API
        getItemApi().getDetailItem(idLeague).enqueue(new Callback<Item>() {
            // ketika berhasil mengambil data
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                try {
                    // menyimpan hasil pengambilan data
                    Item item = response.body();
                    if (item != null) {
                        // mengirimkan data melalui Callback / jembatan listener
                        listener.onSuccess(item);
                    }
                } catch (Exception e) {
                    // mengirimkan pesan error melalui Callback / jembatan listener
                    listener.onFailed(e.getMessage());
                }
            }

            // ketika gagal atau error pada saat mengambil data
            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                // mengirimkan pesan error melalui Callback / jembatan listener
                listener.onFailed(t.getMessage());
            }
        });
    }
}

