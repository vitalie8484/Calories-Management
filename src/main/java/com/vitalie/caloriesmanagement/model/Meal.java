package com.vitalie.caloriesmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal
{
    private LocalDateTime dateTime;
    private String description;
    private int calories;

    public Meal(LocalDateTime dateTime, String description, int calories)
    {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public LocalDate getDate()
    {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime()
    {
        return dateTime.toLocalTime();
    }

    public String getDescription()
    {
        return description;
    }

    public int getCalories()
    {
        return calories;
    }
}
