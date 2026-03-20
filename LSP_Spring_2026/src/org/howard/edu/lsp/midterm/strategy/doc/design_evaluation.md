The original PriceCalculator design is difficult to maintain because it uses multiple if statements to handle different customer types. This makes the code harder to read and update as new discount types are added.

The design also violates the open/closed principle because adding a new discount requires modifying the existing class. This increases the risk of introducing errors and makes the system less flexible.

Using the Strategy Pattern improves the design by separating each discount into its own class. This allows new discount types to be added without modifying existing code. It also makes the system easier to maintain, test, and extend because each class has a single responsibility.