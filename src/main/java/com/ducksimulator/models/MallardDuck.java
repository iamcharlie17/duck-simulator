package com.ducksimulator.models;

import com.ducksimulator.interfaces.Quackable;

public class MallardDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Mallard Duck: Quack!");
    }

    @Override
    public String toString() {
        return "MallardDuck";
    }
}
