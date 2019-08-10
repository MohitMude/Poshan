package com.iitism.poshan.LS;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iitism.poshan.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LSEditChildDetailsActivity extends AppCompatActivity implements View.OnClickListener {
//    EditText mchildName, mFatherName, mMotherName, mAge, mPhone, mWeight, mHeight, mDistrict, mBlock, mGramPanchayat, mAddress,  mAdhar,msummary,mparentsvisited;
   private String ichildName, iFatherName, iMotherName, iGramPanchayat, iAddress;
    private String   iPhone, iDistrict, iBlock,isBPL;

    String xaadhar,xheight,xweight,xsummary;
    String lat,lon;
    EditText aadhar,height,weight,summary;
    double latitude,longitude;
    double xlatitude,xlongitude;
    Button update;
    String center,dob,gender,currentDate;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference mrootRef;
    private LocationManager locationManager;
    private LocationListener locationListener;
    DatabaseReference conditionRef;
    int criticality,flag=0,xAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsedit_child_details);

        firebaseDatabase=FirebaseDatabase.getInstance();
        SharedPreferences sharedPreferences=getSharedPreferences("Values", Context.MODE_PRIVATE);
        center=sharedPreferences.getString("Center","");
        databaseReference=firebaseDatabase.getReference("Child Details").child(center);
        mrootRef=firebaseDatabase.getReference();

          aadhar=findViewById(R.id.edt_txt_edit_adhar_no);
//        mchildName=findViewById(R.id.edt_txt_edit_child_name);
//        mFatherName=findViewById(R.id.edt_txt_edit_father_name);
//        mMotherName=findViewById(R.id.edt_txt_edit_mother_name);
//        mAge=findViewById(R.id.edt_txt_edit_age);
//        mPhone=findViewById(R.id.edt_txt_edit_phone_number);
        weight=findViewById(R.id.edt_txt_edit_weight);
        height=findViewById(R.id.edt_txt_edit_height);
//        mDistrict=findViewById(R.id.edt_txt_edit_district);
//        mBlock=findViewById(R.id.edt_txt_edit_block);
//        mGramPanchayat=findViewById(R.id.edt_txt_edit_gram_panchayat);
//        mAddress=findViewById(R.id.edt_txt_edit_address);
//        mparentsvisited=findViewById(R.id.edt_txt_edit_how_many_times_visited_parents);
        summary=findViewById(R.id.edt_txt_edit_enter_summary);
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        currentDate = mDay + "-" + (mMonth + 1) + "-" + mYear;


        update=findViewById(R.id.btn_edit_child_detail);
        update.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

//        ichildName=mchildName.getText().toString().trim();
//        iFatherName=mFatherName.getText().toString().trim();
//        iMotherName=mMotherName.getText().toString().trim();
//        iAge=mAge.getText().toString().trim();
//        iAddress=mAddress.getText().toString().trim();
        xaadhar=aadhar.getText().toString().trim();
        xheight=height.getText().toString().trim();
        xweight=weight.getText().toString().trim();
//        iDistrict=mDistrict.getText().toString().trim();
//        iBlock=mBlock.getText().toString().trim();
//        iGramPanchayat=mGramPanchayat.getText().toString().trim();
//        iPhone=mPhone.getText().toString().trim();
//        iparentsvisited=mparentsvisited.getText().toString().trim();
        xsummary=summary.getText().toString().trim();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {

                    if(xaadhar.equals(ds.getKey()))
                    {  flag=1;

                       xlatitude=(ds.child("Latitude").getValue(Double.class));
                       xlongitude=(ds.child("Longitude").getValue(Double.class));
                    //   xlongitude=Double.parseDouble(lat);
                      // xlatitude=Double.parseDouble(lon);
                       gender=ds.child("Gender").getValue(String.class);
                       ichildName=ds.child("Child Name").getValue(String.class);
                       iFatherName=ds.child("Father Name").getValue(String.class);
                       iMotherName=ds.child("Mother Name").getValue(String.class);
                       iBlock=ds.child("Block").getValue(String.class);
                       iGramPanchayat=ds.child("Gram Panchayat").getValue(String.class);
                       iDistrict=ds.child("District").getValue(String.class);
                       isBPL=String.valueOf(ds.child("BPL").getValue(Integer.class));
                       dob=ds.child("Date Of Birth").getValue(String.class);
                       iAddress = ds.child("Address").getValue(String.class);
                       iPhone=ds.child("Phone No").getValue(String.class);
                    }
                }
                if(flag==0)
                {   Toast.makeText(getApplicationContext(),"Child detail not exist.First ADD child",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getApplicationContext(),LSAddChildDetailsActivity.class);
                    finish();
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                Toast.makeText(getApplicationContext(), "Longitude" + longitude + "   " + "Latitude " + latitude, Toast.LENGTH_LONG).show();
                if(TextUtils.isEmpty(String.valueOf(latitude))&& TextUtils.isEmpty(String.valueOf(longitude)))
                {
                    Toast.makeText(getApplicationContext(),"Error in setting GPS location",Toast.LENGTH_LONG);

                }
                else{
                   int a=locationChecker();
                    if(a==1)
                    {
                    critical();}
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Location not matched with database",Toast.LENGTH_LONG).show();

                    }
            }}

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 5);
                Toast.makeText(getApplicationContext(), "Enabling Geosync", Toast.LENGTH_LONG).show();
//            Toast.makeText(getApplicationContext(),"Longitude :23.8592"+ "Latitude :" + "89.5074 ",Toast.LENGTH_LONG).show();
            }

            return;
        } else {
            locationManager.requestLocationUpdates("gps", 10000, 10, locationListener);
//                Toast.makeText(getApplicationContext(),"Longitude :23.8592"+ "Latitude :" + "89.5074 ",Toast.LENGTH_LONG).show();
        }


    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case 5:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates("gps", 10000, 500, locationListener);
                    int a=locationChecker();
                    if(a==1)
                    {
                        critical();}

                    else if(a==0)
                    {
                        Toast.makeText(getApplicationContext(),"Location not matched with database",Toast.LENGTH_LONG).show();

                    }
                }
        }  }

    public  void activityloader()
    {
        Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();
        Intent i=new Intent(getApplicationContext(),LSMainActivity.class);
        startActivity(i);

    }

    public void critical() {
        int h = Integer.parseInt(xheight);
        float w = Float.parseFloat(xweight);

        if (gender.equals("Male")) {
            conditionRef = FirebaseDatabase.getInstance().getReference("Boy").child(xheight);
        } else {
            conditionRef = FirebaseDatabase.getInstance().getReference("Girl").child(xweight);
        }

        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String x1 = ds.child("MEDIAN").getValue(String.class);
                    String x2 = ds.child("1SD").getValue(String.class);
                    String x3 = ds.child("2SD").getValue(String.class);
                    String x4 = ds.child("3SD").getValue(String.class);
                    String x5 = ds.child("4SD").getValue(String.class);

                    if (Float.parseFloat(xweight) >= Float.parseFloat(x1)) {
                        criticality = 0;
                    } else if (Float.parseFloat(xweight) >= Float.parseFloat(x2)) {
                        criticality = 1;
                    } else if (Float.parseFloat(xweight) >= Float.parseFloat(x3)) {
                        criticality = 2;
                    } else if (Float.parseFloat(xweight) >= Float.parseFloat(x4)) {
                        criticality = 3;
                    } else if (Float.parseFloat(xweight) >= Float.parseFloat(x5)) {
                        criticality = 4;
                    }


                }
                       Toast.makeText(getApplicationContext(),criticality,Toast.LENGTH_LONG).show();
                load();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public  int locationChecker()
    {
        float[] distance=new float[2];
        Location.distanceBetween(latitude,longitude,xlatitude,xlongitude,distance);
        if(distance[0]>100)
        {
            return  0;
        }
        else
        {
            return 1;
        }
    }

    public  void load()
    {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(dob);
            Date currdate = format.parse(currentDate);
            float difference = currdate.getTime() - date.getTime();
            float daysbetween = (difference) / (1000 * 60 * 60 * 24);
            Toast.makeText(getApplicationContext(), String.valueOf(daysbetween), Toast.LENGTH_LONG).show();

            xAge=(int)daysbetween/30;

        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseReference childRef1 = mrootRef.child("Child Details").child(center).child(xaadhar).child(currentDate);
        if(criticality>0)
        {
            DatabaseReference waitingRef=mrootRef.child("Waiting List").child(center).child(xaadhar);
            //waitingRef.setValue(new ItemModel(ichildName,iFatherName,iMotherName,iGender,iPhone,iAge,iWeight,iHeight,iAddress,iBlock,iDistrict,iGramPanchayat,iAdhar,IsBPL,currentDate,nextDate));

            waitingRef.child("Child Name").setValue(ichildName);
            waitingRef.child("Father Name").setValue(iFatherName);
            waitingRef.child("Mother Name").setValue(iMotherName);
            waitingRef.child("Age").setValue(String.valueOf(xAge));
            waitingRef.child("Phone No").setValue(iPhone);
            waitingRef.child("Weight").setValue(xweight);
            waitingRef.child("Height").setValue(xheight);
            waitingRef.child("District").setValue(iDistrict);
            waitingRef.child("Block").setValue(iBlock);
            waitingRef.child("Gram Panchayat").setValue(iGramPanchayat);
            waitingRef.child("Address").setValue(iAddress);
            waitingRef.child("Aadhar No").setValue(xaadhar);
            waitingRef.child("BPL").setValue(isBPL);
            waitingRef.child("Gender").setValue(gender);
            waitingRef.child("Date Of Birth").setValue(dob);
            waitingRef.child("Critical Level").setValue(criticality);
        }

        //childRef1.push().setValue(new ItemModel(ichildName,iFatherName,iMotherName,iGender,iPhone,iAge,iWeight,iHeight,iAddress,iBlock,iDistrict,iGramPanchayat,iAdhar,IsBPL,currentDate,nextDate));

        childRef1.child("Weight").setValue(xweight);
        childRef1.child("Height").setValue(xheight);
        childRef1.child("Summary").setValue(xsummary);

        Toast.makeText(getApplicationContext(),"Data Uploaded ",Toast.LENGTH_LONG).show();

        Intent i=new Intent(getApplicationContext(),LSMainActivity.class);
        finish();
        startActivity(i);

    }

}
