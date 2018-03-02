package com.vitalie.caloriesmanagement.util;

import java.time.LocalTime;

public class TimeUtil
{
    static public boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime)
    {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
}
