package com.iitism.poshan.MTC;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iitism.poshan.R;

import org.w3c.dom.Text;

import java.util.List;

public class WaitingListAdapter extends RecyclerView.Adapter<WaitingListAdapter.ViewHolder> {

    Context context;
    List<WaitingListModel> waitinglist;

    public WaitingListAdapter(Context context, List<WaitingListModel> waitinglist) {
        this.context = context;
        this.waitinglist = waitinglist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_waiting_list,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
      WaitingListModel waitingListModel=waitinglist.get(i);
      viewHolder.txtchildname.setText(waitingListModel.getChildname());
        viewHolder.txtfathername.setText(waitingListModel.getFathername());
        viewHolder.txtmothername.setText(waitingListModel.getMothername());
        viewHolder.txtage.setText(waitingListModel.getAge());
        viewHolder.txtblock.setText(waitingListModel.getBlock());
        viewHolder.txtaddress.setText(waitingListModel.getAddress());
        viewHolder.txtphone.setText(waitingListModel.getPhone());

    }

    @Override
    public int getItemCount() {
        return waitinglist.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
       TextView txtchildname,txtfathername,txtmothername,txtage,txtblock,txtaddress,txtphone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtchildname=itemView.findViewById(R.id.waiting_list_child_name);
            txtfathername=itemView.findViewById(R.id.waiting_list_father_name);
            txtmothername=itemView.findViewById(R.id.waiting_list_mother_name);
            txtage=itemView.findViewById(R.id.waiting_list_age);
            txtblock=itemView.findViewById(R.id.waiting_list_block);
            txtaddress=itemView.findViewById(R.id.waiting_list_address);
            txtphone=itemView.findViewById(R.id.waiting_list_phone);
        }
    }
}
