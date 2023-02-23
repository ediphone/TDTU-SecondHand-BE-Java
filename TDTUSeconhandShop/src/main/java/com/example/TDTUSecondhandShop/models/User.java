package com.example.TDTUSecondhandShop.models;

public class User {
    private String email;
    private String name;
    private String avatar;
    private String gender;
    private String birthday;
    private String phone;
    private String personalEmail;
    private String genderHidden;
    private String birthdayHidden;
    private String personalEmailHidden;
    private String phoneHidden;

    public User() {
        this.email = "";
        this.name = "";
        this.avatar = "";
        this.gender = "";
        this.birthday = "";
        this.phone = "";
        this.personalEmail = "";
        this.genderHidden = "INACTIVE";
        this.birthdayHidden = "INACTIVE";
        this.personalEmailHidden = "INACTIVE";
        this.phoneHidden = "INACTIVE";
    }

    public User(String email, String name, String avatar, String gender, String birthday, String phone, String personalEmail, String genderHidden, String birthdayHidden, String personalEmailHidden, String phoneHidden) {
        this.email = email;
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.personalEmail = personalEmail;
        this.genderHidden = genderHidden;
        this.birthdayHidden = birthdayHidden;
        this.personalEmailHidden = personalEmailHidden;
        this.phoneHidden = phoneHidden;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getGenderHidden() {
        return genderHidden;
    }

    public void setGenderHidden(String genderHidden) {
        this.genderHidden = genderHidden;
    }

    public String getBirthdayHidden() {
        return birthdayHidden;
    }

    public void setBirthdayHidden(String birthdayHidden) {
        this.birthdayHidden = birthdayHidden;
    }

    public String getPersonalEmailHidden() {
        return personalEmailHidden;
    }

    public void setPersonalEmailHidden(String personalEmailHidden) {
        this.personalEmailHidden = personalEmailHidden;
    }

    public String getPhoneHidden() {
        return phoneHidden;
    }

    public void setPhoneHidden(String phoneHidden) {
        this.phoneHidden = phoneHidden;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                '}';
    }
}

