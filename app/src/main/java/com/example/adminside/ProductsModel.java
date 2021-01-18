package com.example.adminside;

public class ProductsModel {

    String FirstName,LastName,Address1,Address2,Address3,Mobile,City,Pincode,ID;

    public ProductsModel(){}

    public ProductsModel(String FirstName,String LastName,String Address1,String Address2,String Address3,String Mobile,String City,String Pincode,String ID){
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.Address1=Address1;
        this.Address2=Address2;
        this.Address3=Address3;
        this.Mobile=Mobile;
        this.City=City;
        this.Pincode=Pincode;
        this.ID=ID;
    }



    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public String getAddress3() {
        return Address3;
    }

    public void setAddress3(String address3) {
        Address3 = address3;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
