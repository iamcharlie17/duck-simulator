package com.ducksimulator.patterns.observer;

import com.ducksimulator.interfaces.Quackable;
import com.ducksimulator.interfaces.QuackObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObservableQuackable implements Quackable {

    private final Quackable decoratee;
    private final List<QuackObserver> observers = new ArrayList<>();

    public ObservableQuackable(Quackable decoratee) {
        if (decoratee == null) throw new IllegalArgumentException("Decoratee must not be null");
        this.decoratee = decoratee;
    }

    public void registerObserver(QuackObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(QuackObserver observer) {
        observers.remove(observer);
    }

    public List<QuackObserver> getObservers() {
        return Collections.unmodifiableList(observers);
    }

    @Override
    public void quack() {
        decoratee.quack();
        notifyObservers();
    }

    private void notifyObservers() {
        QuackEvent event = new QuackEvent(this);
        observers.forEach(observer -> observer.onQuack(event));
    }

    @Override
    public String toString() {
        return "Observable[" + decoratee + "]";
    }
}
