package cn.com.sm.service.impl;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class TimeServiceImpl {

    public Integer getRemainingTime(){
        try {
            Date summerDate = new SimpleDateFormat("yyy-MM-dd").parse("2020-07-15");
            Date date = new Date();

            Integer day = Math.toIntExact(((summerDate.getTime()-date.getTime()) / (1000 * 60 * 60 * 24)));
            return day;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
