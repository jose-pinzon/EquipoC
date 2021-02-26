package com.example.equipoc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.equipoc.Config.config;
import com.example.equipoc.Interface.retrofitInterface;
import com.example.equipoc.Modelos.mascota;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListadoMascota extends AppCompatActivity {
    EditText editCtext;
    Button btnCconsultar;
    ListView LvCmascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_mascota);
        editCtext = findViewById(R.id.editCtextb);
        btnCconsultar = findViewById(R.id.btnCconsultar);
        LvCmascotas = findViewById(R.id.LvCmascotas);
        getMascotas();


        //funcion del bonton consultar
        btnCconsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editCtext.getText().toString().equals("")) {
                    getByFiltro(editCtext.getText().toString());
                }
            }
        });
    }

    //para poder obtener los datos de la mascota
    private void getMascotas(){
        retrofitInterface cliente = config.getRetrofit().create(retrofitInterface.class);
        Call<List<mascota>> call =cliente.getMascotas("apiAnimales");//acompletar la url que usaremos
        call.enqueue(new Callback<List<mascota>>() {
            @Override
            public void onResponse(Call<List<mascota>> call, Response<List<mascota>> response) {
                ArrayList<String> data = new ArrayList<String>();
                for (mascota datamascota :response.body()){//para recorrer todos los datos que tengamos guardados
                    data.add(datamascota.getNombre());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_activated_1,data);
                LvCmascotas.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<mascota>> call, Throwable t) {

            }
        });
    }//fin de GET mascota


    //Funcion para poder hacer el filtro de la mascota
    private void getByFiltro(String nombre){
        retrofitInterface buscarAnimal = config.getRetrofit().create(retrofitInterface.class);
        Call<List<mascota>> call =  buscarAnimal.getMascotas("buscar/" + nombre);//acompretar la url que usaremos para buscar
        call.enqueue(new Callback<List<mascota>>() {
            @Override
            public void onResponse(Call<List<mascota>> call, Response<List<mascota>> response) {
                ArrayList<String> data = new ArrayList<String>();
                for (mascota datamascota :response.body()){ //recorrer los datos
                    data.add(datamascota.getNombre());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_activated_1,data);
                LvCmascotas.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<mascota>> call, Throwable t) {

            }
        });
    }
}