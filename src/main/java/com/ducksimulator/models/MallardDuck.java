package com.ducksimulator.models;

import com.ducksimulator.interfaces.Quackable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete duck model: Mallard Duck.
 *
 * <p>A classic wild duck that quacks loudly. This is the primary
 * "real" duck in the simulation and serves as the baseline Quackable
 * implementation that all patterns enhance or wrap.</p>
 */
public class MallardDuck implements Quackable {

    private static final Logger log = LoggerFactory.getLogger(MallardDuck.class);

    @Override
    public void quack() {
        log.info("Mallard Duck: Quack!");
    }

    @Override
    public String toString() {
        return "MallardDuck";
    }
}
