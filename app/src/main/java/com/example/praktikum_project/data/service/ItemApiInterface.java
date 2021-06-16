package com.example.praktikum_project.data.service;

import com.example.praktikum_project.data.Item;
import com.example.praktikum_project.data.ItemListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ItemApiInterface {
    // request ambil data dari API dengan link tertentu
    // GET sebagai metode akses yaitu mengambil data
    // Call untuk memanggil object untuk menampung data yang diambil
    // Path untuk membuat link menjadi dinamis
    @GET("api/v1/json/1/search_all_leagues.php?c=England&s=Soccer")
    Call<ItemListResponse> getListItem();

    @GET("api/v1/json/1/lookupleague.php?id={item_id}")
    Call<Item> getDetailItem(@Path("item_id") String idLeague);


}
