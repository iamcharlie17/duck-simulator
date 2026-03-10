package com.ducksimulator.patterns.observer;

import com.ducksimulator.interfaces.Quackable;
import com.ducksimulator.interfaces.QuackObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Decorator that adds Observable behaviour to any {@link Quackable}.
 *
 * <hr>
 * <h3>Design Patterns: <em>Decorator + Observer</em></h3>
 * <ul>
 *   <li><b>Decorator role:</b> Wraps an existing {@link Quackable} and adds
 *       observer management without altering the wrapped object.</li>
 *   <li><b>Subject role (Observer):</b> Maintains a list of
 *       {@link QuackObserver}s and notifies them after every {@code quack()}.</li>
 * </ul>
 *
 * <p>By combining both patterns, any {@code Quackable} — duck, goose adapter,
 * counter — can become observable with a single wrapper call.</p>
 */
public class ObservableQuackable implements Quackable {

    /** The wrapped quackable whose behaviour we are augmenting. */
    private final Quackable decoratee;

    /** Registered observers; protected from external modification. */
    private final List<QuackObserver> observers = new ArrayList<>();

    /**
     * Wraps the given {@link Quackable} with observable support.
     *
     * @param decoratee the target to wrap; must not be {@code null}
     */
    public ObservableQuackable(Quackable decoratee) {
        if (decoratee == null) throw new IllegalArgumentException("Decoratee must not be null");
        this.decoratee = decoratee;
    }

    /**
     * Registers an observer to receive quack notifications.
     *
     * @param observer the observer to add; ignored if already registered
     */
    public void registerObserver(QuackObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Removes a previously registered observer.
     *
     * @param observer the observer to remove
     */
    public void removeObserver(QuackObserver observer) {
        observers.remove(observer);
    }

    /**
     * Returns an unmodifiable view of all registered observers.
     */
    public List<QuackObserver> getObservers() {
        return Collections.unmodifiableList(observers);
    }

    /**
     * Delegates quacking to the wrapped object, then notifies all observers.
     */
    @Override
    public void quack() {
        decoratee.quack();
        notifyObservers();
    }

    /** Dispatches a {@link QuackEvent} to every registered observer. */
    private void notifyObservers() {
        QuackEvent event = new QuackEvent(this);
        observers.forEach(observer -> observer.onQuack(event));
    }

    @Override
    public String toString() {
        return "Observable[" + decoratee + "]";
    }
}
