package com.iitism.poshan.Admin.Admin_MTCAnalysisRecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iitism.poshan.R;

import java.util.List;

public class AnalysisAdapter extends RecyclerView.Adapter<AnalysisAdapter.ViewHolder> {

    private Context context;
    private List<AnalysisItemModel> DetailList ;

    public AnalysisAdapter(Context context, List<AnalysisItemModel> detailList) {
        this.context = context;
        DetailList = detailList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.mtc_center_analysis_card,viewGroup,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        AnalysisItemModel analysisItemModel=DetailList.get(i);
        holder.mCenterName.setText(analysisItemModel.getCenterName());
        holder.mTotalBeds.setText(String.valueOf(analysisItemModel.getTotalBeds()));
        holder.mVacantBeds.setText(String.valueOf(analysisItemModel.getVacantBeds()));
        holder.mABoutToDischarge.setText(String.valueOf(analysisItemModel.getAboutToDischarge()));
        holder.mBedOccupancy.setText(String.valueOf(analysisItemModel.getAverageBedOccupancy()));
        holder.mSno.setText(String.valueOf(analysisItemModel.getSNo()));
    }

    @Override
    public int getItemCount() {
        return DetailList.size();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mCenterName,mTotalBeds,mVacantBeds,mABoutToDischarge,mSno,mBedOccupancy;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mCenterName=itemView.findViewById(R.id.txt_view_mtc_analysis_name);
            mTotalBeds=itemView.findViewById(R.id.txt_view_mtc_analysis_total_beds);
            mVacantBeds=itemView.findViewById(R.id.txt_view_mtc_analysis_vacant_beds);
            mABoutToDischarge=itemView.findViewById(R.id.txt_view_mtc_analysis_about_to_discharge);
            mSno=itemView.findViewById(R.id.txt_view_mtc_analysis_s_no);
            mBedOccupancy=itemView.findViewById(R.id.txt_view_mtc_analysis_average_bed_occupancy);

        }
    }


}

