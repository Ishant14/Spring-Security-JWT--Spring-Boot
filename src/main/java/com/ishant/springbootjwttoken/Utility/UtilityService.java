package com.ishant.springbootjwttoken.Utility;

import java.util.Calendar;
import java.util.Date;

public class UtilityService {

    private UtilityService(){
        //priate constructor
    }

    public static Date getTokenExpirationTime(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
        return cal.getTime();


    }
}
