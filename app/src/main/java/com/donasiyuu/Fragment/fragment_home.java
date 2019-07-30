package com.donasiyuu.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.donasiyuu.Adapter.GambarAndroid;
import com.donasiyuu.Adapter.ImageAdapter;
import com.donasiyuu.R;
import com.donasiyuu.Upload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class fragment_home  extends Fragment {
    private RecyclerView recyclerView2;
    private ImageAdapter mAdapter;
    private ProgressBar mProgressCircle;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;

    public fragment_home() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_images, container, false);

        mProgressCircle = (ProgressBar) view.findViewById(R.id.progress_circle);
        recyclerView2 = view.findViewById(R.id.recycler_view);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.setHasFixedSize(true);


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Photo");

        return view;
    }
    public void onStart()
    {
        super.onStart();
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUploads = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }
                mAdapter = new ImageAdapter(getActivity(), mUploads);
                recyclerView2.setAdapter(mAdapter);


                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

}
