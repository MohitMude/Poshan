package com.iitism.poshan.LS;

import android.content.Intent;
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
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
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

public class LSMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public List<ItemModel> listitem;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ls_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         progressBar=findViewById(R.id.progress_bar);
         progressBar.setVisibility(View.VISIBLE);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("CenterDetails");

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



        recyclerView=(RecyclerView)findViewById(R.id.recycler_view_ls_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadRecyclerViewData();

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
            return true;
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

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void loadRecyclerViewData()
    {
        listitem=new ArrayList<>();
       /* for(int i=0;i<10;i++)
        {
            ItemModel itemModel=new ItemModel("MOHIT","MOHIT MUDE","1234567890","3");
            listitem.add(itemModel);
        }*/

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                listitem.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    for(DataSnapshot ds1:ds.getChildren()) {
                        String childname = ds1.child("ChildName").getValue(String.class);
                        String fathername = ds1.child("FatherName").getValue(String.class);
                        String dayslefttovacate = ds1.child("DaysLeft").getValue(String.class);
                        String phonenumber = ds1.child("PhoneNo").getValue(String.class);
                        ItemModel itemModel = new ItemModel(childname, fathername, dayslefttovacate, phonenumber);
                        listitem.add(itemModel);
                    }
                }
                adapter=new RecyclerViewAdapterMain(getApplicationContext(),listitem);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
