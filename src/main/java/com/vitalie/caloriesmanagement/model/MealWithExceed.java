package com.vitalie.caloriesmanagement.model;

import java.time.LocalDateTime;

public class MealWithExceed
{
    private LocalDateTime dateTime;
    private String description;
    private int calories;
    private boolean exceeded;

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceeded)
    {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceeded = exceeded;
    }

    @Override
    public String toString()
    {
        return "MealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceeded=" + exceeded +
                '}';
    }
}
