package com.ducksimulator.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Goose model — does NOT implement {@link com.ducksimulator.interfaces.Quackable}.
 *
 * <p>A Wild Goose has its own method of vocalisation ({@link #honk()}),
 * which is incompatible with the {@code Quackable} interface used by the
 * rest of the simulation. The {@link com.ducksimulator.patterns.adapter.GooseDuckAdapter}
 * bridges this incompatibility.</p>
 */
public class WildGoose {

    private static final Logger log = LoggerFactory.getLogger(WildGoose.class);

    /** Produces the goose's natural honking sound. */
    public void honk() {
        log.info("Wild Goose: Honk! Honk!");
    }

    @Override
    public String toString() {
        return "WildGoose";
    }
}
