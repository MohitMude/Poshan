package com.iitism.poshan.Admin;

public class MTCCentersModel {

    String MTCName,Incharge,Address,Email,Password, mobile;
    long beds;

    public MTCCentersModel(String MTCName, String incharge, String address, String email, String password, String mobile, long beds) {
        this.MTCName = MTCName;
        Incharge = incharge;
        Address = address;
        Email = email;
        Password = password;
        this.mobile = mobile;
        this.beds = beds;
    }

    public String getMTCName() {
        return MTCName;
    }

    public String getIncharge() {
        return Incharge;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getMobile() {
        return mobile;
    }

    public long getBeds() {
        return beds;
    }
}
