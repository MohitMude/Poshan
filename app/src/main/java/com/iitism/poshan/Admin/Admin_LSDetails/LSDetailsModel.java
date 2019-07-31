package com.iitism.poshan.Admin.Admin_LSDetails;

public class LSDetailsModel {

    String Name,Address,MTC,mobile;

    public LSDetailsModel() {
    }

    public LSDetailsModel(String name, String address, String MTC,String mobile) {
        Name = name;
        Address = address;
        this.MTC = MTC;
        this.mobile = mobile;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getMTC() {
        return MTC;
    }

    public String getMobile() {
        return mobile;
    }
}
