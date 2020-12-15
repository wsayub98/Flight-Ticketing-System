package com.example.myflight;

class User2 {
    private String id,name, icnum, dob, gender, contactNum;

    public User2 (){

    }


    public User2(String id, String name, String icnum, String dob, String gender, String contactNum){
        this.id=id;
        this.name=name;
        this.icnum=icnum;
        this.dob=dob;
        this.gender=gender;
        this.contactNum=contactNum;
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

    public String getIcnum() {
        return icnum;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getContactNum() {
        return contactNum;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setIcnum(String icnum) {
        this.icnum = icnum;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public void getInput(String name, String icnum, String dob, String gender, String contactNum){
        this.name=name;
        this.icnum=icnum;
        this.dob=dob;
        this.gender=gender;
        this.contactNum=contactNum;
    }

}
