package com.example.equipoc.Interface;

import com.example.equipoc.Modelos.mascota;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

//para poder hacer las llamadas de red Y obtener los resultados en json que se usaran
public interface retrofitInterface {

    @GET
    Call<List<mascota>> getMascotas(@Url String url);



}
