package HealthTrackerSource.Classes;

/*
DataEntry class is necessary to map the hash-maps containing the daily slider data and comment data into
the TableView.

The DataEntry class is a static class that corresponds to the individual data for each day.
 */

import javafx.beans.property.SimpleStringProperty;

public class DataEntry {
    SimpleStringProperty date;
    SimpleStringProperty comment;
    int mentalNum;
    int physicalNum;
    int happinessNum;

    //constructor
    DataEntry(String date, String comment, int mentalNum, int physicalNum, int happinessNum){
        this.date = new SimpleStringProperty(date);
        this.comment = new SimpleStringProperty(comment);
        this.mentalNum = mentalNum;
        this.physicalNum = physicalNum;
        this.happinessNum = happinessNum;
    }


    //getter and setter for date
    public String getDate(){
        return date.get();
    }
    public void setDate(String newDate){
        date.set(newDate);
    }

    //getter and setter for comment
    public String getComment(){
        return comment.get();
    }
    public void setComment(String newComment){
        comment.set(newComment);
    }

    //getter and setter for mentalNum
    public int getMentalNum(){
        return mentalNum;
    }
    public void setMentalNum(int newMentalNum){
        mentalNum = newMentalNum;
    }

    //getter and setter for physicalNum
    public int getPhysicalNum() {
        return physicalNum;
    }
    public void setPhysicalNum(int newPhysicalNum) {
        this.physicalNum = newPhysicalNum;
    }

    //getter and setter for happinessNum
    public int getHappinessNum() {
        return happinessNum;
    }
    public void setHappinessNum(int newHappinessNum) {
        this.happinessNum = newHappinessNum;
    }

}
