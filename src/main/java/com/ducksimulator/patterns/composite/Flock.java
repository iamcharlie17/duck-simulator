package com.ducksimulator.patterns.composite;

import com.ducksimulator.interfaces.Quackable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Flock implements Quackable, Iterable<Quackable> {

    private static final Logger log = LoggerFactory.getLogger(Flock.class);

    private final List<Quackable> members = new ArrayList<>();

    public void add(Quackable duck) {
        if (duck != null) {
            members.add(duck);
            log.debug("Flock: added member → {}", duck);
        }
    }

    @Override
    public void quack() {
        log.info("-- Flock quacks ({} member{}) --", members.size(), members.size() == 1 ? "" : "s");
        members.forEach(Quackable::quack);
    }

    @Override
    public Iterator<Quackable> iterator() {
        return Collections.unmodifiableList(members).iterator();
    }

    public int size() {
        return members.size();
    }

    @Override
    public String toString() {
        return "Flock[" + members.size() + " members]";
    }
}
