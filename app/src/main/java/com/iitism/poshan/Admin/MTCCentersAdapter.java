package com.iitism.poshan.Admin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iitism.poshan.R;

import java.util.List;

public class MTCCentersAdapter extends RecyclerView.Adapter<MTCCentersAdapter.ViewHolder> {

      private Context context;
      private List<MTCCentersModel> mtclist;

    public MTCCentersAdapter(Context context, List<MTCCentersModel> mtclist) {
        this.context = context;
        this.mtclist = mtclist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_mtc_centers,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

           MTCCentersModel mtcCentersModel=mtclist.get(i);
           viewHolder.xname.setText(mtcCentersModel.getMTCName());
           viewHolder.xincharge.setText(mtcCentersModel.getIncharge());
           viewHolder.xaddress.setText(mtcCentersModel.getAddress());
           viewHolder.xemail.setText(mtcCentersModel.getEmail());
           viewHolder.xpassword.setText(mtcCentersModel.getPassword());
           viewHolder.xmobile.setText(mtcCentersModel.getMobile());
           viewHolder.xbeds.setText(String.valueOf(mtcCentersModel.getBeds()));

    }

    @Override
    public int getItemCount() {
        return mtclist.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView xname,xincharge,xaddress,xemail,xpassword,xmobile,xbeds;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            xname=itemView.findViewById(R.id.mtc_center_name_txt_view);
            xincharge=itemView.findViewById(R.id.mtc_center_incharge_txt_view);
            xaddress=itemView.findViewById(R.id.mtc_center_address_txt_view);
            xemail=itemView.findViewById(R.id.mtc_center_email_txt_view);
            xpassword=itemView.findViewById(R.id.mtc_center_password_txt_view);
            xmobile=itemView.findViewById(R.id.mtc_center_mobile_txt_view);
            xbeds=itemView.findViewById(R.id.mtc_center_beds_txt_view);

        }
    }
}
