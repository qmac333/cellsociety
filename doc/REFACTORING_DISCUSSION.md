## Lab Discussion
### Team 14
### Names 
Tim Jang
Quentin MacFarlane
Will Convertino
Alexis Cruz-Ayala


### Issues in Current Code

### Grid
* The class has a method to return its int[][], which is giving what should be private access. 
We currently use it to display to the screen, so we will need to figure out how to replace this.

* We have neighbor methods for cardinal and 8 adjacent neighbors, but this makes it difficult to abstract.
We should likely create a method to determine which neighbors it returns.

#### Animal
* There is a lot of casting to subclass variables in the WaTorWorld simulation when we want to use Shark methods.

### Display
* The display is not very dynamic in how it creates the grid. This makes it difficult to implement many of the new
features.

### Refactoring Plan

* What are the code's biggest issues?

The way we update the grid & display is heavily dependent on the accessor methods of the grid class.

* Which issues are easy to fix and which are hard?

Fixing the way we update the Display grid is to satisfy encapsulation is easy to fix. 
Changing 

* What is your plan to implement the changes without losing control of the process?

We plan to refactor each change in a corresponding branch in git. We will test to make sure everything is still working as intended
after each change.

### Refactoring Work

* Grid dependence: We can localize more of the methods and move some functionality into the grid class. Then, we can have it return updated cells.
Alternative: We could also move the int[][] in the grid, and replace it with some other data structure (like a map) to only access specific elements.

* Change the way the grid is displayed: We could specify coordinates and a shape and have the display figure out how to best represent it.
Alternative: We could give it a basic grid pattern and display the new shapes statically. This wouldn't let us expand the grid, but
would be much easier to implement.

* We can create a button creator class to keep our code organized.

* Refactor Error Message: the process is the same for each error, so we can easily refactor the general process out.

* We have a lot of magic numbers to move out.