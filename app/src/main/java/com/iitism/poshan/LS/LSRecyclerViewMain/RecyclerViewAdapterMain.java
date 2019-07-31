package com.iitism.poshan.LS.LSRecyclerViewMain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iitism.poshan.R;

import java.util.List;

public class RecyclerViewAdapterMain extends RecyclerView.Adapter<RecyclerViewAdapterMain.ViewHolder> {

    private Context context;
    private List<ItemModel> DetailList ;

    public RecyclerViewAdapterMain(Context context, List<ItemModel> detailList) {
        this.context = context;
        DetailList = detailList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_ls_main,viewGroup,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        ItemModel itemModel=DetailList.get(i);
        holder.mChildName.setText(itemModel.getChildName());
        holder.mFatherName.setText(itemModel.getFatherName());
        holder.mDaysLeft.setText(itemModel.getDaysLeft());
        holder.mPhoneNo.setText(itemModel.getPhoneNo());

    }

    @Override
    public int getItemCount() {
    return DetailList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mChildName,mFatherName,mDaysLeft,mPhoneNo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mChildName=itemView.findViewById(R.id.txt_view_child_name_card);
            mFatherName=itemView.findViewById(R.id.txt_view_father_name_card);
            mDaysLeft=itemView.findViewById(R.id.txt_view_no_of_days_left_card);
            mPhoneNo=itemView.findViewById(R.id.txt_view_mobile_no_card);

        }
    }

}
