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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point for the Duck Pond Simulator.
 *
 * <p>This class orchestrates the full demonstration of all implemented
 * design patterns working in concert:</p>
 * <ol>
 *   <li><b>Factory</b>   — {@link CountingDuckFactory} creates ducks pre-wrapped in {@link QuackCounter}.</li>
 *   <li><b>Adapter</b>   — {@link GooseDuckAdapter} lets a {@link WildGoose} act as a {@link Quackable}.</li>
 *   <li><b>Decorator</b> — {@link QuackCounter} transparently counts every quack.</li>
 *   <li><b>Observer</b>  — {@link ObservableQuackable} + {@link QuackTracker} track quack events.</li>
 *   <li><b>Composite</b> — {@link Flock} commands the entire pond in a single call.</li>
 *   <li><b>Iterator</b>  — The pond flock is iterated to quack members individually.</li>
 * </ol>
 *
 * <p><b>How to run:</b> {@code mvn exec:java} or run this class directly from your IDE.</p>
 */
public class DuckSimulator {

    private static final Logger log = LoggerFactory.getLogger(DuckSimulator.class);

    // -----------------------------------------------------------------------
    // Entry Point
    // -----------------------------------------------------------------------

    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        simulator.simulate();
    }

    // -----------------------------------------------------------------------
    // Simulation
    // -----------------------------------------------------------------------

    private void simulate() {
        log.info("╔══════════════════════════════════════════╗");
        log.info("║        🦆  Duck Pond Simulator  🦆        ║");
        log.info("╚══════════════════════════════════════════╝");

        // Reset any counters from previous runs (useful in test scenarios)
        QuackCounter.resetCount();

        // ----- 1. Factory ─────────────────────────────────────────────────
        // CountingDuckFactory wraps each duck in a QuackCounter automatically.
        DuckFactory factory = new CountingDuckFactory();

        log.info("\n[Step 1] Creating ducks via CountingDuckFactory…");
        Quackable mallard  = factory.createMallardDuck();
        Quackable redhead  = factory.createRedheadDuck();
        Quackable rubber   = factory.createRubberDuck();

        // ----- 2. Adapter ─────────────────────────────────────────────────
        log.info("\n[Step 2] Adapting a Wild Goose to Quackable…");
        WildGoose goose        = new WildGoose();
        Quackable gooseAdapter = new GooseDuckAdapter(goose);

        // ----- 3. Observer ────────────────────────────────────────────────
        // Wrap each quackable in ObservableQuackable, then attach the tracker.
        log.info("\n[Step 3] Wrapping ducks with ObservableQuackable…");
        QuackTracker tracker = new QuackTracker();

        ObservableQuackable obsMallard = new ObservableQuackable(mallard);
        ObservableQuackable obsRedhead = new ObservableQuackable(redhead);
        ObservableQuackable obsRubber  = new ObservableQuackable(rubber);
        ObservableQuackable obsGoose   = new ObservableQuackable(gooseAdapter);

        obsMallard.registerObserver(tracker);
        obsRedhead.registerObserver(tracker);
        obsRubber.registerObserver(tracker);
        obsGoose.registerObserver(tracker);

        // ----- 4. Composite ───────────────────────────────────────────────
        // Build a primary flock for the main duck types, then a full pond flock.
        log.info("\n[Step 4] Assembling the Flock (Composite)…");
        Flock duckFlock = new Flock();
        duckFlock.add(obsMallard);
        duckFlock.add(obsRedhead);
        duckFlock.add(obsRubber);

        Flock pondFlock = new Flock();
        pondFlock.add(duckFlock);   // nested flock — Composite power!
        pondFlock.add(obsGoose);

        // ----- 5. Full Flock Quack ────────────────────────────────────────
        log.info("\n[Step 5] Full pond quack ↓");
        pondFlock.quack();

        // ----- 6. Statistics ──────────────────────────────────────────────
        log.info("\n[Step 6] Simulation Statistics");
        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        log.info("  Decorator count  (QuackCounter) : {} quacks", QuackCounter.getQuackCount());
        log.info("  Observer count   (QuackTracker) : {} quacks", tracker.getTrackedQuackCount());

        // ----- 7. Iterator ────────────────────────────────────────────────
        log.info("\n[Step 7] Iterating through the duckFlock individually…");
        int index = 1;
        for (Quackable duck : duckFlock) {
            log.info("  Individual quack #{} → {}", index++, duck);
            duck.quack();
        }

        // ----- 8. Final Totals ────────────────────────────────────────────
        log.info("\n[Step 8] Final Totals after individual iteration");
        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        log.info("  Total quacks recorded by Decorator : {}", QuackCounter.getQuackCount());
        log.info("  Total quacks tracked by Observer   : {}", tracker.getTrackedQuackCount());
        log.info("\n╔══════════════════════════════════════════╗");
        log.info("║       Simulation Complete — 🎉           ║");
        log.info("╚══════════════════════════════════════════╝");
    }
}
