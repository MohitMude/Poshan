package com.iitism.poshan.Admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iitism.poshan.LS.LSLoginActivity;
import com.iitism.poshan.LS.LSMainActivity;
import com.iitism.poshan.MTC.MTCLoginActivity;
import com.iitism.poshan.R;

public class AdminLoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView LoginAsMTC,LoginAsLS;
    EditText AdminEmail,AdminPassword;
    String enteredemail,enteredpassword;
    Button AdminLogin;
    int flag=0;
    FirebaseDatabase firebase;
    DatabaseReference databaseReference;
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        LoginAsLS=findViewById(R.id.txt_view_login_as_LS);
        LoginAsMTC=findViewById(R.id.txt_view_login_as_mtc);
        AdminEmail=findViewById(R.id.edt_txt_admin_email);
        AdminPassword=findViewById(R.id.edt_txt_admin_password);
        AdminLogin=findViewById(R.id.btn_admin_login);

        LoginAsLS.setOnClickListener(this);
        LoginAsMTC.setOnClickListener(this);
        AdminLogin.setOnClickListener(this);

        firebase=FirebaseDatabase.getInstance();
        databaseReference=firebase.getReference("Admin");

    }

    @Override
    public void onClick(View v) {
        if(v==AdminLogin)
        {

            enteredemail=AdminEmail.getText().toString().trim();
            enteredpassword=AdminPassword.getText().toString().trim();
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
                                SharedPreferences sharedPreferences = getSharedPreferences("Admin",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("Email",ds.child("Email").getValue(String.class));
                                editor.putString("Phone",ds.child("Phone").getValue(String.class));
                                editor.putString("Name",ds.child("Name").getValue(String.class));
                                editor.apply();

                                SharedPreferences sharedPreferences1=getSharedPreferences("Login",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor1=sharedPreferences1.edit();
                                editor1.putString("Admin Login","true");
                                editor1.apply();

                                 Intent i=new Intent(getApplicationContext(), AdminMainActivity.class);
                                 finish();
                                  startActivity(i);
                            }


                        }
                    }

                    if(flag==0) {
                        AdminPassword.setError("Entered Email Or password is incorrect");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(v==LoginAsLS)
        {
            Intent i=new Intent(getApplicationContext(), LSLoginActivity.class);
            startActivity(i);
        }
        else if(v==LoginAsMTC)
        {
         Intent j=new Intent(getApplicationContext(), MTCLoginActivity.class);
         startActivity(j);
        }

    }
}
