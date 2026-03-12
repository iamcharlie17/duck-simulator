package com.ducksimulator.patterns.observer;

import com.ducksimulator.interfaces.Quackable;

import java.time.Instant;

public class QuackEvent {

    private final Quackable source;
    private final Instant timestamp;

    public QuackEvent(Quackable source) {
        if (source == null) throw new IllegalArgumentException("Source must not be null");
        this.source    = source;
        this.timestamp = Instant.now();
    }

    public Quackable getSource() {
        return source;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "QuackEvent{source=" + source + ", at=" + timestamp + "}";
    }
}
