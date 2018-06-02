package com.example.rdelgado.pruebabrinks.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.rdelgado.pruebabrinks.activities.ApppopularesActivity;
import com.example.rdelgado.pruebabrinks.model.Apppopulares;
import com.example.rdelgado.pruebabrinks.R;
import java.util.List;

/**
 * Created by rdelgado on 2/06/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Apppopulares> mData;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Apppopulares> mData) {
        this.mContext = mContext;
        this.mData = mData;

        //Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.apppopulares_row_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(mContext, ApppopularesActivity.class);
                i.putExtra("app_name",mData.get(viewHolder.getAdapterPosition()).getImg_name_label());
                i.putExtra("app_category",mData.get(viewHolder.getAdapterPosition()).getCategory_attributes_label());
                i.putExtra("app_id",mData.get(viewHolder.getAdapterPosition()).getId_atributes_im_id());
                i.putExtra("app_studio",mData.get(viewHolder.getAdapterPosition()).getIm_artist_label());
                i.putExtra("descripcion",mData.get(viewHolder.getAdapterPosition()).getSummary_label());
                i.putExtra("img_thumbnail",mData.get(viewHolder.getAdapterPosition()).getImage_label());

                mContext.startActivity(i);

            }

        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.app_name.setText(mData.get(position).getImg_name_label());
        holder.app_category.setText(mData.get(position).getCategory_attributes_label());
        holder.app_id.setText(mData.get(position).getId_atributes_im_id());
        holder.app_studio.setText(mData.get(position).getIm_artist_label());

        //Cargar imagen
        Glide.with(mContext).load(mData.get(position).getImage_label()).apply(option).into(holder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView app_name;
        TextView app_id;
        TextView app_studio;
        TextView app_category;
        ImageView img_thumbnail;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            app_name = itemView.findViewById(R.id.apppopulares_name);
            app_id = itemView.findViewById(R.id.apppopulares_id);
            app_studio = itemView.findViewById(R.id.apppopulares_studio);
            app_category = itemView.findViewById(R.id.apppopulares_categoria);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
