package com.example.rdelgado.pruebabrinks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.rdelgado.pruebabrinks.R;
import com.example.rdelgado.pruebabrinks.adapters.RecyclerViewAdapter;
import com.example.rdelgado.pruebabrinks.model.Apppopulares;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "https://itunes.apple.com/us/rss/toppaidapplications/limit=20/json";
    //private JsonArrayRequest request;
    private JsonObjectRequest request;
    private RequestQueue requestQueue;
    private List<Apppopulares> lstApppopulares;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstApppopulares = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();
    }
    private void jsonrequest(){

        request = new JsonObjectRequest(JsonRequest.Method.POST, JSON_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //String jsonArray = response.optString("feed");

                    try{
                        JSONObject feed = response.getJSONObject("feed");
                        JSONArray entry = feed.getJSONArray("entry");

                        for (int i = 0; i <entry.length(); i++){

                            try{
                                JSONObject arraydata = entry.getJSONObject(i);
                                JSONObject nombre = arraydata.getJSONObject("im:name");
                                JSONArray imagenArray = arraydata.getJSONArray("im:image");
                                JSONObject imagen = imagenArray.getJSONObject(0);
                                JSONObject descripcion = arraydata.getJSONObject("summary");
                                JSONObject precio = arraydata.getJSONObject("im:price");
                                JSONObject tipocontenido = arraydata.getJSONObject("im:contentType");
                                JSONObject derechosautor = arraydata.getJSONObject("rights");
                                JSONObject tituloapp = arraydata.getJSONObject("title");
                                JSONObject link = arraydata.getJSONObject("link");
                                JSONObject id = arraydata.getJSONObject("id");
                                JSONObject idatributo = id.getJSONObject("attributes");
                                JSONObject artista = arraydata.getJSONObject("im:artist");
                                JSONObject categoriaarray = arraydata.getJSONObject("category");
                                JSONObject categoria = categoriaarray.getJSONObject("attributes");
                                JSONObject fecha = arraydata.getJSONObject("im:releaseDate");

                                Apppopulares apppopulares = new Apppopulares();
                                apppopulares.setImg_name_label(nombre.getString("label"));
                                apppopulares.setCategory_attributes_label(categoria.getString("label"));
                                apppopulares.setId_atributes_im_id(idatributo.getString("im:id"));
                                apppopulares.setIm_artist_label(artista.getString("label"));
                                apppopulares.setImage_label(imagen.getString("label"));
                                apppopulares.setSummary_label(descripcion.getString("label"));
                                lstApppopulares.add(apppopulares);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                setuprecyclerview(lstApppopulares);
            }
        }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }

        );

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    private void setuprecyclerview(List<Apppopulares> lstApppopulares) {

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstApppopulares);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}

