package HealthTrackerSource.Classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class Time {

    //fields
    private DateFormat simpleFormat;
    private Date date; //date object declaration

    //constructor
    public Time(){
        date = new Date(); //date object
        simpleFormat = new SimpleDateFormat("MM/dd/yyyy"); //instantiate the date format expected
    }

    //we can access the formatted date string using this method
    // formatted using the SimpleDateFormat class above (in this case we are using "mm/dd/yyyy"
    public String getDateString(){
        long currentDate = date.getTime();
        return simpleFormat.format(currentDate);
    }

    //we can access the raw seconds since 1970... using this method
    //useful for sorting the dates
    public long getDateLong(){
        long currentDate = date.getTime();
        return currentDate;
    }

}
