package com.iitism.poshan.MTC;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iitism.poshan.LS.LSLoginActivity;
import com.iitism.poshan.LS.LSRecyclerViewMain.RecyclerViewAdapterMain;
import com.iitism.poshan.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MTCMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private  final String CHANNEL_ID="Waiting List";
private  final int NOTIFICATION_ID=001;
String Sam;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<WaitingListModel> list;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference notificationRef;
    DatabaseReference history;
    ProgressBar progressBar;
    String currentDate;
    String center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtc_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        currentDate = mDay + "/" + (mMonth + 1) + "/" + mYear;

        SharedPreferences sharedPreferences=getSharedPreferences("MTC",Context.MODE_PRIVATE);
        center=sharedPreferences.getString("Center","");
        progressBar=findViewById(R.id.progress_bar1);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        progressBar=findViewById(R.id.progress_bar1);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Waiting List");
        notificationRef=firebaseDatabase.getReference("Under Treatment").child(center);
        history=firebaseDatabase.getReference("Under Treatment");

        recyclerView=findViewById(R.id.mtc_waiting_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadRecyclerView();
        loadDischarge();

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
        getMenuInflater().inflate(R.menu.mtcmain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) { SharedPreferences sharedPreferences=getSharedPreferences("Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1=sharedPreferences.edit();
            editor1.putString("MTC Login","");
            editor1.apply();
            Intent i=new Intent(getApplicationContext(), LSLoginActivity.class);
            finish();
            startActivity(i);

    }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Update_Daily_Details) {

            Intent i=new Intent(getApplicationContext(),MTCUpdateDailyDetails.class);
            startActivity(i);
        }
        else if (id==R.id.nav_mtc_profile)
        {
            Intent i=new Intent(getApplicationContext(),MTCProfileActivity.class);
            startActivity(i);
        }
            else if (id==R.id.nav_mtc_logout) {
            SharedPreferences sharedPreferences=getSharedPreferences("Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1=sharedPreferences.edit();
            editor1.putString("MTC Login","");
            editor1.apply();
            Intent i=new Intent(getApplicationContext(), LSLoginActivity.class);
            finish();
            startActivity(i);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void loadRecyclerView()
    {
        progressBar.setVisibility(View.VISIBLE);
       list =new ArrayList<>();
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {

               for(DataSnapshot ds:dataSnapshot.getChildren())
               {
                   for(DataSnapshot ds1:ds.getChildren()) {
                       String childname = ds1.child("Aame").getValue(String.class);
                       String fathername = ds1.child("Acfather Name").getValue(String.class);
                       String mothername = ds1.child("Acmother Name").getValue(String.class);
                       String age = ds1.child("Age").getValue(String.class);
                       String phonenumber = ds1.child("Bphone").getValue(String.class);
                       String block = ds1.child("Block").getValue(String.class);
                       String address = ds1.child("Address").getValue(String.class);
                       String adhar=ds1.child("Aadhar No").getValue(String.class);
                       String center=ds.getKey();
                       String height=ds1.child("Height").getValue(String.class);
                       String weight=ds1.child("Weight").getValue(String.class);
                       WaitingListModel waitingListModel = new WaitingListModel(childname, fathername, mothername, age, block,  address,   phonenumber,adhar,center,height,weight);
                       list.add(waitingListModel);
                   }
               }

               adapter=new WaitingListAdapter(getApplicationContext(),list);
               recyclerView.setAdapter(adapter);
               progressBar.setVisibility(View.INVISIBLE);

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
    }

    void loadDischarge()
    {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        notificationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                     Sam=ds.getKey();

                    {

                        String x = ds.child("Discharge Date").getValue(String.class);
                        try {
                            Date date = format.parse(x);
                            Date currdate = format.parse(currentDate);
                            float difference = date.getTime() - currdate.getTime();
                            float daysbetween = (difference) / (1000 * 60 * 60 * 24);
                            Toast.makeText(getApplicationContext(), String.valueOf(daysbetween), Toast.LENGTH_LONG).show();

                            if (daysbetween <= 3) {
                                displayNotification();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public  void displayNotification()
    {

        createNotificationChannel();
        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms);
        builder.setContentTitle("Alert Notification");
        builder.setContentText("Discharge date coming soon for SAM :"+Sam);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }

    private  void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name="Alert Notification";
            String description ="Discharge date coming soon..";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);


        }
    }
}
