package com.iitism.poshan.Admin;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iitism.poshan.Admin.Admin_MTCAnalysisRecyclerView.AnalysisAdapter;
import com.iitism.poshan.Admin.Admin_MTCAnalysisRecyclerView.AnalysisItemModel;
import com.iitism.poshan.LS.LSLoginActivity;
import com.iitism.poshan.LS.LSRecyclerViewMain.ItemModel;
import com.iitism.poshan.LS.LSRecyclerViewMain.RecyclerViewAdapterMain;
import com.iitism.poshan.MTC.WaitingListAdapter;
import com.iitism.poshan.MTC.WaitingListModel;
import com.iitism.poshan.R;

import java.util.ArrayList;
import java.util.List;

public class AdminMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<WaitingListModel> list;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference weightRef;
    ProgressBar progressBar;
    private  final String CHANNEL_ID="Malnutition Alert";
    private  final int NOTIFICATION_ID=001;
    public ArrayList<Integer> weightlist=new ArrayList<>();
    int i;
    Button ls,mtc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        progressBar=findViewById(R.id.progress_bar2);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Under Treatment");
        weightRef=firebaseDatabase.getReference("Under Treatment");

        loadweight();



        recyclerView=findViewById(R.id.under_treatment_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadRecyclerView();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_main, menu);
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
            SharedPreferences sharedPreferences=getSharedPreferences("Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1=sharedPreferences.edit();
            editor1.putString("Admin  Login","");
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

        if (id == R.id.nav_mtc_center_analysis) {

            Intent i=new Intent(getApplicationContext(), AdminMTCCenterAnalysis.class);
            startActivity(i);

        } else if (id == R.id.nav_ls_details) {
            Intent i=new Intent(getApplicationContext(), AdminLSDetailActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_mtc_centers) {

            Intent i=new Intent(getApplicationContext(), MTCCentersActivity.class);
            startActivity(i);

        }else if(id==R.id.nav_add_mtc_center)
        {
            Intent i=new Intent(getApplicationContext(),AddMTCCenter.class);
            startActivity(i);
        }
        else if (id == R.id.nav_admin_profile) {
            Intent i=new Intent(getApplicationContext(),AdminProfileActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_admin_logout) {
            SharedPreferences sharedPreferences=getSharedPreferences("Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1=sharedPreferences.edit();
            editor1.putString("Admin  Login","");
            editor1.apply();
            Intent i=new Intent(getApplicationContext(),LSLoginActivity.class);
            finish();
            startActivity(i);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


  public void loadRecyclerView()
  {
      list =new ArrayList<>();
      databaseReference.addValueEventListener(new ValueEventListener() {

          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              list.clear();
              for(DataSnapshot ds:dataSnapshot.getChildren())
              {
                  for(DataSnapshot ds1:ds.getChildren()) {
                      String childname = ds1.child("Child Name").getValue(String.class);
                      String fathername = ds1.child("Father Name").getValue(String.class);
                      String mothername = ds1.child("Mother Name").getValue(String.class);
                      String age = ds1.child("Age").getValue(String.class);
                      String phonenumber = ds1.child("Phone No").getValue(String.class);
                      String block = ds1.child("Block").getValue(String.class);
                      String address = ds1.child("Address").getValue(String.class);
                      String adhar=ds1.child("Aadhar").getValue(String.class);
                      String center=ds.getKey();
                      String SAM=ds1.getKey();
                      String height=ds1.child("Height").getValue(String.class);
                      String weight=ds1.child("Weight").getValue(String.class);
                      WaitingListModel waitingListModel = new WaitingListModel(childname, fathername, mothername, age, block,  address,   phonenumber,adhar,center,height,weight,SAM);
                      list.add(waitingListModel);
                  }
              }
              adapter=new GraphListAdapter(getApplicationContext(),list);
              recyclerView.setAdapter(adapter);
              progressBar.setVisibility(View.INVISIBLE);
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });
  }

  public  void loadweight()
  {
      weightRef.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              for(DataSnapshot ds:dataSnapshot.getChildren())
              {
                weightlist.clear();
                  for(DataSnapshot ds1:ds.getChildren())
                  {
                      for(DataSnapshot ds2:ds1.getChildren())
                      {
                          for(DataSnapshot ds3:ds2.getChildren())
                          {
                              String a=ds3.child("Weight").getValue(String.class);
                              weightlist.add(Integer.parseInt(a));

                          }
                      }
                      for(i=0;i<weightlist.size()-2;i++)
                      {
                          if(weightlist.get(i)>weightlist.get(i+1))
                          {
                              if (weightlist.get(i+1)>weightlist.get(i+2))
                              {
                                  displayNotification(ds1.getKey());
                              }
                          }
                      }


                  }
              }

          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });
  }

    public  void displayNotification(String sam)
    {

        createNotificationChannel(sam);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms);
        builder.setContentTitle("Alert Notification");
        builder.setContentText("Weight decreased for third consecutive day of child: "+sam);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }

    private  void createNotificationChannel(String sam)
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name="Alert Notification";
            String description ="Weight decreased for third consecutive day of child: "+sam;
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);


        }
    }


}
