# 🔧 Vehicle Workshop — Java OOP Assessment

> **Package:** `za.co.bhunganecodes.workshop`
> **Assessment weight:** Implementation 60% · UML 25% · Long Question 15%
> **Pass mark:** 60%

---

## Project overview

A Java simulation of a vehicle workshop that manages service jobs and processes client work orders through a queue-based system. The project demonstrates **Encapsulation** and **Inheritance** as core OOP principles.

---

## Project structure

```
vehicle-workshop/
├── pom.xml
├── README.md
├── answers.txt
├── uml.pdf                          ← export your draw.io diagram here
└── src/
    ├── main/java/za/co/bhunganecodes/workshop/
    │   ├── Main.java
    │   ├── model/
    │   │   ├── Part.java            ← Step 1
    │   │   ├── ServiceJob.java      ← Step 2
    │   │   └── WorkOrder.java       ← Step 3
    │   └── service/
    │       ├── Workshop.java        ← Step 4 (abstract)
    │       ├── MobileWorkshop.java  ← Step 5
    │       └── GarageWorkshop.java  ← Step 5
    └── test/java/za/co/bhunganecodes/workshop/
        ├── PartTest.java
        ├── ServiceJobTest.java
        ├── WorkOrderTest.java
        └── WorkshopTest.java
```

---

## Getting started

### Prerequisites

| Tool  | Minimum version |
|-------|----------------|
| Java  | 25             |
| Maven | 3.8+           |

> **Windows tip:** make sure your `JAVA_HOME` environment variable points to your JDK 25 installation directory (e.g. `C:\Program Files\Java\jdk-25`). You can verify with `java -version` in a command prompt.

### Clone and build

```bash
git clone <your-repo-url>
cd vehicle-workshop
mvn clean compile
```

### Run all tests

```bash
mvn clean test
```

### Run a single test class

```bash
mvn clean test -Dtest=PartTest
mvn clean test -Dtest=ServiceJobTest
mvn clean test -Dtest=WorkOrderTest
mvn clean test -Dtest=WorkshopTest
```

### Run the demo entry point

```bash
mvn compile exec:java -Dexec.mainClass="za.co.bhunganecodes.workshop.Main"
```

---

## Implementation guide (TDD workflow)

This project follows **spec-driven development** and **TDD (Test-Driven Development)**. The tests are written first; your job is to make them pass by implementing the `TODO` steps in each class.

Work through the steps **in order** — later classes depend on earlier ones.

---

### Step 1 — `Part`

`src/main/java/.../model/Part.java`

- Declare private fields: `name` (String) and `quantity` (double)
- Implement the constructor, accessors, `updateQuantity(double)`, and `toString()`
- `updateQuantity` must throw `IllegalArgumentException` for negative values
- `toString` format: `"Brake Pads: 4.0 units"`

```bash
mvn clean test -Dtest=PartTest
```

---

### Step 2 — `ServiceJob`

`src/main/java/.../model/ServiceJob.java`

- Declare private fields: `name` (String) and `parts` (List<Part>)
- Constructor must store a **copy** of the incoming list — not the reference
- `parts()` must return a **defensive copy**
- Implement `addPart`, and `toString` (name + each part on its own line)

```bash
mvn clean test -Dtest=ServiceJobTest
```

> **Why defensive copies?** If you store or return the original list reference, any caller can mutate your service job's internal state silently. Defensive copies protect encapsulation.

---

### Step 3 — `WorkOrder`

`src/main/java/.../model/WorkOrder.java`

- The `WorkOrderStatus` enum is already declared for you: `PENDING`, `IN_PROGRESS`, `COMPLETED`
- Declare private fields: `workOrderId`, `clientName`, `serviceJob`, `status`
- Constructor sets `status` to `PENDING`
- Implement all accessors and `updateStatus(WorkOrderStatus)`

```bash
mvn clean test -Dtest=WorkOrderTest
```

---

### Step 4 — `Workshop` (abstract class)

`src/main/java/.../service/Workshop.java`

- Declare private fields: `workshopName`, `serviceJobs` (HashMap), `workOrderQueue` (ArrayList), `orderCounter`
- `placeOrder` uses `++orderCounter` so the first work order gets ID 1
- `processNextOrder` finds the first PENDING order → sets IN_PROGRESS → calls `service()` → sets COMPLETED → returns it
- Return unmodifiable collections from `getAllServiceJobs()` and `workOrderQueue()`
- `service(WorkOrder)` is abstract — subclasses implement it

---

### Step 5 — `MobileWorkshop` & `GarageWorkshop`

`src/main/java/.../service/MobileWorkshop.java`
`src/main/java/.../service/GarageWorkshop.java`

- Both `extend Workshop`
- Constructor calls `super(workshopName)`
- Each implements `service(WorkOrder)` printing a workshop-specific message:
  - Mobile:  `"[workshopName] completing [jobName] for [clientName] using on-site mobile dispatch."`
  - Garage:  `"[workshopName] completing [jobName] for [clientName] using in-garage service bay."`

```bash
mvn clean test -Dtest=WorkshopTest
```

---

## Running all tests (final check)

```bash
mvn clean test
```

Your goal: **all tests green** before submission.

---

## UML class diagram

Produce your diagram using **draw.io only**. Export as `uml.pdf` and place it in the root of this project.

Your diagram must show:
- All **6 classes** with fields, types, and access modifiers (`+` / `-` / `#`)
- All **methods** with return types and parameters
- **Inheritance arrows** (MobileWorkshop → Workshop, GarageWorkshop → Workshop)
- **Association arrows** (ServiceJob → Part, WorkOrder → ServiceJob, Workshop → ServiceJob, Workshop → WorkOrder)

---

## Long question

Answer the long question in `answers.txt`. Do not change the format of that file.

---

## Submission checklist

- [ ] All TODO comments replaced with working implementations
- [ ] `mvn clean compile` passes with no errors
- [ ] `mvn clean test` passes with all tests green
- [ ] `uml.pdf` placed in project root (exported from draw.io)
- [ ] `answers.txt` completed
- [ ] Project pushed to your submission repository
