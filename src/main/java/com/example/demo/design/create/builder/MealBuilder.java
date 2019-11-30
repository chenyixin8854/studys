package com.example.demo.design.create.builder;

import com.example.demo.design.create.builder.item.ChickenBurger;
import com.example.demo.design.create.builder.item.Coke;
import com.example.demo.design.create.builder.item.Pepsi;
import com.example.demo.design.create.builder.item.VegBurger;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 00:43
 **/
public class MealBuilder {

    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
