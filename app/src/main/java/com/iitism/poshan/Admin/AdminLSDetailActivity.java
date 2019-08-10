package com.iitism.poshan.Admin;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iitism.poshan.Admin.Admin_LSDetails.LSDetailsAdapter;
import com.iitism.poshan.Admin.Admin_LSDetails.LSDetailsModel;
import com.iitism.poshan.LS.LSRecyclerViewMain.ItemModel;
import com.iitism.poshan.LS.LSRecyclerViewMain.RecyclerViewAdapterMain;
import com.iitism.poshan.R;

import java.util.ArrayList;
import java.util.List;

public class AdminLSDetailActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public List<LSDetailsModel> listitem;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lsdetail);

        recyclerView=findViewById(R.id.admin_ls_detail_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("LSName");
        loadRecyclerViewData();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

    }

    public void loadRecyclerViewData()
    {
        listitem = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String name="Name: "+ds.child("Name").getValue(String.class);
                    String center="Center"+ds.child("pacenter").getValue(String.class);
                    String email="Email : "+ds.child("Email").getValue(String.class);
                    String password="Password : "+ds.child("Password").getValue(String.class);
                    String phone="Phone : "+ds.child("Phone").getValue(String.class);
                    LSDetailsModel lsDetailsModel=new LSDetailsModel(name,email,center,phone,password);
                    listitem.add(lsDetailsModel);
                }
                adapter = new LSDetailsAdapter(getApplicationContext(), listitem);
                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
