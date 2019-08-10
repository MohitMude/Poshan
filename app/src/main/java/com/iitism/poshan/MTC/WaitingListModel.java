package com.iitism.poshan.MTC;

public class WaitingListModel {

   private String childname,fathername,mothername,age,block,address,phone,adhar,center,height,weight,SAM,critical;

    public WaitingListModel(String childname, String fathername, String mothername, String age, String block, String address, String phone, String adhar, String center, String height, String weight, String critical) {
        this.childname = childname;
        this.fathername = fathername;
        this.mothername = mothername;
        this.age = age;
        this.block = block;
        this.address = address;
        this.phone = phone;
        this.adhar = adhar;
        this.center = center;
        this.height = height;
        this.weight = weight;
        this.critical = critical;
    }

    public WaitingListModel(String childname, String fathername, String mothername, String age, String block, String address, String phone, String adhar, String center) {
        this.childname = childname;
        this.fathername = fathername;
        this.mothername = mothername;
        this.age = age;
        this.block = block;
        this.address = address;
        this.phone = phone;
        this.adhar = adhar;
        this.center=center;
    }

    public WaitingListModel(String childname, String fathername, String mothername, String age, String block, String address, String phone) {
        this.childname = childname;
        this.fathername = fathername;
        this.mothername = mothername;
        this.age = age;
        this.block = block;
        this.address = address;
        this.phone = phone;
    }

    public WaitingListModel(String childname, String fathername, String mothername, String age, String block, String address, String phone, String adhar, String center, String height, String weight) {
        this.childname = childname;
        this.fathername = fathername;
        this.mothername = mothername;
        this.age = age;
        this.block = block;
        this.address = address;
        this.phone = phone;
        this.adhar = adhar;
        this.center = center;
        this.height = height;
        this.weight = weight;
    }



    public String getCritical() {
        return critical;
    }

    public String getCenter() {
        return center;
    }

    public String getAdhar() {
        return adhar;
    }

    public String getChildname() {
        return childname;
    }

    public String getFathername() {
        return fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public String getAge() {
        return age;
    }

    public String getBlock() {
        return block;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getSAM() {
        return SAM;
    }
}
