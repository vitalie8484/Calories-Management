package com.vitalie.caloriesmanagement.util;

import com.vitalie.caloriesmanagement.model.Meal;
import com.vitalie.caloriesmanagement.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealsUtil
{
    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2018, Month.MARCH, 30, 10, 0), "Breakfast", 500),
            new Meal(LocalDateTime.of(2018, Month.MARCH, 30, 13, 0), "Lunch", 1000),
            new Meal(LocalDateTime.of(2018, Month.MARCH, 30, 20, 0), "Dinner", 500),
            new Meal(LocalDateTime.of(2018, Month.MARCH, 31, 10, 0), "Breakfast", 1000),
            new Meal(LocalDateTime.of(2018, Month.MARCH, 31, 13, 0), "Lunch", 500),
            new Meal(LocalDateTime.of(2018, Month.MARCH, 31, 20, 0), "Dinner", 510)
    );

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static List<MealWithExceed> getWithExceeded(List<Meal> meals, int caloriesPerDay)
    {
        return getFilteredWithExceeded(meals, caloriesPerDay, meal -> true);
    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> meals, int caloriesPerDay, LocalTime startTime, LocalTime endTime)
    {
        return  getFilteredWithExceeded(meals, caloriesPerDay, meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime));
    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> meals, int caloriesPerDay, Predicate<Meal> filter)
    {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(Collectors.groupingBy(meal -> meal.getDate(), Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .filter(filter)
                .map(meal -> createWithExceeded(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(toList());
    }

    public static MealWithExceed createWithExceeded(Meal meal, boolean exceeded)
    {
        return new MealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }
}
