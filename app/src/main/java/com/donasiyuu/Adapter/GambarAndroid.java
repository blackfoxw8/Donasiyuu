package com.donasiyuu.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.donasiyuu.R;

public class GambarAndroid extends RecyclerView.Adapter<GambarAndroid.ViewHolder> {

    private int[] datagambar;

    public  GambarAndroid(){
        datagambar = new int[30];
        for (int i = 0; i < 30; i++)
        {
            datagambar[i] = R.drawable.donation;
        }
    }
    @NonNull
    @Override
    public GambarAndroid.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GambarAndroid.ViewHolder holder, int position) {

        holder.imageView.setImageResource(datagambar[position]);
    }

    @Override
    public int getItemCount() {

        return datagambar.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imgView);
        }


    }
}
