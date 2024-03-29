package com.iitism.poshan.LS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iitism.poshan.Admin.AdminLoginActivity;
import com.iitism.poshan.MTC.MTCLoginActivity;
import com.iitism.poshan.R;

public class LSLoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView LogInAsAdmin,LogInAsMTC;
    EditText LSEmail,LSPassword;
    String enteredemail,enteredpassword;
    String email,password,center;
    int flag=0;
    Button LsLogin;

    FirebaseDatabase firebase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ls_login);

        LogInAsAdmin=findViewById(R.id.txt_view_login_as_admin);
        LogInAsMTC=findViewById(R.id.txt_view_login_as_mtc);
        LSEmail=findViewById(R.id.edt_txt_ls_email);
        LSPassword=findViewById(R.id.edt_txt_ls_password);
        LsLogin=findViewById(R.id.btn_ls_login);



        LogInAsAdmin.setOnClickListener(this);
        LogInAsMTC.setOnClickListener(this);
        LsLogin.setOnClickListener(this);

        firebase=FirebaseDatabase.getInstance();
        databaseReference=firebase.getReference("LSName");
    }

    @Override
    public void onClick(View v) {

        if(v == LsLogin)
        {
            enteredemail=LSEmail.getText().toString().trim();
            enteredpassword=LSPassword.getText().toString().trim();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot ds:dataSnapshot.getChildren())
                    {
                       email=ds.child("Email").getValue(String.class);
                       password=ds.child("Password").getValue(String.class);

                        if(email.equals(enteredemail)){


                            if(password.equals(enteredpassword))
                            {   flag=1;

                                   center=ds.child("pacenter").getValue(String.class);


                                SharedPreferences sharedPreferences = getSharedPreferences("Values",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("Center",center);
                                editor.putString("Email",ds.child("Email").getValue(String.class));
                                editor.putString("Phone",ds.child("Phone").getValue(String.class));
                                editor.putString("Name",ds.child("Name").getValue(String.class));
                                editor.apply();

                                SharedPreferences sharedPreferences1=getSharedPreferences("Login",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor1=sharedPreferences1.edit();
                                editor1.putString("LS Login","true");
                                editor1.apply();





                                Intent i=new Intent(getApplicationContext(),LSMainActivity.class);
                                finish();
                                startActivity(i);
                            }


                        }
                    }

                    if(flag==0) {
                        LSPassword.setError("Entered Email Or password is incorrect");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        }
        else if(v == LogInAsAdmin)
        {
            Intent i=new Intent(getApplicationContext(), AdminLoginActivity.class);
            finish();
            startActivity(i);
        }
        else if(v== LogInAsMTC)
        {
           Intent j=new Intent(getApplicationContext(), MTCLoginActivity.class);
            finish();
           startActivity(j);
        }


    }
}
