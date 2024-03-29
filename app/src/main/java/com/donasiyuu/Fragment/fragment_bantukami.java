package com.donasiyuu.Fragment;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.donasiyuu.Adapter.ImageAdapter;
import com.donasiyuu.R;
import com.donasiyuu.Upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class fragment_bantukami extends Fragment {


    private static final int pilih_gambar = 1;
    private Button mpilihgambar;
    private Button muploadgambar;
    private TextView mlihathasilgambar;
    private EditText medittextnamalengkap;
    private EditText meditusia;
    private EditText meditalamatlengkap;
    private ImageView mphotodiri;
    private ProgressBar mProgressBar;

    private Uri mGambarUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_bantukami, container, false);

        Button mpilihgambar = v.findViewById(R.id.pilihgambar);
        Button muploadgambar = v.findViewById(R.id.uploadgambar);
        mphotodiri = (ImageView) v.findViewById(R.id.mphotodiri);

        medittextnamalengkap = (EditText) v.findViewById(R.id.editText);
        meditusia = (EditText) v.findViewById(R.id.editText2);
        meditalamatlengkap = (EditText) v.findViewById(R.id.editText4);
        mProgressBar = (ProgressBar) v.findViewById(R.id.progressBar);

        mStorageRef = FirebaseStorage.getInstance().getReference("Photo");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Photo");


        mpilihgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfilechooser();

            }
        });

        muploadgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress())
                {
                    Toast.makeText(getContext(),"Upload Sedang Berjalan", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    uploadFile();
                }

            }
        });


        return v;
    }


    private void openfilechooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, pilih_gambar);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == pilih_gambar && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
            mGambarUri = data.getData();
            Picasso.get().load(mGambarUri).fit().into(mphotodiri);
        }
    }

    private String getFileExtension(Uri uri)
    {
        ContentResolver cR = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile()
    {
        if (mGambarUri != null) {
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mGambarUri));

            mUploadTask = fileReference.putFile(mGambarUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri>urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadurl = urlTask.getResult();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(getContext(), "Upload successful", Toast.LENGTH_LONG).show();
                            Upload upload = new Upload(medittextnamalengkap.getText().toString(),
                                    meditusia.getText().toString(),
                                    meditalamatlengkap.getText().toString(),
                                    downloadurl.toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(getContext(), "Tidak Ada File", Toast.LENGTH_SHORT).show();
        }
    }
}
