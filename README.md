# Discrete Structures — Datalog Analyzer & Relational Interpreter (Java)

A multi-stage, portfolio-ready Java project that incrementally builds a **Datalog interpreter** from first principles. Across five stages you implement: (1) a **lexer** to tokenize input, (2) a **recursive-descent parser** that builds a typed AST (Schemes, Facts, Rules, Queries), (3) a **query evaluator** that computes variable bindings/solutions, (4) a lightweight **relational algebra engine** (Relation, Tuple, Schema with select/project/rename/join/union), and (5) **rule evaluation** to a fixpoint (derive new facts until no more can be produced). The repository includes drivers, extensive Java sources, Javadoc for mid-stages, and assignment PDFs mapping specs to each stage.

## ✨ Highlights
- **Compiler/Tooling Foundations:** Deterministic tokenization of identifiers, string literals, punctuation, and Datalog keywords (SCHEMES, FACTS, RULES, QUERIES) with line tracking and line/block comment handling.
- **Parsing & AST Construction:** Clean recursive-descent parser that validates the grammar and constructs strongly typed nodes: `DatalogProgram`, `Scheme/List`, `Fact/List`, `Rule/List`, `Query/List`, `Predicate`, `Parameter`, `Domain`.
- **Query Evaluation:** Differentiates constants vs. variables, produces variable bindings and sets of solutions; helpers like `QueryVariableInformation` and `SetOfSolutions`.
- **Relational Algebra (In-Memory RDBMS):** Core types `Database`, `Relation`, `Schema`, `Tuple` plus RA operations (select, project, rename, join, union) with readable output.
- **Rules & Fixpoint:** Iteratively applies Datalog rules until no new tuples arise; clear separation of base Facts and derived Rules.
- **Readable, Modular Java:** Clear stage boundaries (project1→project5), small focused drivers (`Project1.java`, `Project2.java`, …), a top-level `Driver.java`, and Javadoc HTML for project3.

## 🛠 Tech Stack
- **Language:** Java (SE) — compatible with modern JDKs.
- **Build/Run:** `javac` / `java` CLI or any Java IDE (IntelliJ IDEA recommended).
- **Docs/Testing:** Javadoc HTML for project3; per-stage CLI drivers for behavioral verification.
- **Dependencies:** Standard library only (no external runtime deps).

## 📂 Repository Structure
    src/src/
    ├── Driver.java
    ├── project1/        # Lexer
    │   ├── Enums.java
    │   ├── LexicalAnalyzer.java
    │   ├── Token.java
    │   └── Project1.java
    ├── project2/        # Parser & AST
    │   ├── DatalogProgram.java
    │   ├── Domain.java
    │   ├── Fact*.java, Scheme*.java, Rule*.java, Query*.java, Predicate*.java, Parameter.java
    │   └── Project2.java
    ├── project3/        # Query Evaluation (+ Javadoc)
    │   ├── lex/         # stage-specific lexer types
    │   ├── three/       # DatalogProgram, NamedList, QueryVariableInformation, SetOfSolutions, etc.
    │   ├── documentation/   # generated Javadoc / API HTML
    │   └── Project3.java
    ├── project4/        # Relational Algebra Engine
    │   ├── Database.java
    │   ├── Relation.java, Schema.java, Tuple.java, Tuples.java
    │   └── Project4.java
    └── project5/        # Rules → Fixpoint
        ├── DatabaseRules.java
        └── Project5.java

    projects descriptions/
    ├── Proj1.lex.pdf
    ├── Proj2.parser.pdf
    ├── Proj3.recurse.pdf
    ├── Proj4.rdbms.pdf
    └── Proj5.interp.pdf

    (The PDFs map directly to each stage’s spec: lex → parser → query recursion → RDBMS → interpreter with rules.)

## ▶️ Build & Run (CLI)
    # From repo root, compile all sources
    mkdir -p out
    javac -d out $(find src -name "*.java")

    # Run a specific stage (adjust package/class if needed)
    # Project 1 — Lexer (prints tokens & line counts)
    java -cp out project1.Project1 input/datalog.txt > output1.txt

    # Project 2 — Parser (prints parsed program & domain)
    java -cp out project2.Project2 input/datalog.txt > output2.txt

    # Project 3 — Query evaluation (solutions for queries)
    # (Some setups use package project3.three; check the top of Project3.java)
    java -cp out project3.three.Project3 input/datalog.txt > output3.txt

    # Project 4 — Relational algebra engine (evaluate via RA ops)
    java -cp out project4.Project4 input/datalog.txt > output4.txt

    # Project 5 — Rules (apply to fixpoint, then answer queries)
    java -cp out project5.Project5 input/datalog.txt > output5.txt

    # Tip: find all runnable mains quickly
    grep -R --line-number "public static void main" src | sed 's/:.*//g' | sort -u

    # Input format reminder:
    # Use a single file with Datalog Schemes, Facts, Rules, and Queries.
    # See the corresponding PDF spec in "projects descriptions/" for exact I/O.

## 📑 Stage-by-Stage Overview
- **Project 1 (Lexer):** Converts input to tokens (`COMMA`, `PERIOD`, `ID`, `STRING`, keywords), tracks lines, handles comments.
- **Project 2 (Parser):** Builds a typed AST (`DatalogProgram`, `SchemeList`, `FactList`, `RuleList`, `QueryList`, `Domain`), validates grammar.
- **Project 3 (Queries):** Executes queries over facts/schemes; returns sets of variable bindings; includes query-variable bookkeeping types.
- **Project 4 (Relational Algebra):** Implements select/project/rename/join/union on in-memory relations; pretty-prints results.
- **Project 5 (Rules/Interpreter):** Applies rules until fixpoint; evaluates queries against the saturated database.

## 🌟 Why It’s Portfolio-Worthy
- **First-principles engineering:** Scanner, parser, evaluator, and RA engine are written without frameworks.
- **Theory → practice:** Formal grammar and relational algebra concepts become executable Java.
- **Clarity & modularity:** Each stage is self-contained, with clear APIs and drivers; Javadoc artifacts help navigation.
- **Database fundamentals:** Demonstrates the algebraic backbone used by real query engines.
- **Interview-friendly:** Easy to explain design choices, invariants, complexity, and trade-offs with concrete code.

## 📜 License & Author
- **License:** MIT (see LICENSE)
- **Author:** Lehi Alcantara — https://www.lehi.dev — lehi@lehi.dev