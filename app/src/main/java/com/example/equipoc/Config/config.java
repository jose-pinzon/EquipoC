package com.example.equipoc.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



//configuracion para conetarnos a nuestra API
public class config {
    private static final String _BASEURL = "http://192.168.1.110/mascotas/public/api/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(_BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
