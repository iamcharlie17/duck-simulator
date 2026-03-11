package com.ducksimulator.patterns.composite;

import com.ducksimulator.interfaces.Quackable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Composite container that treats a group of {@link Quackable}s as a single unit.
 *
 * <hr>
 * <h3>Design Patterns: <em>Composite + Iterator</em></h3>
 *
 * <h4>Composite</h4>
 * <ul>
 *   <li><b>Intent:</b> Compose objects into tree structures and let clients
 *       treat individual objects and compositions uniformly.</li>
 *   <li>Because {@code Flock} itself implements {@link Quackable}, a flock can
 *       contain other flocks, creating an arbitrary duck hierarchy.</li>
 * </ul>
 *
 * <h4>Iterator</h4>
 * <ul>
 *   <li>{@code Flock} implements {@link Iterable}, exposing a sequential view
 *       of its members without revealing the internal {@link ArrayList}.</li>
 *   <li>This satisfies the Iterator Pattern's intent: provide a uniform way to
 *       traverse a collection without knowing its concrete type.</li>
 * </ul>
 *
 * <p><b>Real-world analogy:</b> A jazz ensemble — the conductor can signal
 * the whole band or a single musician; both understand "play".</p>
 */
public class Flock implements Quackable, Iterable<Quackable> {

    private static final Logger log = LoggerFactory.getLogger(Flock.class);

    /** Ordered list of all Quackable members in this flock. */
    private final List<Quackable> members = new ArrayList<>();

    /**
     * Adds a {@link Quackable} to this flock.
     *
     * @param duck the member to add; ignored if {@code null}
     */
    public void add(Quackable duck) {
        if (duck != null) {
            members.add(duck);
            log.debug("Flock: added member → {}", duck);
        }
    }

    /**
     * Commands every member of the flock to quack.
     * Because every member is a {@link Quackable}, sub-flocks quack recursively.
     */
    @Override
    public void quack() {
        log.info("-- Flock quacks ({} member{}) --", members.size(), members.size() == 1 ? "" : "s");
        members.forEach(Quackable::quack);
    }

    /**
     * Returns an iterator over this flock's direct members.
     * Backed by an unmodifiable copy so callers cannot mutate the flock.
     */
    @Override
    public Iterator<Quackable> iterator() {
        return Collections.unmodifiableList(members).iterator();
    }

    /**
     * Returns the number of direct members in this flock (does not count
     * members of nested flocks).
     */
    public int size() {
        return members.size();
    }

    @Override
    public String toString() {
        return "Flock[" + members.size() + " members]";
    }
}
