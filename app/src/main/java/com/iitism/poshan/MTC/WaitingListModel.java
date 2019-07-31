package com.iitism.poshan.MTC;

public class WaitingListModel {

   private String childname,fathername,mothername,age,block,address,phone;

    public WaitingListModel(String childname, String fathername, String mothername, String age, String block, String address, String phone) {
        this.childname = childname;
        this.fathername = fathername;
        this.mothername = mothername;
        this.age = age;
        this.block = block;
        this.address = address;
        this.phone = phone;
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
}
