package com.ducksimulator.patterns.observer;

import com.ducksimulator.interfaces.QuackObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuackTracker implements QuackObserver {

    private static final Logger log = LoggerFactory.getLogger(QuackTracker.class);

    private final List<QuackEvent> eventLog = new ArrayList<>();

    @Override
    public void onQuack(QuackEvent event) {
        eventLog.add(event);
        log.debug("QuackTracker observed: {}", event.getSource());
    }

    public int getTrackedQuackCount() {
        return eventLog.size();
    }

    public List<QuackEvent> getEventLog() {
        return Collections.unmodifiableList(eventLog);
    }

    public void reset() {
        eventLog.clear();
    }

    @Override
    public String toString() {
        return "QuackTracker";
    }
}
