# Cell Society Design Discussion
### Team Number 14
William Convertino,
Tim Jang,
Quentin MacFarlane,
Alexis Cruz-Ayala


### Discussion Questions

 * How does a Cell know what rules to apply for its simulation?

We will make a subclass for each algorithm that will apply the rules to the grid on each cycle
of the program.

 * How does a Cell know about its neighbors?

In our Grid class we can have a couple methods that check the neighboring
grids and return their values.

 * How can a Cell update itself without affecting its neighbors update?

We can have two different arrays, one for the current pattern and one for the updated pattern. When a cell is updated,
we can check the current pattern and set the new value in the updated pattern. Once all the updates are completed,
we can replace the current pattern with the updated one.

 * What behaviors does the Grid itself have?
 
It needs to be able to return the state of each cell, update itself and add new elements to the updated array, and
it needs to be able to get the neighbors of any cells.

 * How can a Grid update all the Cells it contains?

We can either iterate over each cell or we can create a map of active cells and only update their neighbors.

 * How is configuration information used to set up a simulation?

It tells us the type of algorithm we're using and set the value for many of our program-specific variables (ei. probabilities)

 * How is the GUI updated after all the cells have been updated?

The front end can display the updated grid once it's passed at a time interval.

### Alternate Designs

#### Design Idea #1

 * Data Structure #1 and File Format #1

2D integer array to represent the grid and the items in each cell. Each integer value represents different objects 
depending on the project. The entire grid (2D array) is passed from the backend to the frontend. 


#### Design Idea #2

 * Data Structure #1 and File Format #1

We could make an array of cell-block objects, each of which keeps track of its own state and has a mehtod
to update itself based on the state of adjacent cells.


### CRC Card Classes

This class's purpose or value is to represent a customer's order:
![Order Class CRC Card](images/order_crc_card.png "Order Class")


This class's purpose or value is to represent a customer's order:

|Order| |
|---|---|
|boolean isInStock(OrderLine)         |OrderLine|
|double getTotalPrice(OrderLine)      |Customer|
|boolean isValidPayment (Customer)    | |
|void deliverTo (OrderLine, Customer) | |


This class's purpose or value is to represent a customer's order:
```java
public class Order {
     // returns whether or not the given items are available to order
     public boolean isInStock (OrderLine items)
     // sums the price of all the given items
     public double getTotalPrice (OrderLine items)
     // returns whether or not the customer's payment is valid
     public boolean isValidPayment (Customer customer)
     // dispatches the items to be ordered to the customer's selected address
     public void deliverTo (OrderLine items, Customer customer)
 }
 ```
 

### Use Cases

 * Apply the rules to a cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all of its neighbors)
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```

 * Move to the next generation: update all cells in a simulation from their current state to their next state
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```

 * Switch simulations: load a new simulation from a data file, replacing the current running simulation with the newly loaded one
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```
