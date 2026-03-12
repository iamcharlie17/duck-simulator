package com.ducksimulator.models;

import com.ducksimulator.interfaces.Quackable;

public class RubberDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Rubber Duck: Squeak!");
    }

    @Override
    public String toString() {
        return "RubberDuck";
    }
}
