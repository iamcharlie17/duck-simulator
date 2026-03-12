package com.ducksimulator;

import com.ducksimulator.models.WildGoose;
import com.ducksimulator.patterns.adapter.GooseDuckAdapter;
import com.ducksimulator.patterns.composite.Flock;
import com.ducksimulator.interfaces.Quackable;
import com.ducksimulator.patterns.factory.CountingDuckFactory;
import com.ducksimulator.patterns.factory.DuckFactory;
import com.ducksimulator.patterns.decorator.QuackCounter;
import com.ducksimulator.patterns.observer.ObservableQuackable;
import com.ducksimulator.patterns.observer.QuackTracker;

public class DuckSimulator {

    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        simulator.simulate();
    }

    private void simulate() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║        🦆  Duck Pond Simulator  🦆        ║");
        System.out.println("╚══════════════════════════════════════════╝");

        QuackCounter.resetCount();

        DuckFactory factory = new CountingDuckFactory();

        System.out.println("\n[Step 1] Creating ducks via CountingDuckFactory…");
        Quackable mallard  = factory.createMallardDuck();
        Quackable redhead  = factory.createRedheadDuck();
        Quackable rubber   = factory.createRubberDuck();

        System.out.println("\n[Step 2] Adapting a Wild Goose to Quackable…");
        WildGoose goose        = new WildGoose();
        Quackable gooseAdapter = new GooseDuckAdapter(goose);

        System.out.println("\n[Step 3] Wrapping ducks with ObservableQuackable…");
        QuackTracker tracker = new QuackTracker();

        ObservableQuackable obsMallard = new ObservableQuackable(mallard);
        ObservableQuackable obsRedhead = new ObservableQuackable(redhead);
        ObservableQuackable obsRubber  = new ObservableQuackable(rubber);
        ObservableQuackable obsGoose   = new ObservableQuackable(gooseAdapter);

        obsMallard.registerObserver(tracker);
        obsRedhead.registerObserver(tracker);
        obsRubber.registerObserver(tracker);
        obsGoose.registerObserver(tracker);

        System.out.println("\n[Step 4] Assembling the Flock (Composite)…");
        Flock duckFlock = new Flock();
        duckFlock.add(obsMallard);
        duckFlock.add(obsRedhead);
        duckFlock.add(obsRubber);

        Flock pondFlock = new Flock();
        pondFlock.add(duckFlock);
        pondFlock.add(obsGoose);

        System.out.println("\n[Step 5] Full pond quack ↓");
        pondFlock.quack();

        System.out.println("\n[Step 6] Simulation Statistics");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  Decorator count  (QuackCounter) : " + QuackCounter.getQuackCount() + " quacks");
        System.out.println("  Observer count   (QuackTracker) : " + tracker.getTrackedQuackCount() + " quacks");

        System.out.println("\n[Step 7] Iterating through the duckFlock individually…");
        int index = 1;
        for (Quackable duck : duckFlock) {
            System.out.println("  Individual quack #" + index++ + " → " + duck);
            duck.quack();
        }

        System.out.println("\n[Step 8] Final Totals after individual iteration");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  Total quacks recorded by Decorator : " + QuackCounter.getQuackCount());
        System.out.println("  Total quacks tracked by Observer   : " + tracker.getTrackedQuackCount());
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║       Simulation Complete — 🎉           ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }
}