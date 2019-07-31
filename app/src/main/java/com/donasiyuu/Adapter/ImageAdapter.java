package com.donasiyuu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.donasiyuu.PostDetailActivity;
import com.donasiyuu.R;
import com.donasiyuu.Upload;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Upload> mUploads;


    public ImageAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getmName());
        Picasso.get()
                .load(uploadCurrent.getGambarUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }



    public class ImageViewHolder extends RecyclerView.ViewHolder  {
        public ImageView imageView;
        public TextView textViewName;

        public ImageViewHolder(View itemView){
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view_upload);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postdetailactivity = new Intent(mContext, PostDetailActivity.class);
                    int position = getAdapterPosition();

                    postdetailactivity.putExtra("imageView",mUploads.get(position).getGambarUrl());
                    postdetailactivity.putExtra("textViewName",mUploads.get(position).getmName());
                    postdetailactivity.putExtra("textViewUsia",mUploads.get(position).getmUsia());
                    postdetailactivity.putExtra("textViewAlamat",mUploads.get(position).getmAlamat());

                    mContext.startActivity(postdetailactivity);
                }
            });

        }

    }

}
