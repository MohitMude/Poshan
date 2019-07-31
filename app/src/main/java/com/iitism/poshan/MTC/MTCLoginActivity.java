package com.iitism.poshan.MTC;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iitism.poshan.Admin.AdminLoginActivity;
import com.iitism.poshan.LS.LSLoginActivity;
import com.iitism.poshan.R;

import org.w3c.dom.Text;

public class MTCLoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView LoginAsAdmin,LoginAsLS;
    EditText MTCEmail,MTCPassword;
    Button MTCLogin;

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

    }

    @Override
    public void onClick(View v) {

        if(v==LoginAsAdmin){
            Intent i=new Intent(getApplicationContext(), AdminLoginActivity.class);
            startActivity(i);
        }
        else if(v==LoginAsLS)
        {
            Intent j=new Intent(getApplicationContext(), LSLoginActivity.class);
            startActivity(j);
        }
        else if(v==MTCLogin)
        {
            Intent j=new Intent(getApplicationContext(), MTCMainActivity.class);
            startActivity(j);
        }

    }
}
