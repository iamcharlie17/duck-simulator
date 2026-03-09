package com.ducksimulator.models;

import com.ducksimulator.interfaces.Quackable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete duck model: Rubber Duck.
 *
 * <p>A bath-time favourite. Unlike real ducks, it makes a squeaky sound.
 * By implementing {@link Quackable}, it can still participate in the simulation
 * without modification — the interface does not enforce <em>how</em> something
 * quacks, only <em>that</em> it does.</p>
 */
public class RubberDuck implements Quackable {

    private static final Logger log = LoggerFactory.getLogger(RubberDuck.class);

    @Override
    public void quack() {
        log.info("Rubber Duck: Squeak!");
    }

    @Override
    public String toString() {
        return "RubberDuck";
    }
}
