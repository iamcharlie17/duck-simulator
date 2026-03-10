package com.ducksimulator.patterns.observer;

import com.ducksimulator.interfaces.QuackObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Concrete observer that tracks every quack event it receives.
 *
 * <hr>
 * <h3>Design Pattern: <em>Observer Pattern</em> — Concrete Observer</h3>
 * <p>{@code QuackTracker} records each {@link QuackEvent} it receives from
 * subscribed {@link ObservableQuackable} subjects. At any point, callers can
 * query the full history or simply the total count of tracked quacks.</p>
 *
 * <p><b>Real-world analogy:</b> A wildlife researcher sitting by the pond,
 * logging every sound they hear into a notebook.</p>
 */
public class QuackTracker implements QuackObserver {

    private static final Logger log = LoggerFactory.getLogger(QuackTracker.class);

    /** Immutable record of all quack events witnessed. */
    private final List<QuackEvent> eventLog = new ArrayList<>();

    /**
     * Called by an {@link ObservableQuackable} subject each time it quacks.
     * Stores the event and logs its receipt.
     */
    @Override
    public void onQuack(QuackEvent event) {
        eventLog.add(event);
        log.debug("QuackTracker observed: {}", event.getSource());
    }

    /**
     * Returns the total number of quacks this tracker has witnessed.
     *
     * @return quack count (≥ 0)
     */
    public int getTrackedQuackCount() {
        return eventLog.size();
    }

    /**
     * Returns an unmodifiable view of the full event history.
     *
     * @return ordered list of all witnessed {@link QuackEvent}s
     */
    public List<QuackEvent> getEventLog() {
        return Collections.unmodifiableList(eventLog);
    }

    /** Clears all recorded events (useful between simulation runs). */
    public void reset() {
        eventLog.clear();
    }

    @Override
    public String toString() {
        return "QuackTracker";
    }
}
