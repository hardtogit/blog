package com.thon.controller.util;

import com.thon.commons.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by thon on 8/30/14.
 */
public class DateTest {
    public static void main(String[] args){
        Date nowDate = new Date();
        Date beginDate = DateUtils.ceiling(nowDate, Calendar.DATE);
        Date endDate = DateUtils.truncate(nowDate, Calendar.DATE);

        System.out.println(DateUtils.formatDate(beginDate,"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils.formatDate(endDate,"yyyy-MM-dd HH:mm:ss"));
    }
}
