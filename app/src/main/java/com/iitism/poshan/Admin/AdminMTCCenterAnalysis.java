package com.iitism.poshan.Admin;

import android.support.annotation.NonNull;
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
import com.iitism.poshan.Admin.Admin_MTCAnalysisRecyclerView.AnalysisAdapter;
import com.iitism.poshan.Admin.Admin_MTCAnalysisRecyclerView.AnalysisItemModel;
import com.iitism.poshan.LS.LSRecyclerViewMain.ItemModel;
import com.iitism.poshan.R;

import java.util.ArrayList;
import java.util.List;

public class AdminMTCCenterAnalysis extends AppCompatActivity {

    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public List<AnalysisItemModel> listitem;
   FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    AnalysisAdapter analysisAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mtccenter_analysis);

       firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Center");

        recyclerView = (RecyclerView) findViewById(R.id.mtc_center_analysis_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadRecyclerViewData();
    }


    public void loadRecyclerViewData() {
        listitem = new ArrayList<>();

     /* for(int i=0;i<10;i++)
        {
            AnalysisItemModel analysisItemModel=new AnalysisItemModel(10,5,3,7,"CHAS");
            listitem.add(analysisItemModel);
        }
        adapter = new AnalysisAdapter(getApplicationContext(), listitem);
        recyclerView.setAdapter(adapter);*/

       databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listitem.clear();
               // listitem.add(new AnalysisItemModel("TOTAL BEDS","VACANT BEDS","ABOUT TO DISCHARGE","AVERAGE BED OCCUPANCY","CENTER NAME"));
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                   {
                        String CenterName = ds.getKey();
                       // Toast.makeText(getApplicationContext(),CenterName,Toast.LENGTH_LONG).show();
                        String t= (ds.child("Total Bed").getValue(String.class));
                        String v= (ds.child("Vacant Beds").getValue(String.class));
                         String ab= (ds.child("about to discharge").getValue(String.class));
                       String av = (ds.child("average bed occupancy").getValue(String.class));

                       int TotalBeds=Integer.parseInt(t);
                       int VacantBeds=Integer.parseInt(v);
                       int AboutToDischarge=Integer.parseInt(ab);
                       int AverageBedOccupancy=Integer.parseInt(av);
                       AnalysisItemModel analysisItemModel = new AnalysisItemModel(TotalBeds,VacantBeds,AboutToDischarge,AverageBedOccupancy,CenterName);
                        listitem.add(analysisItemModel);

                    }
                }
                adapter = new AnalysisAdapter(getApplicationContext(), listitem);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
