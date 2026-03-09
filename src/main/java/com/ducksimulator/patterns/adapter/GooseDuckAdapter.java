package com.ducksimulator.patterns.adapter;

import com.ducksimulator.interfaces.Quackable;
import com.ducksimulator.models.WildGoose;

/**
 * Adapter that allows a {@link WildGoose} to be used wherever a
 * {@link Quackable} is expected.
 *
 * <hr>
 * <h3>Design Pattern: <em>Adapter Pattern</em></h3>
 * <ul>
 *   <li><b>Intent:</b> Convert the interface of a class into another interface
 *       that the client expects. Adapter lets incompatible classes work together.</li>
 *   <li><b>Target interface:</b> {@link Quackable}</li>
 *   <li><b>Adaptee:</b> {@link WildGoose} (has {@code honk()}, not {@code quack()})</li>
 *   <li><b>Adapter:</b> This class — translates {@code quack()} into {@code honk()}.</li>
 * </ul>
 *
 * <p><b>Real-world analogy:</b> A power-plug travel adapter lets your European
 * appliance work in a US outlet without changing the appliance or the outlet.</p>
 */
public class GooseDuckAdapter implements Quackable {

    /** The adaptee — the goose whose interface is incompatible with {@code Quackable}. */
    private final WildGoose goose;

    /**
     * Constructs an adapter wrapping the given goose.
     *
     * @param goose the Wild Goose to adapt; must not be {@code null}
     */
    public GooseDuckAdapter(WildGoose goose) {
        if (goose == null) throw new IllegalArgumentException("Goose must not be null");
        this.goose = goose;
    }

    /**
     * Delegates the quack call to the goose's {@code honk()} method,
     * fulfilling the {@link Quackable} contract.
     */
    @Override
    public void quack() {
        goose.honk();   // translate quack → honk
    }

    @Override
    public String toString() {
        return "GooseDuckAdapter[" + goose + "]";
    }
}
