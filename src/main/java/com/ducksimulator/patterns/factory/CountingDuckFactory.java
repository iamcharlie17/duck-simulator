package com.ducksimulator.patterns.factory;

import com.ducksimulator.interfaces.Quackable;
import com.ducksimulator.patterns.decorator.QuackCounter;

/**
 * Extended factory that automatically wraps every produced duck in a
 * {@link QuackCounter} decorator.
 *
 * <hr>
 * <h3>Design Patterns: <em>Factory + Decorator</em></h3>
 * <p>By subclassing {@link DuckFactory}, this factory reuses all creation
 * logic and adds a counting wrapper transparently. The simulation code
 * never needs to know that counting is happening — it just receives a
 * {@link Quackable} as usual.</p>
 *
 * <p>This is a clean illustration of the Open/Closed Principle: the base
 * {@link DuckFactory} is closed for modification but open for extension.</p>
 */
public class CountingDuckFactory extends DuckFactory {

    /**
     * Creates a Mallard Duck wrapped in a {@link QuackCounter}.
     */
    @Override
    public Quackable createMallardDuck() {
        return new QuackCounter(super.createMallardDuck());
    }

    /**
     * Creates a Redhead Duck wrapped in a {@link QuackCounter}.
     */
    @Override
    public Quackable createRedheadDuck() {
        return new QuackCounter(super.createRedheadDuck());
    }

    /**
     * Creates a Rubber Duck wrapped in a {@link QuackCounter}.
     */
    @Override
    public Quackable createRubberDuck() {
        return new QuackCounter(super.createRubberDuck());
    }
}
