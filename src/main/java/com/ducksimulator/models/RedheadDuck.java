package com.ducksimulator.models;

import com.ducksimulator.interfaces.Quackable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete duck model: Redhead Duck.
 *
 * <p>A diving duck renowned for its vibrant red-head plumage. Like the Mallard,
 * it is a standard {@link Quackable} that participates fully in all patterns.</p>
 */
public class RedheadDuck implements Quackable {

    private static final Logger log = LoggerFactory.getLogger(RedheadDuck.class);

    @Override
    public void quack() {
        log.info("Redhead Duck: Quack!");
    }

    @Override
    public String toString() {
        return "RedheadDuck";
    }
}
