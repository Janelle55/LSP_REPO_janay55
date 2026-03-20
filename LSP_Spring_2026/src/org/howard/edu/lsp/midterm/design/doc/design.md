Class: Order
Responsibilities:
- Store order information such as customer name, email, item, and price
- Provide access to order data
Collaborators:
- DiscountService
- ReceiptPrinter
- OrderRepository
- NotificationService
- LoggerService

Class: DiscountService
Responsibilities:
- Calculate tax and apply discounts
- Return the final total price
Collaborators:
- Order

Class: ReceiptPrinter
Responsibilities:
- Print the receipt for an order
Collaborators:
- Order

Class: OrderRepository
Responsibilities:
- Save order details to storage
Collaborators:
- Order

Class: NotificationService
Responsibilities:
- Send confirmation messages to customers
Collaborators:
- Order

Class: LoggerService
Responsibilities:
- Log order activity
Collaborators:
- Order