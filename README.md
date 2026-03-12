# 🦆 Duck Pond Simulator

> A comprehensive demonstration of **six Gang-of-Four design patterns** implemented in Java, simulating ducks and geese interacting in a pond. Built with clean architecture, SLF4J logging, and full Maven support.

[![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=java)](https://adoptium.net/)
[![Maven](https://img.shields.io/badge/Maven-3.8%2B-blue?logo=apache-maven)](https://maven.apache.org/)
[![Design Patterns](https://img.shields.io/badge/Design%20Patterns-6%20GoF%20Patterns-green)](https://en.wikipedia.org/wiki/Design_Patterns)
[![SLF4J](https://img.shields.io/badge/Logging-SLF4J%2FLogback-lightgrey)](https://www.slf4j.org/)

---

## 📋 Table of Contents

1. [Project Overview](#-project-overview)
2. [Folder Structure](#-folder-structure)
3. [Design Patterns Explained](#-design-patterns-explained)
   - [Interface Pattern](#1-interface-pattern)
   - [Adapter Pattern](#2-adapter-pattern)
   - [Decorator Pattern](#3-decorator-pattern)
   - [Factory Pattern](#4-factory-pattern)
   - [Composite Pattern](#5-composite-pattern)
   - [Observer Pattern](#6-observer-pattern)
4. [Design Pattern Mapping Table](#-design-pattern-mapping-table)
5. [Execution Flow](#-execution-flow)
6. [How Patterns Work Together](#-how-patterns-work-together)
7. [Sample Output](#-sample-output)
8. [How to Run](#-how-to-run)

---

## 📖 Project Overview

The **Duck Pond Simulator** is an academic and portfolio-grade Java project that showcases how multiple object-oriented design patterns from the *Gang of Four* book can coexist and reinforce each other in a single application.

The simulation models a virtual duck pond where:
- 🦆 **Ducks** (Mallard, Redhead, Rubber) quack in their own way
- 🪿 **Geese** honk — but through an **Adapter**, they can participate as ducks
- 🔢 Every quack is **counted** automatically via a **Decorator**
- 📡 A **QuackTracker** observes quack events in real-time
- 🐓 A **Flock** (Composite) commands the entire pond at once
- 🏭 A **Factory** ensures consistent creation of observable, counted ducks

---

## 📁 Folder Structure

```
DP_Project/
├── pom.xml                                         # Maven build configuration
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── ducksimulator/
        │           ├── DuckSimulator.java           # Main simulation entry point
        │           │
        │           ├── interfaces/
        │           │   ├── Quackable.java           # Core contract for all pond entities
        │           │   └── QuackObserver.java       # Observer listener contract
        │           │
        │           ├── models/
        │           │   ├── MallardDuck.java         # Concrete duck: Mallard
        │           │   ├── RedheadDuck.java         # Concrete duck: Redhead
        │           │   ├── RubberDuck.java          # Concrete duck: Rubber (squeaks)
        │           │   └── WildGoose.java           # Goose (honks, not Quackable)
        │           │
        │           └── patterns/
        │               ├── adapter/
        │               │   └── GooseDuckAdapter.java    # Adapts WildGoose → Quackable
        │               ├── decorator/
        │               │   └── QuackCounter.java        # Counts quacks globally
        │               ├── factory/
        │               │   ├── DuckFactory.java         # Base factory
        │               │   └── CountingDuckFactory.java # Factory + Decorator combo
        │               ├── composite/
        │               │   └── Flock.java               # Composite flock of Quackables
        │               └── observer/
        │                   ├── QuackEvent.java          # Event object
        │                   ├── ObservableQuackable.java # Subject (observable wrapper)
        │                   └── QuackTracker.java        # Concrete observer / tracker
        │
        └── resources/
            └── logback.xml                          # SLF4J/Logback logging config
```

---

## 🎨 Design Patterns Explained

---

### 1. Interface Pattern

| | |
|---|---|
| **File** | [`Quackable.java`](src/main/java/com/ducksimulator/interfaces/Quackable.java) |
| **Type** | Foundational Contract |

#### Intent
Define a shared contract that all pond entities must fulfil, enabling the system to treat ducks, geese adapters, decorators, and composites **uniformly** through a single type.

#### Where Used in This Project
`Quackable` is the backbone of the entire system. Every concrete duck, the `GooseDuckAdapter`, `QuackCounter`, `ObservableQuackable`, and `Flock` all implement it — making them interchangeable wherever a `Quackable` is expected.

#### Class Diagram (Textual)
```
«interface»
Quackable
  + quack() : void
       ▲
       │ implements
  ┌────┴────────────────────────────────────────┐
  │            │            │           │        │
MallardDuck RedheadDuck RubberDuck GooseDuckAdapter Flock
```

#### Why It Was Chosen
Satisfies the **Dependency Inversion Principle** — high-level modules (the simulator) depend on the `Quackable` abstraction, not on concrete duck classes.

#### Real-World Analogy
A universal remote control interface — different TV brands all have a "power on" button, even though their internals differ completely.

---

### 2. Adapter Pattern

| | |
|---|---|
| **File** | [`GooseDuckAdapter.java`](src/main/java/com/ducksimulator/patterns/adapter/GooseDuckAdapter.java) |
| **Adaptee** | `WildGoose` (has `honk()`) |
| **Target** | `Quackable` (needs `quack()`) |

#### Intent
Convert the interface of a class into another interface that clients expect, allowing incompatible classes to work together **without modifying either**.

#### Where Used in This Project
`WildGoose` cannot directly participate in the simulation because it has a `honk()` method rather than `quack()`. `GooseDuckAdapter` wraps a `WildGoose` instance, implements `Quackable`, and translates `quack()` calls into `honk()`.

#### Class Diagram (Textual)
```
«interface»         GooseDuckAdapter           WildGoose
Quackable   ◄───── (implements Quackable)  ───► honk()
quack()             delegates to ──────────────►
```

#### Why It Was Chosen
Follows the **Open/Closed Principle** — neither `WildGoose` nor the simulator were modified to support the integration. The adapter class absorbs all translation logic in isolation.

#### Real-World Analogy
A power-plug travel adapter — your UK kettle works in a US 110V outlet without modifying either the kettle or the wall socket.

---

### 3. Decorator Pattern

| | |
|---|---|
| **File** | [`QuackCounter.java`](src/main/java/com/ducksimulator/patterns/decorator/QuackCounter.java) |
| **Component** | `Quackable` |
| **Concrete Decorator** | `QuackCounter` |

#### Intent
Attach additional responsibilities to an object **dynamically** without subclassing, by wrapping it in a decorator that implements the same interface.

#### Where Used in This Project
`QuackCounter` wraps any `Quackable` and, in addition to delegating the `quack()` call, increments a **static `AtomicInteger` counter** shared across all instances. This gives us a real-time global quack tally for the entire pond.

#### Class Diagram (Textual)
```
«interface»
Quackable
    ▲
    │ implements + wraps
QuackCounter          MallardDuck (wrapped inside)
  - decoratee: Quackable
  - static quackCount: AtomicInteger
  + quack() → delegate + count++
```

#### Why It Was Chosen
Preferred over subclassing because counting is a **cross-cutting concern** that should be applicable to *any* `Quackable` — including future ones — without modifying them.

#### Real-World Analogy
A coffee cup sleeve — it keeps the original cup unchanged while adding insulation (extra behaviour) on top.

---

### 4. Factory Pattern

| | |
|---|---|
| **Files** | [`DuckFactory.java`](src/main/java/com/ducksimulator/patterns/factory/DuckFactory.java), [`CountingDuckFactory.java`](src/main/java/com/ducksimulator/patterns/factory/CountingDuckFactory.java) |
| **Type** | Factory Method (with inheritance extension) |

#### Intent
Define an interface for creating objects, but let subclasses decide which class to instantiate. Centralise construction logic to promote consistency and extensibility.

#### Where Used in This Project
`DuckFactory` is the base factory with `createMallardDuck()`, `createRedheadDuck()`, and `createRubberDuck()` methods.  
`CountingDuckFactory` **extends** `DuckFactory` and overrides every method to automatically wrap the created duck in a `QuackCounter`.

The simulator uses only `DuckFactory` references — it does not care whether counting is active or not.

#### Class Diagram (Textual)
```
DuckFactory
  + createMallardDuck()  → MallardDuck
  + createRedheadDuck()  → RedheadDuck
  + createRubberDuck()   → RubberDuck
         ▲
CountingDuckFactory
  + createMallardDuck()  → QuackCounter(MallardDuck)
  + createRedheadDuck()  → QuackCounter(RedheadDuck)
  + createRubberDuck()   → QuackCounter(RubberDuck)
```

#### Why It Was Chosen
Satisfies the **Open/Closed Principle** — the base factory is closed for modification; `CountingDuckFactory` extends it without touching any existing code. Swapping factories is a one-line change in `DuckSimulator`.

#### Real-World Analogy
A pizza franchise — each restaurant follows the same menu (factory interface), but individual outlets may add regional toppings (extended factory behaviour).

---

### 5. Composite Pattern

| | |
|---|---|
| **File** | [`Flock.java`](src/main/java/com/ducksimulator/patterns/composite/Flock.java) |
| **Component** | `Quackable` |
| **Composite** | `Flock` |

#### Intent
Compose objects into **tree structures** to represent part-whole hierarchies. Let clients treat individual objects and compositions of objects **uniformly**.

#### Where Used in This Project
`Flock` implements `Quackable`, so the simulator can call `pondFlock.quack()` — a single call that recursively quacks every duck and goose in the pond, including nested sub-flocks.

`Flock` also implements `Iterable<Quackable>`, enabling individual iteration over members when finer control is needed.

#### Class Diagram (Textual)
```
«interface»
Quackable + Iterable<Quackable>
    ▲
    └── Flock
          - members: List<Quackable>
          + add(duck)
          + quack()     → members.forEach(quack)
          + iterator()  → unmodifiable iterator

          pondFlock
          ├── duckFlock (nested Flock)
          │   ├── Observable[QuackCounter[MallardDuck]]
          │   ├── Observable[QuackCounter[RedheadDuck]]
          │   └── Observable[QuackCounter[RubberDuck]]
          └── Observable[GooseDuckAdapter[WildGoose]]
```

#### Why It Was Chosen
Avoids explicit loops in the simulator. The pond can be any depth of nested flocks and the client code doesn't change — true to the **Single Responsibility Principle**.

#### Real-World Analogy
A military command structure — a single "fire!" order flows down through divisions → battalions → squads → individual soldiers.

---

### 6. Observer Pattern

| | |
|---|---|
| **Files** | [`QuackObserver.java`](src/main/java/com/ducksimulator/interfaces/QuackObserver.java), [`QuackEvent.java`](src/main/java/com/ducksimulator/patterns/observer/QuackEvent.java), [`ObservableQuackable.java`](src/main/java/com/ducksimulator/patterns/observer/ObservableQuackable.java), [`QuackTracker.java`](src/main/java/com/ducksimulator/patterns/observer/QuackTracker.java) |

#### Intent
Define a one-to-many dependency between objects so that when one object changes state, all its dependents are **notified and updated automatically**.

#### Where Used in This Project
- **Subject:** `ObservableQuackable` — wraps a `Quackable`, maintains a list of `QuackObserver`s, and calls `onQuack(event)` after each quack.
- **Observer:** `QuackTracker` — implements `QuackObserver`, stores each `QuackEvent`, and provides a total count.
- **Event Object:** `QuackEvent` — carries the source `Quackable` and a timestamp, decoupling observers from the subject's internals.

#### Class Diagram (Textual)
```
«interface»           «interface»
QuackObserver         Quackable
  onQuack(event)          ▲
       ▲                  │
QuackTracker     ObservableQuackable
  - eventLog       - decoratee: Quackable
  + onQuack()      - observers: List<QuackObserver>
  + getCount()     + registerObserver()
                   + quack() → delegate + notifyObservers()
                       │
                   QuackEvent
                     - source: Quackable
                     - timestamp: Instant
```

#### Why It Was Chosen
Cleanly separates the *what happened* (quack) from *who cares* (tracker). New observers (e.g., a sound meter or database logger) can be added without touching a single existing class.

#### Real-World Analogy
A news agency and its subscribers — the agency publishes one article; all subscribers are notified without the agency knowing who they are.

---

## 📊 Design Pattern Mapping Table

| Pattern | Class / File | Responsibility | Interaction |
|---------|-------------|----------------|-------------|
| **Interface** | `Quackable` | Defines the quack contract for all pond entities | Root type used by every pattern below |
| **Adapter** | `GooseDuckAdapter` | Translates `WildGoose.honk()` → `Quackable.quack()` | Produces a `Quackable` wrapping a `WildGoose` |
| **Decorator** | `QuackCounter` | Intercepts quack calls to maintain a global count | Wraps any `Quackable`; used inside `CountingDuckFactory` |
| **Factory** | `DuckFactory` / `CountingDuckFactory` | Centralises duck creation; optionally adds counting | `CountingDuckFactory` composes Factory + Decorator |
| **Composite** | `Flock` | Groups `Quackable`s and commands them uniformly | Contains `ObservableQuackable` and nested `Flock`s |
| **Iterator** | `Flock` (implements `Iterable`) | Provides sequential traversal over flock members | Used in the individual-quack loop in `DuckSimulator` |
| **Observer** | `ObservableQuackable` + `QuackTracker` | Notifies registered observers after every quack | `ObservableQuackable` (subject) → `QuackTracker` (observer) |
| **Event Object** | `QuackEvent` | Encapsulates quack event data (source + timestamp) | Passed from `ObservableQuackable` to `QuackObserver` |

---

## 🔄 Execution Flow

Step-by-step walkthrough of `DuckSimulator.simulate()`:

```
Step 1 — Factory
   CountingDuckFactory.createMallardDuck()
   → new QuackCounter( new MallardDuck() )       ← Decorator applied by Factory
   (same for RedheadDuck and RubberDuck)

Step 2 — Adapter
   new GooseDuckAdapter( new WildGoose() )
   → WildGoose is now a valid Quackable

Step 3 — Observer setup
   new ObservableQuackable( mallardQuackCounter )
   obsMallard.registerObserver( tracker )
   (same for redhead, rubber, goose)

Step 4 — Composite assembly
   duckFlock.add(obsMallard, obsRedhead, obsRubber)
   pondFlock.add(duckFlock)           ← nested Flock
   pondFlock.add(obsGoose)

Step 5 — Full pond quack
   pondFlock.quack()
   → duckFlock.quack()
     → obsMallard.quack()
       → QuackCounter.quack()          (count++)
         → MallardDuck.quack()         (logs "Mallard Duck: Quack!")
       → tracker.onQuack(event)        (records event)
     → (same for odRedhead, obsRubber)
   → obsGoose.quack()
     → GooseDuckAdapter.quack()
       → WildGoose.honk()              (logs "Wild Goose: Honk! Honk!")
     → tracker.onQuack(event)

Step 6 — Statistics printed (QuackCounter total, QuackTracker total)

Step 7 — Iterator
   for (Quackable duck : duckFlock) { duck.quack() }
   → Each duck quacks individually, counts increase again

Step 8 — Final totals printed
```

---

## 🧩 How Patterns Work Together

```
                     ┌──────────────────────────────────────────────────────┐
                     │                  DuckSimulator                        │
                     │         uses DuckFactory (Factory Pattern)            │
                     └──────────────────┬───────────────────────────────────┘
                                        │ creates
                         ┌──────────────▼──────────────┐
                         │  CountingDuckFactory         │
                         │  (Factory + Decorator)       │
                         └──────────────┬──────────────┘
                                        │ produces
          ┌─────────────────────────────▼──────────────────────────┐
          │             QuackCounter (Decorator)                    │
          │             wraps each duck from models/                │
          └─────────────────────────────┬──────────────────────────┘
                                        │
          ┌─────────────────────────────▼──────────────────────────┐
          │          ObservableQuackable (Decorator + Subject)      │
          │          wraps QuackCounter; notifies QuackTracker      │
          └─────────────────────────────┬──────────────────────────┘
                                        │ added to
          ┌─────────────────────────────▼──────────────────────────┐
          │               Flock (Composite + Iterable)             │
          │         pondFlock → duckFlock + obsGoose               │
          └─────────────────────────────────────────────────────────┘

  Also:
  WildGoose ──[GooseDuckAdapter]──► Quackable (Adapter Pattern)
  QuackTracker ◄── observes ─── ObservableQuackable (Observer Pattern)
```

---

## 🖥️ Sample Output

```
╔══════════════════════════════════════════╗
║        🦆  Duck Pond Simulator  🦆        ║
╚══════════════════════════════════════════╝

[Step 1] Creating ducks via CountingDuckFactory…

[Step 2] Adapting a Wild Goose to Quackable…

[Step 3] Wrapping ducks with ObservableQuackable…

[Step 4] Assembling the Flock (Composite)…

[Step 5] Full pond quack ↓
-- Flock quacks (2 members) --
-- Flock quacks (3 members) --
Mallard Duck: Quack!
Redhead Duck: Quack!
Rubber Duck: Squeak!
Wild Goose: Honk! Honk!

[Step 6] Simulation Statistics
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  Decorator count  (QuackCounter) : 3 quacks
  Observer count   (QuackTracker) : 4 quacks

[Step 7] Iterating through the duckFlock individually…
  Individual quack #1 → Observable[QuackCounter[MallardDuck]]
Mallard Duck: Quack!
  Individual quack #2 → Observable[QuackCounter[RedheadDuck]]
Redhead Duck: Quack!
  Individual quack #3 → Observable[QuackCounter[RubberDuck]]
Rubber Duck: Squeak!

[Step 8] Final Totals after individual iteration
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  Total quacks recorded by Decorator : 6
  Total quacks tracked by Observer   : 7

╔══════════════════════════════════════════╗
║       Simulation Complete — 🎉           ║
╚══════════════════════════════════════════╝
```

> **Note:** The Decorator counts only ducks wrapped in `QuackCounter` (3 duck types × 2 rounds = 6).  
> The Observer counts all `ObservableQuackable` instances including the goose adapter (4 × first round + 3 ducks × second round = 7).

---

## 🚀 How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.8 or higher

### Build & Run

```bash
# Clone / enter the project directory
cd DP_Project

# Download dependencies and compile
mvn clean compile

# Run the simulation
mvn exec:java

# (Optional) Run with debug logging to see internal events
mvn exec:java -Dlogback.configurationFile=src/main/resources/logback.xml
```

### Build an Executable JAR

```bash
mvn clean package

java -cp target/duck-simulator-1.0.0.jar com.ducksimulator.DuckSimulator
```

---

## 🧱 Adding a New Duck Type

1. Create a new class in `models/` implementing `Quackable`:
   ```java
   public class CanvasDuck implements Quackable {
       private static final Logger log = LoggerFactory.getLogger(CanvasDuck.class);
       @Override public void quack() { System.out.println("Canvas Duck: Quaaack!"); }
   }
   ```
2. Add a factory method to `DuckFactory` (and override in `CountingDuckFactory`):
   ```java
   public Quackable createCanvasDuck() { return new CanvasDuck(); }
   ```
3. In `DuckSimulator`, obtain from factory and add to the flock — **zero other changes required.**

---

## 📄 License

This project is released for academic and portfolio use. Feel free to fork, adapt, and extend it.
