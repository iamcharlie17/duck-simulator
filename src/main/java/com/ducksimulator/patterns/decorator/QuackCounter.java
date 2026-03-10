package com.ducksimulator.patterns.decorator;

import com.ducksimulator.interfaces.Quackable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Decorator that wraps any {@link Quackable} and counts each quack call.
 *
 * <hr>
 * <h3>Design Pattern: <em>Decorator Pattern</em></h3>
 * <ul>
 *   <li><b>Intent:</b> Attach additional responsibilities to an object
 *       dynamically without subclassing.</li>
 *   <li><b>Component:</b> {@link Quackable}</li>
 *   <li><b>Concrete Decorator:</b> This class — adds quack-counting behaviour.</li>
 * </ul>
 *
 * <p>A shared {@link AtomicInteger} counter is used so that a single total
 * is maintained across <em>all</em> {@code QuackCounter} instances in the
 * simulation — reflecting the global quack count for the entire pond.</p>
 *
 * <p><b>Real-world analogy:</b> A coin wrapper around a piggy-bank slot that
 * counts each coin dropped in, while the piggy-bank itself remains unchanged.</p>
 */
public class QuackCounter implements Quackable {

    private static final Logger log = LoggerFactory.getLogger(QuackCounter.class);

    /** Shared across all QuackCounter instances — global pond quack tally. */
    private static final AtomicInteger globalQuackCount = new AtomicInteger(0);

    /** The real Quackable being counted. */
    private final Quackable decoratee;

    /**
     * Wraps the given {@link Quackable} with quack-counting behaviour.
     *
     * @param decoratee the duck (or adapted goose) to count; must not be {@code null}
     */
    public QuackCounter(Quackable decoratee) {
        if (decoratee == null) throw new IllegalArgumentException("Decoratee must not be null");
        this.decoratee = decoratee;
    }

    /**
     * Delegates quacking to the wrapped object and increments the global counter.
     */
    @Override
    public void quack() {
        decoratee.quack();
        int total = globalQuackCount.incrementAndGet();
        log.debug("QuackCounter → total quacks so far: {}", total);
    }

    /**
     * Returns the total number of quacks recorded across all {@code QuackCounter}
     * instances since the counter was last reset.
     *
     * @return global quack count (≥ 0)
     */
    public static int getQuackCount() {
        return globalQuackCount.get();
    }

    /**
     * Resets the global counter to zero.
     * Useful when running multiple independent simulations.
     */
    public static void resetCount() {
        globalQuackCount.set(0);
    }

    @Override
    public String toString() {
        return "QuackCounter[" + decoratee + "]";
    }
}
