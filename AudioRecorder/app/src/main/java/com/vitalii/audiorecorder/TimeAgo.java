package com.vitalii.audiorecorder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeAgo {

    public String getTimeAgo(long duratiion) {

        Date now = new Date();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - duratiion);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - duratiion);
        long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - duratiion);
        long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - duratiion);

        if(seconds < 60) {
            return "just now";
        } else if(minutes == 1) {
            return "a minute ago";
        } else if(minutes > 1 && minutes < 60) {
            return minutes + " minutes ago";
        } else if(hours == 1) {
            return "an hour ago";
        } else if(hours > 1 && hours < 24) {
            return hours+" hours ago";
        } else if(days == 1) {
            return "a day ago";
        } else {
            return days + " days ago";
        }
    }
}
