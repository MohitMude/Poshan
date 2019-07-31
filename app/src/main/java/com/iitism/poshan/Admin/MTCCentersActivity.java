package com.iitism.poshan.Admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iitism.poshan.LS.LSRecyclerViewMain.ItemModel;
import com.iitism.poshan.LS.LSRecyclerViewMain.RecyclerViewAdapterMain;
import com.iitism.poshan.R;

import java.util.ArrayList;
import java.util.List;

public class MTCCentersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    public List<MTCCentersModel>  centerlist;
    FirebaseDatabase firebaseDatabase1;
    DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtccenters);

        //Firebase.setAndroidContext(this);
        //centerref=new Firebase("https://poshan-app.firebaseio.com/Center");


        recyclerView=findViewById(R.id.mtc_centers_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        firebaseDatabase1=FirebaseDatabase.getInstance();
        databaseReference1=firebaseDatabase1.getReference("Center");

        loadRecyclerViewData();
    }

    public void loadRecyclerViewData()
    {
        centerlist= new ArrayList<>();
        MTCCentersModel mtcCentersModel = new MTCCentersModel("CHAS","Albel KHERKATTA","Chas","chasmtc@gmail.com","123456","9131981111",20);
        centerlist.add(mtcCentersModel);
        adapter=new MTCCentersAdapter(this,centerlist);
        recyclerView.setAdapter(adapter);

       /* databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    //for(DataSnapshot ds1:ds.getChildren()) {
                        String centername=ds.child("Address").getValue(String.class);
                        String incharge=ds.child("Incharge").getValue(String.class);
                        String address=ds.child("Address").getValue(String.class);
                        int  mobile=Integer.parseInt(ds.child("Mobile").getValue(String.class));
                        int beds=Integer.parseInt(ds.child("Total Beds").getValue(String.class));
                        String email=ds.child("Email").getValue(String.class);
                        String password=ds.child("Password").getValue(String.class);
                      // MTCCentersModel mtcCentersModel = new MTCCentersModel(centername,incharge,address,email,password,mobile,beds);
                    MTCCentersModel mtcCentersModel = new MTCCentersModel("CHAS","Albel KHERKATTA","Chas","chasmtc@gmail.com","123456",913198,20);
                    centerlist.add(mtcCentersModel);
                    //}
                }
                adapter=new MTCCentersAdapter(getApplicationContext(),centerlist);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}
