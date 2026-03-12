package com.ducksimulator.patterns.adapter;

import com.ducksimulator.interfaces.Quackable;
import com.ducksimulator.models.WildGoose;

public class GooseDuckAdapter implements Quackable {

    private final WildGoose goose;

    public GooseDuckAdapter(WildGoose goose) {
        if (goose == null) throw new IllegalArgumentException("Goose must not be null");
        this.goose = goose;
    }

    @Override
    public void quack() {
        goose.honk();
    }

    @Override
    public String toString() {
        return "GooseDuckAdapter[" + goose + "]";
    }
}
