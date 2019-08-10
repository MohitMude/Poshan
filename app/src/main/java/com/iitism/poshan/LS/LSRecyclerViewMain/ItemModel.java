package com.iitism.poshan.LS.LSRecyclerViewMain;

import android.location.Address;
import android.location.Location;

public class ItemModel {
     String ChildName,FatherName,PhoneNo,DaysLeft,Mothername,Age,Weight,Height,District;
     String GramPanchayat,Block,Gender,Summary,NumberOftimeParentsVisited,Location,Address,Date,nextDate;
     String AdharNo;
     int isApproved,isBPL,isParentssupporting;
    public ItemModel(String childName, String fatherName, String phoneNo, String daysLeft) {
        ChildName = childName;
        FatherName = fatherName;
        PhoneNo = phoneNo;
        DaysLeft = daysLeft;
    }

    public ItemModel(String childName, String fatherName,String mothername,String gender, String phoneNo,String location,String age, String weight, String height, String daysLeft,String address, String block, String district, String gramPanchayat, int isBPL, int isParentssupporting, String summary, String numberOftimeParentsVisited,int isApproved) {
        ChildName = childName;
        FatherName = fatherName;
        PhoneNo = phoneNo;
        DaysLeft = daysLeft;
        Mothername = mothername;
        Age = age;
        Weight = weight;
        Height = height;
        District = district;
        GramPanchayat = gramPanchayat;
        Address=address;
        Block = block;
        this.isBPL = isBPL;
        this.isParentssupporting = isParentssupporting;
        Gender = gender;
        Summary = summary;
        NumberOftimeParentsVisited = numberOftimeParentsVisited;
        Location = location;
        this.isApproved = isApproved;

    }

    public ItemModel(String childName, String fatherName, String mothername,String gender,String phoneNo, String age, String weight, String height, String address,String block,String district, String gramPanchayat,String adhar, int isBPL, String date,String nextdate) {
        ChildName = childName;
        FatherName = fatherName;
        PhoneNo = phoneNo;
        Mothername = mothername;
        Age = age;
        Weight = weight;
        Height = height;
        District = district;
        GramPanchayat = gramPanchayat;
        Block = block;
        Gender = gender;
        Address = address;
        AdharNo=adhar;
        this.isBPL = isBPL;
        Date=date;
        nextDate=nextdate;

    }

    public String getDate() {
        return Date;
    }

    public String getNextDate() {
        return nextDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getChildName() {
        return ChildName;
    }

    public String getFatherName() {
        return FatherName;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public String getDaysLeft() {
        return DaysLeft;
    }

    public void setChildName(String childName) {
        ChildName = childName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public void setDaysLeft(String daysLeft) {
        DaysLeft = daysLeft;
    }

    public String getMothername() {
        return Mothername;
    }

    public String getAge() {
        return Age;
    }

    public String getWeight() {
        return Weight;
    }

    public String getHeight() {
        return Height;
    }

    public String getDistrict() {
        return District;
    }

    public String getGramPanchayat() {
        return GramPanchayat;
    }

    public String getBlock() {
        return Block;
    }

    public int getIsBPL() {
        return isBPL;
    }

    public int getIsParentssupporting() {
        return isParentssupporting;
    }

    public String getGender() {
        return Gender;
    }

    public String getSummary() {
        return Summary;
    }

    public String getNumberOftimeParentsVisited() {
        return NumberOftimeParentsVisited;
    }

    public String getLocation() {
        return Location;
    }

    public int getIsApproved() {
        return isApproved;
    }

    public void setMothername(String mothername) {
        Mothername = mothername;
    }

    public void setAge(String age) {
        Age = age;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public void setGramPanchayat(String gramPanchayat) {
        GramPanchayat = gramPanchayat;
    }

    public void setBlock(String block) {
        Block = block;
    }

    public void setIsBPL(int isBPL) {
        this.isBPL = isBPL;
    }

    public void setIsParentssupporting(int isParentssupporting) {
        this.isParentssupporting = isParentssupporting;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public void setNumberOftimeParentsVisited(String numberOftimeParentsVisited) {
        NumberOftimeParentsVisited = numberOftimeParentsVisited;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setNextDate(String nextDate) {
        this.nextDate = nextDate;
    }

    public String getAdharNo() {
        return AdharNo;
    }

    public void setAdharNo(String adharNo) {
        AdharNo = adharNo;
    }
}
