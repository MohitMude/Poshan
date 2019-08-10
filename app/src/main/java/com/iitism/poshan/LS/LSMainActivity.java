package com.iitism.poshan.LS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iitism.poshan.LS.LSRecyclerViewMain.ItemModel;
import com.iitism.poshan.LS.LSRecyclerViewMain.RecyclerViewAdapterMain;
import com.iitism.poshan.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class LSMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {



    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public List<ItemModel> listitem;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference centerRef;
   // ProgressBar progressBar;
    Spinner spinner;
    String spinnerSelecteditem;
    TextView vacantbeds;
    final List<String> centers=new ArrayList<String>();
    String defaultCenter;
    TextView headername,headeremail;
    Button btn1,btn2,btn3,btn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ls_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        headeremail=findViewById(R.id.email_android_studio);
        headername=findViewById(R.id.android_Studio);
        btn1=findViewById(R.id.btn_main_under_treatment);
        btn2=findViewById(R.id.btn_main_edit_child);
        btn3=findViewById(R.id.btn_main_waiting_list);
        btn4=findViewById(R.id.btn_main_profile);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);


        SharedPreferences sharedPreferences=getSharedPreferences("Values", Context.MODE_PRIVATE);
        defaultCenter =sharedPreferences.getString("Center","");
        centers.add(defaultCenter);
//        recyclerView=(RecyclerView)findViewById(R.id.recycler_view_ls_main);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       // headername.setText(sharedPreferences.getString("Name",""));
        //headeremail.setText(sharedPreferences.getString("Email",""));

        vacantbeds=findViewById(R.id.txt_view_vacant_beds_ls_main);

     //    progressBar=findViewById(R.id.progress_bar);
       //  progressBar.setVisibility(View.VISIBLE);
         spinner=findViewById(R.id.center_spinner);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("CenterDetails");
        centerRef=firebaseDatabase.getReference("Center");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LSAddChildDetailsActivity.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       centerRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {

               if(!defaultCenter.equals(dataSnapshot.getKey())){
               for(DataSnapshot areasnapshot:dataSnapshot.getChildren())
               {
                   String areaname=areasnapshot.getKey();
                   if(!areaname.equals(defaultCenter))
                   {centers.add(areaname);}

               }}
               ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,centers);
               arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               spinner.setAdapter(arrayAdapter);


           }


           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });


       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            spinnerSelecteditem=spinner.getSelectedItem().toString();
               Toast.makeText(getApplicationContext(),spinnerSelecteditem,Toast.LENGTH_LONG).show();
               loadVacantBeds();
               loadRecyclerViewData();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });



      //  loadRecyclerViewData();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lsmain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            SharedPreferences sharedPreferences=getSharedPreferences("Login",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1=sharedPreferences.edit();
            editor1.putString("LS Login","");
            editor1.apply();
            Intent i=new Intent(getApplicationContext(),LSLoginActivity.class);
            finish();
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_under_treatment) {
           Intent i=new Intent(getApplicationContext(),LSUnderTreatmentActivity.class);
           startActivity(i);

        }
        else if (id == R.id.nav_profile) {
            Intent i=new Intent(getApplicationContext(),LSProfileActivity.class);
            startActivity(i);

        }
        else if (id == R.id.nav_edit_child_details) {
             Intent j=new Intent(getApplicationContext(),LSEditChildDetailsActivity.class);
             startActivity(j);

        }
        else  if (id==R.id.nav_waiting_list){
            Intent j=new Intent(getApplicationContext(),LSWaitingList.class);
            startActivity(j);
        }
        else if (id == R.id.nav_logout) {
                 SharedPreferences sharedPreferences=getSharedPreferences("Login",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1=sharedPreferences.edit();
            editor1.putString("LS Login","");
            editor1.apply();
            Intent i=new Intent(getApplicationContext(),LSLoginActivity.class);
            finish();
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void loadRecyclerViewData()
    {
        //listitem=new ArrayList<>();
       /* for(int i=0;i<10;i++)
        {
            ItemModel itemModel=new ItemModel("MOHIT","MOHIT MUDE","1234567890","3");
            listitem.add(itemModel);
        }*/
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//                listitem.clear();
//                for(DataSnapshot ds:dataSnapshot.getChildren())
//                {
//                    if(spinnerSelecteditem.equals(ds.getKey())) {
//                        for (DataSnapshot ds1 : ds.getChildren()) {
//
//                            String childname = ds1.child("ChildName").getValue(String.class);
//                            String fathername = ds1.child("FatherName").getValue(String.class);
//                            String dayslefttovacate = ds1.child("DaysLeft").getValue(String.class);
//                            String phonenumber = ds1.child("PhoneNo").getValue(String.class);
//                            ItemModel itemModel = new ItemModel(childname, fathername, dayslefttovacate, phonenumber);
//                            listitem.add(itemModel);
//
//                        }
//                    }
//                }
//                adapter=new RecyclerViewAdapterMain(getApplicationContext(),listitem);
//                recyclerView.setAdapter(adapter);
//                progressBar.setVisibility(View.GONE);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


    }

    public  void loadVacantBeds()
    {
        centerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                {
                    for (DataSnapshot vacant : dataSnapshot.getChildren()) {

                        if(spinnerSelecteditem.equals(vacant.getKey())) {


                            vacantbeds.setText(vacant.child("Vacant Beds").getValue().toString()+"  BEDS VACANT");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==btn1)
        {
            Intent i=new Intent(getApplicationContext(),LSUnderTreatmentActivity.class);
            startActivity(i);
        }
        else if(v==btn2)
        {
            Intent i=new Intent(getApplicationContext(),LSEditChildDetailsActivity.class);
            startActivity(i);
        }else if(v==btn3)
        {Intent i=new Intent(getApplicationContext(),LSWaitingList.class);
            startActivity(i);

        }else if(v==btn4)
        {
            Intent i=new Intent(getApplicationContext(),LSProfileActivity.class);
            startActivity(i);
        }
    }
}
