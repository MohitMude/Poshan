package com.iitism.poshan.MTC;

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
import com.iitism.poshan.Admin.AdminLoginActivity;
import com.iitism.poshan.LS.LSLoginActivity;
import com.iitism.poshan.LS.LSMainActivity;
import com.iitism.poshan.R;

import org.w3c.dom.Text;

public class MTCLoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView LoginAsAdmin,LoginAsLS;
    EditText MTCEmail,MTCPassword;
    String enteredemail,enteredpassword;
    String email,password;
    int flag=0;
    Button MTCLogin;
    FirebaseDatabase firebase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtc_login);

        LoginAsAdmin=findViewById(R.id.txt_view_login_as_admin);
        LoginAsLS=findViewById(R.id.txt_view_login_as_LS);
        MTCEmail=findViewById(R.id.edt_txt_mtc_email);
        MTCPassword=findViewById(R.id.edt_txt_mtc_password);
        MTCLogin=findViewById(R.id.btn_mtc_login);

        LoginAsAdmin.setOnClickListener(this);
        LoginAsLS.setOnClickListener(this);
        MTCLogin.setOnClickListener(this);
        firebase=FirebaseDatabase.getInstance();
        databaseReference=firebase.getReference("Center");

    }

    @Override
    public void onClick(View v) {

        if(v==MTCLogin){

            enteredemail=MTCEmail.getText().toString().trim();
            enteredpassword=MTCPassword.getText().toString().trim();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        email=ds.child("Oemail").getValue(String.class);
                        password=ds.child("Password").getValue(String.class);

                        if(email.equals(enteredemail)){


                            if(password.equals(enteredpassword))
                            {   flag=1;
                                   String center=ds.getKey();
                                   SharedPreferences sharedPreferences = getSharedPreferences("MTC",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("Center",center);
                                editor.putString("Email",ds.child("Oemail").getValue(String.class));
                                editor.putString("Phone",ds.child("Mobile").getValue(String.class));
                                editor.putString("Name",ds.child("Incharge").getValue(String.class));
                                editor.apply();

                                SharedPreferences sharedPreferences1=getSharedPreferences("Login",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor1=sharedPreferences1.edit();
                                editor1.putString("MTC Login","true");
                                editor1.apply();

                                Intent j=new Intent(getApplicationContext(), MTCMainActivity.class);
                                finish();
                                startActivity(j);
                            }


                        }
                    }

                    if(flag==0) {
                        MTCPassword.setError("Entered Email Or password is incorrect");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if(v==LoginAsLS)
        {
            Intent j=new Intent(getApplicationContext(), LSLoginActivity.class);
            startActivity(j);
        }
        else if(v==LoginAsAdmin)
        {Intent i=new Intent(getApplicationContext(), AdminLoginActivity.class);
            startActivity(i);

        }

    }
}
