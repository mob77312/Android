package com.example.praktikum_project.data.service;

import com.example.praktikum_project.data.ItemListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemListener<T> {
    void onSuccess(T body);
    void onFailed(String message);
}
