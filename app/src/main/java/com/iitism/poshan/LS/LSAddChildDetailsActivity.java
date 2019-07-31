package com.iitism.poshan.LS;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iitism.poshan.LS.LSRecyclerViewMain.ItemModel;
import com.iitism.poshan.R;



public class LSAddChildDetailsActivity extends AppCompatActivity implements View.OnClickListener {
     EditText mchildName,mFatherName,mMotherName,mAge,mPhone,mWeight,mHeight,mDistrict,mBlock,mGramPanchayat,mAddress,mparentsVisited,mSummary;
    private String ichildName,iFatherName,iMotherName,iAge,iPhone,iWeight,iHeight,iDistrict,iBlock,iGramPanchayat,iAddress,iparentsVisited,iSummary;
   private String iGender;
   int IsBPL,IsparentsSupporting;
    Button add;
    Spinner spinner;
   Switch bpl,ParentsSupporting;
   ArrayAdapter<CharSequence> spinneradapter;
    private Firebase mRootref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ls_add_child_details);
        Firebase.setAndroidContext(this);
        add=findViewById(R.id.btn_add_child_detail);
        mRootref1=new Firebase("https://poshan-app.firebaseio.com/");

        mchildName=findViewById(R.id.edt_txt_child_name);
        mFatherName=findViewById(R.id.edt_txt_father_name);
        mMotherName=findViewById(R.id.edt_txt_mother_name);
        mAge=findViewById(R.id.edt_txt_age);
        mPhone=findViewById(R.id.edt_txt_phone_number);
        mWeight=findViewById(R.id.edt_txt_weight);
        mHeight=findViewById(R.id.edt_txt_height);
        mDistrict=findViewById(R.id.edt_txt_district);
        mBlock=findViewById(R.id.edt_txt_block);
        mGramPanchayat=findViewById(R.id.edt_txt_gram_panchayat);
        mAddress=findViewById(R.id.edt_txt_address);
        mparentsVisited=findViewById(R.id.edt_txt_how_many_times_visited_parents);
        mSummary=findViewById(R.id.edt_txt_enter_summary);




        add.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        {
             String SAM="hello";

            ichildName=mchildName.getText().toString();
            iFatherName=mFatherName.getText().toString();
            iMotherName=mMotherName.getText().toString();
            iAge=mAge.getText().toString();
            iPhone=mPhone.getText().toString();
            iWeight=mWeight.getText().toString();
            iHeight=mHeight.getText().toString();
            iDistrict=mDistrict.getText().toString();
            iBlock=mBlock.getText().toString();
            iGramPanchayat=mGramPanchayat.getText().toString();
            iAddress=mAddress.getText().toString();
            iparentsVisited=mparentsVisited.getText().toString();
            iSummary=mSummary.getText().toString();



            Firebase childRef1=mRootref1.child("To Be Approved");
         /*   childRef1.child("ChildName").setValue(ichildName);
            childRef1.child("FatherName").setValue(iFatherName);
            childRef1.child("MotherName").setValue(iMotherName);
            childRef1.child("Age").setValue(iAge);
            childRef1.child("PhoneNo").setValue(iPhone);
            childRef1.child("Weight").setValue(iWeight);
            childRef1.child("Height").setValue(iHeight);
            childRef1.child("District").setValue(iDistrict);
            childRef1.child("Block").setValue(iBlock);
            childRef1.child("Gram Panchayat").setValue(iGramPanchayat);
            childRef1.child("Address").setValue(iAddress);
            childRef1.child("Parents Visited").setValue(iparentsVisited);
            childRef1.child("Summary").setValue(iSummary);
         */

         childRef1.push().setValue(new ItemModel(ichildName,iFatherName,iMotherName,"Male",iPhone,iAge,iWeight,iHeight,iAddress,iBlock,iDistrict,iGramPanchayat,iSummary,iparentsVisited,1,1));
            Toast.makeText(getApplicationContext(),"Data Uploaded ",Toast.LENGTH_LONG).show();

            Intent i=new Intent(getApplicationContext(),LSMainActivity.class);
            finish();
            startActivity(i);

        }

    }
}
