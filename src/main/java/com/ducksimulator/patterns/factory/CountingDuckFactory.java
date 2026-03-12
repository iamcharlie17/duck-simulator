package com.ducksimulator.patterns.factory;

import com.ducksimulator.interfaces.Quackable;
import com.ducksimulator.patterns.decorator.QuackCounter;

public class CountingDuckFactory extends DuckFactory {

    @Override
    public Quackable createMallardDuck() {
        return new QuackCounter(super.createMallardDuck());
    }

    @Override
    public Quackable createRedheadDuck() {
        return new QuackCounter(super.createRedheadDuck());
    }

    @Override
    public Quackable createRubberDuck() {
        return new QuackCounter(super.createRubberDuck());
    }
}
