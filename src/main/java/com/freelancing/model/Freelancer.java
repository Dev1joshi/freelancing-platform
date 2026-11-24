
package com.freelancing.model;

public class Freelancer {

    private int freelancerId;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private int experienceYears;
    private String bio;

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
