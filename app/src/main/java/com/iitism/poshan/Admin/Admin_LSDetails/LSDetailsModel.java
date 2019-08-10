package com.iitism.poshan.Admin.Admin_LSDetails;

public class LSDetailsModel {

    String Name,Email,Center,mobile,password;

    public LSDetailsModel() {
    }

    public LSDetailsModel(String name, String Email, String Center,String mobile,String password) {
        Name = name;
        this.Email= Email;
        this.Center = Center;
        this.mobile = mobile;
        this.password=password;
    }

    public String getName() {
        return Name;
    }


    public String getEmail() {
        return Email;
    }

    public String getCenter() {
        return Center;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }
}
