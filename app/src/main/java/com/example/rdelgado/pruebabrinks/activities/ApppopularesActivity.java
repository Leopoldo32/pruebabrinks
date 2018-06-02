package com.example.rdelgado.pruebabrinks.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.rdelgado.pruebabrinks.R;

public class ApppopularesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apppopulares);

        getSupportActionBar().hide();

        //recibimos los datos
        String nombreapp = getIntent().getExtras().getString("app_name");
        String categoria = getIntent().getExtras().getString("app_category");
        String idapp = getIntent().getExtras().getString("app_id");
        String studio = getIntent().getExtras().getString("app_studio");
        String descripcion = getIntent().getExtras().getString("descripcion");
        String imagenurl = getIntent().getExtras().getString("img_thumbnail");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView nombrevista = findViewById(R.id.aa_apppopulares_name);
        TextView categoriavista = findViewById(R.id.aa_apppopulares_categoria);
        TextView idvista = findViewById(R.id.aa_apppopulares_id);
        TextView studiovista = findViewById(R.id.aa_apppopulares_studio);
        TextView descripcionvista = findViewById(R.id.aa_descripcion);
        ImageView imagenvista = findViewById(R.id.aa_thumbnail);


        nombrevista.setText(nombreapp);
        categoriavista.setText(categoria);
        idvista.setText(idapp);
        studiovista.setText(studio);
        descripcionvista.setText(descripcion);

        collapsingToolbarLayout.setTitle(nombreapp);
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        //imagen
        Glide.with(this).load(imagenurl).apply(requestOptions).into(imagenvista);



    }
}
