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
import java.util.stream.Collectors;

public class MealsUtil
{
    public static void main(String[] args)
    {
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2018, Month.MARCH, 30, 10, 0), "Breakfast", 500),
                new Meal(LocalDateTime.of(2018, Month.MARCH, 30, 13, 0), "Lunch", 1000),
                new Meal(LocalDateTime.of(2018, Month.MARCH, 30, 20, 0), "Dinner", 500),
                new Meal(LocalDateTime.of(2018, Month.MARCH, 31, 10, 0), "Breakfast", 1000),
                new Meal(LocalDateTime.of(2018, Month.MARCH, 31, 13, 0), "Lunch", 500),
                new Meal(LocalDateTime.of(2018, Month.MARCH, 31, 20, 0), "Dinner", 510)
        );

        List<MealWithExceed> mealsWithExceeded = getFilteredWithExceeded(meals, LocalTime.of(7, 0), LocalTime.of(15, 0), 2000);
        mealsWithExceeded.forEach(System.out::println);
    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay)
    {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(Collectors.groupingBy(meal -> meal.getDate(), Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                .map(meal -> new MealWithExceed(meal.getDateTime(),
                        meal.getDescription(),
                        meal.getCalories(),
                        caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
