package com.eren.parkinghouse.util;

import com.eren.parkinghouse.enums.ParkingTimeType;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by esimsek on 10/5/2016.
 */
public class TimeUtil {

    public static long CalculateHalfHours(Date startDate, Date endDate){

        if(startDate == null || endDate==null){
            throw new IllegalArgumentException("Paramter dates cannot be null!");
        }

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(startDate);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(endDate);

        long result = CalculateHalfHours(calendar1, calendar2);

        return result;
    }
    public static long CalculateHalfHours(Calendar calendar1, Calendar calendar2){

        if(calendar1 == null || calendar2 == null){
            throw new IllegalArgumentException("Paramter dates cannot be null!");
        }else if(calendar1.after(calendar2)){
            throw new IllegalArgumentException("Start date can not be after end date!");
        }

        long result = (((calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / (1000 * 60))/30)+1;

        return result;
    }

    public static long CalculateHalfHours(Calendar calendar1, Calendar calendar2, boolean calculateToAm){
        if(calendar1 == null || calendar2 == null){
            throw new IllegalArgumentException("Paramter dates cannot be null!");
        }else if(calendar1.after(calendar2)){
            throw new IllegalArgumentException("Start date can not be after end date!");
        }
        long result = 0;

        while(calendar1.before(calendar2)){
            if(calculateToAm && calendar1.get(Calendar.HOUR_OF_DAY) >= 7 && calendar1.get(Calendar.HOUR_OF_DAY) < 19){
                result++;
            } else if(!calculateToAm && !(calendar1.get(Calendar.HOUR_OF_DAY) >= 7 && calendar1.get(Calendar.HOUR_OF_DAY) < 19)){
                result++;
            }
            calendar1.add(Calendar.MINUTE,30);
        }
        return result;
    }
    public static long CalculateAMHalfHours(Calendar calendar1, Calendar calendar2){
        return CalculateHalfHours(calendar1,calendar2,true);
    }
    public static long CalculatePMHalfHours(Calendar calendar1, Calendar calendar2){
        return CalculateHalfHours(calendar1,calendar2,false);
    }
    public static long CalculateAMHalfHours(Date startDate, Date endDate){

        if(startDate == null || endDate==null){
            throw new IllegalArgumentException("Paramter dates cannot be null!");
        }

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(startDate);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(endDate);

        long result = CalculateAMHalfHours(calendar1, calendar2);

        return result;
    }
    public static long CalculatePMHalfHours(Date startDate, Date endDate){
        if(startDate == null || endDate==null){
            throw new IllegalArgumentException("Paramter dates cannot be null!");
        }

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(startDate);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(endDate);

        long result = CalculatePMHalfHours(calendar1, calendar2);

        return result;
    }
}
