package com.iitism.poshan.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iitism.poshan.LS.LSLoginActivity;
import com.iitism.poshan.MTC.MTCLoginActivity;
import com.iitism.poshan.R;

public class AdminLoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView LoginAsMTC,LoginAsLS;
    EditText AdminEmail,AdminPassword;
    Button AdminLogin;

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
    }

    @Override
    public void onClick(View v) {
        if(v==AdminLogin)
        {
            Intent i=new Intent(getApplicationContext(), AdminMainActivity.class);
            startActivity(i);
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
