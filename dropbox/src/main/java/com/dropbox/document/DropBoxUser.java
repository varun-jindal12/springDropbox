package com.dropbox.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;

@Document
public class DropBoxUser {
    @Id
    private String _id ;
    private String firstName;
    private String lastName;
    private String emailID;
    private String userPass;
    private String gender;
    private String dateOfBirth;
    private String workEdu;
    private String contactInfo;
    private String lifeEvents;
    private String musicInterest;
    private String movieInterest;
    private String sportsInterest;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getWorkEdu() {
        return workEdu;
    }

    public void setWorkEdu(String workEdu) {
        this.workEdu = workEdu;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getLifeEvents() {
        return lifeEvents;
    }

    public void setLifeEvents(String lifeEvents) {
        this.lifeEvents = lifeEvents;
    }

    public String getMusicInterest() {
        return musicInterest;
    }

    public void setMusicInterest(String musicInterest) {
        this.musicInterest = musicInterest;
    }

    public String getMovieInterest() {
        return movieInterest;
    }

    public void setMovieInterest(String movieInterest) {
        this.movieInterest = movieInterest;
    }

    public String getSportsInterest() {
        return sportsInterest;
    }

    public void setSportsInterest(String sportsInterest) {
        this.sportsInterest = sportsInterest;
    }
    @Override
    public String toString(){
        return get_id()+" "+getLastName()+" "+getFirstName()+" "+getEmailID()+" "+getUserPass();
    }
}
