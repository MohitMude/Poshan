package com.iitism.poshan.Admin;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iitism.poshan.Admin.Admin_LSDetails.LSDetailsAdapter;
import com.iitism.poshan.Admin.Admin_LSDetails.LSDetailsModel;
import com.iitism.poshan.LS.LSRecyclerViewMain.ItemModel;
import com.iitism.poshan.LS.LSRecyclerViewMain.RecyclerViewAdapterMain;
import com.iitism.poshan.R;

import java.util.ArrayList;
import java.util.List;

public class AdminLSDetailActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public List<LSDetailsModel> listitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lsdetail);

        recyclerView=findViewById(R.id.admin_ls_detail_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadRecyclerViewData();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    public void loadRecyclerViewData()
    {
        listitem = new ArrayList<>();
       for(int i=0;i<10;i++)
        {
           LSDetailsModel lsDetailsModel=new LSDetailsModel("XYZ","ABCD COLONY","CHAS","1234567890");
            listitem.add(lsDetailsModel);
        }
        adapter = new LSDetailsAdapter(getApplicationContext(), listitem);
        recyclerView.setAdapter(adapter);

    }
}
