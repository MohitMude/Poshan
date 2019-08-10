package com.iitism.poshan.LS;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iitism.poshan.LS.LSRecyclerViewMain.ItemModel;
import com.iitism.poshan.R;
import java.util.Date;

import java.util.Calendar;
import java.util.Date;

import java.text.SimpleDateFormat;


public class LSAddChildDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mchildName, mFatherName, mMotherName, mAge, mPhone, mWeight, mHeight, mDistrict, mBlock, mGramPanchayat, mAddress,  mAdhar;
    private String ichildName, iFatherName, iMotherName, iAge, iPhone, iWeight, iHeight, iDistrict, iBlock, iGramPanchayat, iAddress, iAdhar;
    private String iGender, currentDate, nextDate;
    String center,dob;
    int xage;
    String sex;
    ImageButton calendar;
     long a=6000000000L,b=9999999999L;
   double latitude,longitude;
    int IsBPL;
    Button add;
    Spinner spinner;
    Switch bpl;
    ArrayAdapter<CharSequence> spinneradapter;
    private Firebase mRootref1;
    private LocationManager locationManager;
    private LocationListener locationListener;
    DatabaseReference conditionRef;
    int criticality;
    private  DatePickerDialog.OnDateSetListener mdatelistener;
    int mYear,mMonth,mDay;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ls_add_child_details);
        Firebase.setAndroidContext(this);
        add = findViewById(R.id.btn_add_child_detail);
        mRootref1 = new Firebase("https://poshan-app.firebaseio.com/");

        spinner=findViewById(R.id.gender_spinner);
        bpl=findViewById(R.id.bpl_switch);
        calendar=findViewById(R.id.btn_calendar);




        String[] gendername=getResources().getStringArray(R.array.gender_spinner);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,gendername);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iGender=spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mchildName = findViewById(R.id.edt_txt_child_name);
        mFatherName = findViewById(R.id.edt_txt_father_name);
        mMotherName = findViewById(R.id.edt_txt_mother_name);
     //   mAge = findViewById(R.id.edt_txt_age);
        mPhone = findViewById(R.id.edt_txt_phone_number);
        mWeight = findViewById(R.id.edt_txt_weight);
        mHeight = findViewById(R.id.edt_txt_height);
        mDistrict = findViewById(R.id.edt_txt_district);
        mBlock = findViewById(R.id.edt_txt_block);
        mGramPanchayat = findViewById(R.id.edt_txt_gram_panchayat);
        mAddress = findViewById(R.id.edt_txt_address);
        mAdhar = findViewById(R.id.edt_txt_enter_Aadhar);
        add.setOnClickListener(this);
        calendar.setOnClickListener(this);


        SharedPreferences sharedPreferences=getSharedPreferences("Values",Context.MODE_PRIVATE);
         center =sharedPreferences.getString("Center","");

//       calendarView=findViewById(R.id.calendarView);
//       calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//           @Override
//           public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
//                dob=dayOfMonth+"/"+(month+1)+"/"+year;
//               Toast.makeText(getApplicationContext(),dob,Toast.LENGTH_LONG).show();
//           }
//       });
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


        Calendar c = Calendar.getInstance();
         mYear = c.get(Calendar.YEAR);
         mMonth = c.get(Calendar.MONTH);
         mDay = c.get(Calendar.DAY_OF_MONTH);
        currentDate = mDay + "/" + (mMonth + 1) + "/" + mYear;

        c.add(Calendar.DATE, 30);

        Date resultdate = new Date(c.getTimeInMillis());
        nextDate = format.format(resultdate);

      mdatelistener=new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dob=dayOfMonth+"/"+(month+1)+"/"+year;
        Toast.makeText(getApplicationContext(),dob,Toast.LENGTH_LONG).show();
    }
};

    }


    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case 5:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates("gps", 1000, 20, locationListener);
                }
    }  }

    @Override
    public void onClick(View v) {
        {
             if(v==add){
            if(bpl.isChecked())
            {
                IsBPL=1;
            }
            else {IsBPL=0;}
            ichildName = mchildName.getText().toString();
            iFatherName = mFatherName.getText().toString();
            iMotherName = mMotherName.getText().toString();
          //  iAge = mAge.getText().toString();
            iPhone = mPhone.getText().toString().trim();
            String s=iPhone+"L";
            long p=Long.parseLong(iPhone);

            if(p<a||p>b )
            {
                Toast.makeText(getApplicationContext(), "Please Enter Correct phone number ", Toast.LENGTH_SHORT).show();
                return;
            }
            final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date = format.parse(dob);
                Date currdate = format.parse(currentDate);
                float difference = currdate.getTime() - date.getTime();
                float daysbetween = (difference) / (1000 * 60 * 60 * 24);
                Toast.makeText(getApplicationContext(), String.valueOf(daysbetween), Toast.LENGTH_LONG).show();

                 xage=(int)daysbetween/30;

            } catch (Exception e) {
                e.printStackTrace();
            }


            iWeight = mWeight.getText().toString();
            iHeight = mHeight.getText().toString();
            iDistrict = mDistrict.getText().toString();
            iBlock = mBlock.getText().toString();
            iGramPanchayat = mGramPanchayat.getText().toString();
            iAddress = mAddress.getText().toString();
            iAdhar = mAdhar.getText().toString();
//                 latitude=11.43354;
//                 longitude=43.6555;
//                 critical();

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
                    critical();}
                }

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
        else if(v==calendar)
        {
            DatePickerDialog dialog=new DatePickerDialog(LSAddChildDetailsActivity.this,R.style.CalendarViewCustom ,mdatelistener,mYear,mMonth,mDay);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
            dialog.show();

        }
    }}

    public void critical() {


        if (iGender.equals("Male")) {
            conditionRef = FirebaseDatabase.getInstance().getReference("Boy").child(iHeight);
        } else {
            conditionRef = FirebaseDatabase.getInstance().getReference("Girl").child(iHeight);
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

                    if (Float.parseFloat(iWeight) >= Float.parseFloat(x1)) {
                        criticality = 0;
                    } else if (Float.parseFloat(iWeight) >= Float.parseFloat(x2)) {
                        criticality = 1;
                    } else if (Float.parseFloat(iWeight) >= Float.parseFloat(x3)) {
                        criticality = 2;
                    } else if (Float.parseFloat(iWeight) >= Float.parseFloat(x4)) {
                        criticality = 3;
                    } else if (Float.parseFloat(iWeight) >= Float.parseFloat(x5)) {
                        criticality = 4;
                    }


                }

      load();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }







  public  void load()
  {
      final Firebase childRef1 = mRootref1.child("Child Details").child(center).child(iAdhar);
        if(criticality>0)
         {
             Firebase waitingRef=mRootref1.child("Waiting List").child(center).child(iAdhar);
             //waitingRef.setValue(new ItemModel(ichildName,iFatherName,iMotherName,iGender,iPhone,iAge,iWeight,iHeight,iAddress,iBlock,iDistrict,iGramPanchayat,iAdhar,IsBPL,currentDate,nextDate));

             waitingRef.child("Child Name").setValue(ichildName);
             waitingRef.child("Father Name").setValue(iFatherName);
             waitingRef.child("Mother Name").setValue(iMotherName);
             waitingRef.child("Age").setValue(String.valueOf(xage));
             waitingRef.child("Phone No").setValue(iPhone);
             waitingRef.child("Weight").setValue(iWeight);
             waitingRef.child("Height").setValue(iHeight);
             waitingRef.child("District").setValue(iDistrict);
             waitingRef.child("Block").setValue(iBlock);
             waitingRef.child("Gram Panchayat").setValue(iGramPanchayat);
             waitingRef.child("Address").setValue(iAddress);
             waitingRef.child("Aadhar No").setValue(iAdhar);
             waitingRef.child("BPL").setValue(IsBPL);
             waitingRef.child("Gender").setValue(iGender);
             waitingRef.child("Date Of Birth").setValue(dob);
             waitingRef.child("Critical Level").setValue(criticality);
         }

         //childRef1.push().setValue(new ItemModel(ichildName,iFatherName,iMotherName,iGender,iPhone,iAge,iWeight,iHeight,iAddress,iBlock,iDistrict,iGramPanchayat,iAdhar,IsBPL,currentDate,nextDate));
            childRef1.child("Child Name").setValue(ichildName);
            childRef1.child("Father Name").setValue(iFatherName);
            childRef1.child("Mother Name").setValue(iMotherName);
            childRef1.child("Age").setValue(String.valueOf(xage));
            childRef1.child("Phone No").setValue(iPhone);
            childRef1.child("Weight").setValue(iWeight);
            childRef1.child("Height").setValue(iHeight);
            childRef1.child("District").setValue(iDistrict);
            childRef1.child("Block").setValue(iBlock);
            childRef1.child("Gram Panchayat").setValue(iGramPanchayat);
            childRef1.child("Address").setValue(iAddress);
            childRef1.child("Aadhar No").setValue(iAdhar);
            childRef1.child("BPL").setValue(IsBPL);
            childRef1.child("Gender").setValue(iGender);
            childRef1.child("Date Of Birth").setValue(dob);
            childRef1.child("Latitude").setValue(latitude);
            childRef1.child("Longitude").setValue(longitude);
            Toast.makeText(getApplicationContext(),"Data Uploaded ",Toast.LENGTH_LONG).show();

            Intent i=new Intent(getApplicationContext(),LSMainActivity.class);
            finish();
            startActivity(i);

        }



    }


