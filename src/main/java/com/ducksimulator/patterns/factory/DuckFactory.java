package com.ducksimulator.patterns.factory;

import com.ducksimulator.interfaces.Quackable;
import com.ducksimulator.models.MallardDuck;
import com.ducksimulator.models.RedheadDuck;
import com.ducksimulator.models.RubberDuck;

public class DuckFactory {

    public Quackable createMallardDuck() {
        return new MallardDuck();
    }

    public Quackable createRedheadDuck() {
        return new RedheadDuck();
    }

    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
