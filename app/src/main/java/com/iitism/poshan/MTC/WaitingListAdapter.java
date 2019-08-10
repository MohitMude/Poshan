package com.iitism.poshan.MTC;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iitism.poshan.Admin.AddMTCCenter;
import com.iitism.poshan.Admin.GraphActivity;
import com.iitism.poshan.R;

import org.w3c.dom.Text;

import java.util.List;

public class WaitingListAdapter extends RecyclerView.Adapter<WaitingListAdapter.ViewHolder> {

    String adhar;
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
      viewHolder.relativeLayout.setTag(i);
      String a="Name : "+waitingListModel.getChildname();
      String b="Father Name:"+waitingListModel.getFathername();
      String c="Mother Name : "+waitingListModel.getMothername();
      String d="Age:  "+waitingListModel.getAge();
      String e="Block:"+waitingListModel.getBlock();
      String f="Address: "+waitingListModel.getAddress();
      String g="Phone: "+waitingListModel.getPhone();
      viewHolder.txtchildname.setText(a);
        viewHolder.txtfathername.setText(b);
        viewHolder.txtmothername.setText(c);
        viewHolder.txtage.setText(d);
        viewHolder.txtblock.setText(e);
        viewHolder.txtaddress.setText(f);
        viewHolder.txtphone.setText(g);



    }

    @Override
    public int getItemCount() {
        return waitinglist.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       TextView txtchildname,txtfathername,txtmothername,txtage,txtblock,txtaddress,txtphone;
       RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtchildname=itemView.findViewById(R.id.waiting_list_child_name);
            txtfathername=itemView.findViewById(R.id.waiting_list_father_name);
            txtmothername=itemView.findViewById(R.id.waiting_list_mother_name);
            txtage=itemView.findViewById(R.id.waiting_list_age);
            txtblock=itemView.findViewById(R.id.waiting_list_block);
            txtaddress=itemView.findViewById(R.id.waiting_list_address);
            txtphone=itemView.findViewById(R.id.waiting_list_phone);
            relativeLayout=itemView.findViewById(R.id.relative_layout_waiting_list);
            relativeLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=(int)v.getTag();
            adhar=waitinglist.get(position).getAdhar();
             {
                Intent i = new Intent(context, SAMDialog.class);
                i.putExtra("Adhar", adhar);
                i.putExtra("Child Name", waitinglist.get(position).getChildname());
                i.putExtra("Father Name", waitinglist.get(position).getFathername());
                i.putExtra("Mother Name", waitinglist.get(position).getMothername());
                i.putExtra("Center", waitinglist.get(position).getCenter());
                i.putExtra("Height", waitinglist.get(position).getHeight());
                i.putExtra("Weight", waitinglist.get(position).getWeight());

                Toast.makeText(v.getContext(), adhar + waitinglist.get(position).getChildname(), Toast.LENGTH_LONG).show();
                context.startActivity(i);
            }



        }
    }


}
