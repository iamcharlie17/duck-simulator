package com.ducksimulator.models;

import com.ducksimulator.interfaces.Quackable;

public class RedheadDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Redhead Duck: Quack!");
    }

    @Override
    public String toString() {
        return "RedheadDuck";
    }
}
