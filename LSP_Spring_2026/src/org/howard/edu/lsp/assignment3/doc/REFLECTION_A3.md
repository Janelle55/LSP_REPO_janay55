# Reflection – Assignment 3

## What is different between Assignment 2 and Assignment 3?
In Assignment 2, my ETL pipeline was mostly in one class. In Assignment 3, I broke the work into multiple classes so each class has one job (reading CSV, transforming data, writing CSV, and running the pipeline).

## How is Assignment 3 more object-oriented?
Assignment 3 is more object-oriented because I separated responsibilities into objects/classes instead of putting everything inside one big method. This makes the code easier to read, test, and update.

## Which OO ideas did I use?
- **Class/Object:** Each part of the pipeline is its own class (Reader, Transformer, Writer, Pipeline).
- **Encapsulation:** CSV parsing details are hidden inside CsvProductReader and CsvProductWriter.
- **Polymorphism/Inheritance:** I didn’t use inheritance heavily, but the design allows swapping in different readers/transformers/writers later without rewriting the pipeline.

## How I tested A3 matches A2 behavior
- I ran normal input to confirm uppercase names, Electronics discount, rounding (HALF_UP), Premium Electronics rule, and PriceRange.
- I tested bad rows (like invalid prices) to confirm the program skips them without crashing.
- I tested empty input to confirm it produces a header-only transformed_products.csv.
- I tested missing input file to confirm it prints a clear error message and exits cleanly without a stack trace.