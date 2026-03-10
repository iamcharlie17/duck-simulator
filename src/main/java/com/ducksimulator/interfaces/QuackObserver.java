package com.ducksimulator.interfaces;

import com.ducksimulator.patterns.observer.QuackEvent;

/**
 * Observer interface for the Observer Pattern.
 *
 * <p>Design Pattern: <strong>Observer Pattern</strong> — defines the "listener" contract.</p>
 *
 * <p>Any object that wants to be notified of quack events must implement this
 * interface. The {@link QuackEvent} carries the source of the quack, providing
 * full context to the observer without coupling it to the concrete duck type.</p>
 */
public interface QuackObserver {

    /**
     * Called whenever a quack occurs on an observed {@link Quackable}.
     *
     * @param event the event object describing what quacked and when
     */
    void onQuack(QuackEvent event);
}
