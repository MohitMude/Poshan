package com.iitism.poshan.Admin.Admin_LSDetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iitism.poshan.LS.LSRecyclerViewMain.ItemModel;
import com.iitism.poshan.R;

import java.util.List;

public class LSDetailsAdapter extends RecyclerView.Adapter<LSDetailsAdapter.ViewHolder> {

    private Context context;
    private List<LSDetailsModel> LSDetailList ;

    public LSDetailsAdapter(Context context, List<LSDetailsModel> lsdetailList) {
        this.context = context;
        LSDetailList = lsdetailList;
    }


    @NonNull
    @Override
    public LSDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.admin_ls_detail_card,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LSDetailsAdapter.ViewHolder viewHolder, int i) {
        LSDetailsModel lsDetailsModel=LSDetailList.get(i);
        viewHolder.aName.setText(lsDetailsModel.getName());
        viewHolder.aEmail.setText(lsDetailsModel.getEmail());
        viewHolder.aMobile.setText(lsDetailsModel.getMobile());
        viewHolder.acenter.setText(lsDetailsModel.getCenter());
        viewHolder.apassword.setText(lsDetailsModel.getPassword());

    }

    @Override
    public int getItemCount() {
        return LSDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView aName,aEmail,aMobile,acenter,apassword;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            aName=itemView.findViewById(R.id.txt_view_admin_ls_name);
            aEmail=itemView.findViewById(R.id.txt_view_admin_ls_email);
            aMobile=itemView.findViewById(R.id.txt_view_admin_ls_mobile_no);
            acenter=itemView.findViewById(R.id.txt_view_admin_ls_mtc_center);
            apassword=itemView.findViewById(R.id.txt_view_admin_ls_password);
        }
    }
}
