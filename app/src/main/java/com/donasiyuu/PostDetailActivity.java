package com.donasiyuu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PostDetailActivity extends AppCompatActivity {


    ImageView img ;
    TextView txt_post_detail_nama, txt_post_detail_usia, txt_post_detail_alamat;
    Button btn_form1;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        //Ini Views

        img = findViewById(R.id.post_detail_img);
        txt_post_detail_nama = findViewById(R.id.post_detail_nama);
        txt_post_detail_usia = findViewById(R.id.post_detail_usia);
        txt_post_detail_alamat = findViewById(R.id.post_detail_alamat);
        btn_form1 = findViewById(R.id.btn_form);

        // Pengisian Data
        String postimg = getIntent().getExtras().getString("imageView");
        Picasso.get().load(postimg).fit().centerCrop().into(img);

        String postnama = getIntent().getExtras().getString("textViewName");
        txt_post_detail_nama.setText(postnama);
        String postusia = getIntent().getExtras().getString("textViewUsia");
        txt_post_detail_usia.setText(postusia);
        String postalamat = getIntent().getExtras().getString("textViewAlamat");
        txt_post_detail_alamat.setText(postalamat);

        btn_form1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FormActivity.class);
                startActivity(intent);
            }
        });


    }
}
