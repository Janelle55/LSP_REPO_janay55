Heuristic 1:
Name:
H2.1: All data should be hidden within its class

Explanation:
This heuristic improves maintainability by ensuring that a class controls access to its own data. In lecture, this was explained as encapsulation, where data is kept private and only accessed through public methods. This prevents other classes from directly modifying internal data and reduces the impact of changes to the class.

Heuristic 2:
Name:
H2.8: A class should capture one and only one key abstraction

Explanation:
This heuristic improves readability and maintainability by ensuring that each class has a clear and focused responsibility. In lecture, this was explained as avoiding classes that try to do too many things. A class that focuses on one abstraction is easier to understand, test, and modify.

Heuristic 3:
Name:
H3.2: Do not create god classes/objects in your system

Explanation:
This heuristic improves maintainability by preventing one class from controlling too much of the system’s logic. In lecture, this was illustrated with the “god class” problem, where a single class becomes overly complex and difficult to maintain. Distributing responsibilities across multiple classes makes the system more flexible and easier to modify.