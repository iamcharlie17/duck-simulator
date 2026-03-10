package com.ducksimulator.patterns.factory;

import com.ducksimulator.interfaces.Quackable;
import com.ducksimulator.models.MallardDuck;
import com.ducksimulator.models.RedheadDuck;
import com.ducksimulator.models.RubberDuck;

/**
 * Basic factory that creates plain {@link Quackable} duck objects.
 *
 * <hr>
 * <h3>Design Pattern: <em>Factory Method / Abstract Factory Pattern</em></h3>
 * <ul>
 *   <li><b>Intent:</b> Define an interface for creating objects but let
 *       subclasses (or sibling classes) decide which class to instantiate.</li>
 *   <li><b>Benefit:</b> The {@code DuckSimulator} never calls {@code new} on a
 *       concrete duck class directly — all construction is centralised here,
 *       making it trivial to swap implementations.</li>
 * </ul>
 *
 * <p><b>Real-world analogy:</b> A bird sanctuary's intake office — visitors
 * don't choose which cage a bird goes in; they hand the bird to the office
 * and it handles appropriate placement.</p>
 */
public class DuckFactory {

    /**
     * Creates a new {@link MallardDuck}.
     *
     * @return a fresh Mallard Duck
     */
    public Quackable createMallardDuck() {
        return new MallardDuck();
    }

    /**
     * Creates a new {@link RedheadDuck}.
     *
     * @return a fresh Redhead Duck
     */
    public Quackable createRedheadDuck() {
        return new RedheadDuck();
    }

    /**
     * Creates a new {@link RubberDuck}.
     *
     * @return a fresh Rubber Duck
     */
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
