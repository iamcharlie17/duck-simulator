package com.ducksimulator.interfaces;

/**
 * Core interface for all quackable entities in the pond simulation.
 *
 * <p>Design Pattern: <strong>Interface Pattern</strong> (foundational contract).</p>
 *
 * <p>Every entity that can "quack" — whether a real duck, rubber duck, or even
 * an adapted goose — must implement this interface. This ensures the simulator
 * can treat all participants uniformly (Liskov Substitution Principle).</p>
 */
public interface Quackable {

    /**
     * Produces a quacking sound (or equivalent behaviour) for this entity.
     */
    void quack();
}
