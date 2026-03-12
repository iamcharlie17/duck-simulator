package com.ducksimulator.patterns.decorator;

import com.ducksimulator.interfaces.Quackable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class QuackCounter implements Quackable {

    private static final Logger log = LoggerFactory.getLogger(QuackCounter.class);

    private static final AtomicInteger globalQuackCount = new AtomicInteger(0);

    private final Quackable decoratee;

    public QuackCounter(Quackable decoratee) {
        if (decoratee == null) throw new IllegalArgumentException("Decoratee must not be null");
        this.decoratee = decoratee;
    }

    @Override
    public void quack() {
        decoratee.quack();
        int total = globalQuackCount.incrementAndGet();
        log.debug("QuackCounter → total quacks so far: {}", total);
    }

    public static int getQuackCount() {
        return globalQuackCount.get();
    }

    public static void resetCount() {
        globalQuackCount.set(0);
    }

    @Override
    public String toString() {
        return "QuackCounter[" + decoratee + "]";
    }
}
