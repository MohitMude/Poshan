package com.iitism.poshan.LS;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.widget.LinearLayout;

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

public class LSUnderTreatmentActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public List<ItemModel> listitem;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ls_under_treatment);


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("CenterDetails");

        recyclerView=findViewById(R.id.recycler_view_ls_under_treatment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadRecyclerViewData();
    }


    public void loadRecyclerViewData() {
        listitem = new ArrayList<>();
       /*for(int i=0;i<10;i++)
        {
            ItemModel itemModel=new ItemModel("MOHIT","MOHIT MUDE","1234567890","3");
            listitem.add(itemModel);
        }*/

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listitem.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    for (DataSnapshot ds1 : ds.getChildren()) {
                        String childname = ds1.child("ChildName").getValue().toString();
                        String fathername = ds1.child("FatherName").getValue().toString();
                        String dayslefttovacate = ds1.child("DaysLeft").getValue().toString();
                        String phonenumber = ds1.child("PhoneNo").getValue().toString();
                       // int isapproved=Integer.parseInt( ds1.child("isApproved").getValue().toString());
                        ItemModel itemModel = new ItemModel(childname, fathername, dayslefttovacate, phonenumber);
                        listitem.add(itemModel);
                        /*if(isapproved==1)
                        {
                            linearLayout.setBackgroundColor(Color.RED);
                        }*/
                    }
                }
                adapter = new RecyclerViewAdapterMain(getApplicationContext(), listitem);
                recyclerView.setAdapter(adapter);
            }

           @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
