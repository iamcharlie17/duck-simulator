package com.ducksimulator.patterns.observer;

import com.ducksimulator.interfaces.Quackable;

import java.time.Instant;

/**
 * Immutable event object raised whenever a {@link Quackable} emits a quack.
 *
 * <hr>
 * <h3>Design Pattern: <em>Observer Pattern</em> — Event Object</h3>
 * <p>The {@code QuackEvent} is passed from the subject (an
 * {@link ObservableQuackable}) to each registered {@link com.ducksimulator.interfaces.QuackObserver}.
 * Encapsulating the event in its own class keeps the observer interface clean and
 * makes it trivial to add new event metadata (e.g., location, sound intensity)
 * without breaking existing observers.</p>
 */
public class QuackEvent {

    /** The duck or goose that produced the quack. */
    private final Quackable source;

    /** The precise moment the quack occurred. */
    private final Instant timestamp;

    /**
     * Constructs a new event for the given quacking source.
     *
     * @param source the entity that quacked; must not be {@code null}
     */
    public QuackEvent(Quackable source) {
        if (source == null) throw new IllegalArgumentException("Source must not be null");
        this.source    = source;
        this.timestamp = Instant.now();
    }

    /** @return the {@link Quackable} that triggered this event */
    public Quackable getSource() {
        return source;
    }

    /** @return the exact timestamp this event was created */
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "QuackEvent{source=" + source + ", at=" + timestamp + "}";
    }
}
