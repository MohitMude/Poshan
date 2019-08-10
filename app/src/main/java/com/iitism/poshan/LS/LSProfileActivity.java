package com.iitism.poshan.LS;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iitism.poshan.R;

public class LSProfileActivity extends AppCompatActivity {

    TextView name,center,email,phone,undertreatment,waiting;
    String xname,xcenter,xemail,xphone,xundertreatment,xwaiting;
    int count1=0,count2=0;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference waitingRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ls_profile);

        name=findViewById(R.id.name_field);
        email=findViewById(R.id.email_field);
        center=findViewById(R.id.center_field);
        phone=findViewById(R.id.phone_field);
        undertreatment=findViewById(R.id.undertreatment_field);
        waiting=findViewById(R.id.waiting_list_field);

        firebaseDatabase=FirebaseDatabase.getInstance();


        SharedPreferences sharedPreferences=getSharedPreferences("Values", Context.MODE_PRIVATE);
        xname="Name : "+sharedPreferences.getString("Name","");
        xcenter="Center : "+sharedPreferences.getString("Center","");
        xemail="Email : "+sharedPreferences.getString("Email","");
        xphone="Phone : "+sharedPreferences.getString("Phone","");
        databaseReference=firebaseDatabase.getReference("Under Treatment").child(sharedPreferences.getString("Center",""));
        waitingRef=firebaseDatabase.getReference("Waiting List").child(sharedPreferences.getString("Center",""));
        load();



    }
    public  void load()
    {
        {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        count1++;

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            waitingRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                        count2++;
                    }
                    data(count1,count2);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }
    public void data(int a,int b)
    {   String x,y;
    x=String.valueOf(a);
    y=String.valueOf(b);

        xundertreatment="Under Treatment : "+x;
        xwaiting="Waiting List : "+y;
        name.setText(xname);
        center.setText(xcenter);
        phone.setText(xphone);
        email.setText(xemail);
        undertreatment.setText(xundertreatment);
        waiting.setText(xwaiting);
    }
}
