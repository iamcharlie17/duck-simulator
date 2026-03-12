package com.ducksimulator.interfaces;

import com.ducksimulator.patterns.observer.QuackEvent;

public interface QuackObserver {
    void onQuack(QuackEvent event);
}
