package com.exple.stray_care;

public class userdonateNgodatadonate {
    private String id,name,email,mobile,address;

    public userdonateNgodatadonate() {
    }

    public userdonateNgodatadonate(String id,String name, String email,String mobile,String address) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;

    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address= address;


    }

}
