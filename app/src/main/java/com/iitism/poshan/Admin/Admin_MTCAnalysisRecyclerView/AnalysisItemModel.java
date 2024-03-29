package com.iitism.poshan.Admin.Admin_MTCAnalysisRecyclerView;

public class AnalysisItemModel {

    private int TotalBeds,VacantBeds,AboutToDischarge,SNo,AverageBedOccupancy;
    String TOTALBEDS,VACANTBEDS,ABOUTTODISCHARGE,AVERAGEBEDOCCUPANCY,CENTERNAME;
    private String CenterName;

    public AnalysisItemModel() {
    }

    public AnalysisItemModel(String TOTALBEDS, String VACANTBEDS, String ABOUTTODISCHARGE, String AVERAGEBEDOCCUPANCY, String CENTERNAME) {
        this.TOTALBEDS = TOTALBEDS;
        this.VACANTBEDS = VACANTBEDS;
        this.ABOUTTODISCHARGE = ABOUTTODISCHARGE;
        this.AVERAGEBEDOCCUPANCY = AVERAGEBEDOCCUPANCY;
        this.CENTERNAME = CENTERNAME;
    }

    public AnalysisItemModel(int totalBeds, int vacantBeds, int aboutToDischarge, int SNo, int averageBedOccupancy, String centerName) {
        TotalBeds = totalBeds;
        VacantBeds = vacantBeds;
        AboutToDischarge = aboutToDischarge;
        this.SNo = SNo;
        AverageBedOccupancy = averageBedOccupancy;
        CenterName = centerName;
    }

    public AnalysisItemModel(int totalBeds, int vacantBeds, int aboutToDischarge, int averageBedOccupancy, String centerName) {
        TotalBeds = totalBeds;
        VacantBeds = vacantBeds;
        AboutToDischarge = aboutToDischarge;
        AverageBedOccupancy = averageBedOccupancy;
        CenterName = centerName;
    }

    public int getTotalBeds() {
        return TotalBeds;
    }

    public int getVacantBeds() {
        return VacantBeds;
    }

    public int getAboutToDischarge() {
        return AboutToDischarge;
    }

    public String getCenterName() {
        return CenterName;
    }

    public int getSNo() {
        return SNo;
    }

    public int getAverageBedOccupancy() {
        return AverageBedOccupancy;
    }
}
