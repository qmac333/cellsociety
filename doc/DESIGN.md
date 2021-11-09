# Cell Society Design Final
### William Convertino, Tim Jang, Quentin MacFarlane, Alexis Cruz-Ayala

## Team Roles and Responsibilities

William Convertino - Created the Grid and Cell classes, the base neighborhood and shape classes & their implementation,
Controller and Logic Controller, Error Handling, and the second display. I also created some of the test files, set up
the button functionality, and contributed heavily to the design of the project.

Tim Jang - 

Quentin MacFarlane - Created the backend for the WaTorWorld simulation, created the saving file logic and connected it 
with the controller. Created the English properties files, helped modify and debug the Cell and Grid classes to work 
with the new changes. Created a lot of tests as well.

Alexis Cruz-Ayala - 

## Design goals

The core design idea of our project shifted when we received the final set of changes.

We initially just wanted a system that would be able to run the simulations successfully,
and allow users to create new simulation types with ease. The key design goal here was to 
make the additional simulation types and any new files easy to add. We were fairly successful with
our initial design, but the new changes would have been difficult to implement.

We decided to rebuild much of the design to account for these changes. Our new design goals included
the ones previously mentioned, as well as the ability to add new border types, neighborhood patterns,
and shapes. We also wanted to be able to add new types of these features individually. For example,
if you wanted to add a new border, it should be as simple as creating a new border class, without needing
to create any additional files to make it compatible with each neighborhood and shape.

#### What Features are Easy to Add

With our final design, we were able to accomplish all of our design goals. Adding new simulations is
quite easy, requiring you only to extend the parent simulation class, initialize any new variables
in the constructor, and your logic to the updateNextGridFromCell method, and add an initialization 
method in the LogicController. Adding new sim files is also quite easy, as you can simply add any matching
keys and values in any order. Any unnecessary keys are ignored, and any missing keys have automatic error 
handling.

The new features are all also quite easy to implement. The new neighborhood patterns are the easiest, as
you only need to extend the parent class and add 1 method. The border patterns are quite easy too, as you
only need to make a new Grid child class and change the getNeighbors method. The hardest one to integrate is
a new shape, but this is mostly a matter of display. The logic is quite simple; you can extend the ShapeManager
class and fill out the required methods to establish which cells are considered its neighbors. Making it 
display properly is a bit more challenging, and requires you to make a Display class for the shape. This isn't
terribly difficult to do, and only requires the one class, but it still has a bit more overhead than the rest of the
features.

## High-level Design

The application is created with the Main and Controller classes, and an initial Display is created for the user. If a user wants to
play a simulation, they can load it using the ChooseFile button, which then signals for the LogicController to load a new Simulation
based on the sim/csv files. This Simulation is then updated on a loop, dictated by the LogicController, and the resulting cells are then
sent through the LogicController and into the display, where they are added to the screen.

When a Simulation is created, it updates its internal variables using the metadata in its sim file. Tt then initializes a new 
Grid based on its associated csv file and the border/shape type specified in the metadata.

When a Simulation is updated, it goes through each of the Cells in the Grid, and changes their next states based on the current and next state.
When every cell has been updated, the current states are set to be whatever value used to be the next state, and the next states are reset.
This allows for synchronization between iterations, and prevents conflicts that could occur if one cell's updated state would change the behavior
of a non-updated cell.

The Simulation's logic is based on a cell's neighbors, which they can get from the Grid class using the getNeighbors method. This method uses the passed
neighborhood pattern (which can be specified if needed, but usually just uses the pattern chosen by the metadata) as well as the Grid's shape and
border pattern to choose which cells are considered neighbors.

#### Core Classes

_Display_ - The Display class manages its active window, showing the active simulation grid and allowing the user to interact
with the program through buttons and clickable cells. We have a child display class for each different shape. They all share
the same basic functionality, but have different logic for how the cells are displayed.

_Logic Controller_ - The logic controller manages and updates the simulations. It can load a new simulation from a sim/csv file
using the initializeFromFile method, and updates it every couple seconds (depending on the current speed) by using a ScheduleExecutorService.
Each logic controller has a display that it communicates with to display the current state of the grid. It is worth noting that
when we create multiple concurrent simulations, there is a logic controller that loads and manages each one, and displays it to
its respective display. This allows us to load a new simulation, play/pause/change the speed of a simulation, or close a simulation
without it affecting any of the other active simulations.

_Controller_ - The controller manages the functionality of the application as a whole. It initializes each display and logic controller
when a new simulation instance is created, and handles all the button events.

_Simulation_ - The simulation class is the base class off which each of the simulations are created. when it is initialized, it sets up the
current simulation by reading the metadata in the sim file (the keys and values). Whenever the simulation is updated, the class
goes through each of its cells and calls the abstract updateNextGridFromCell method, which allows the child classes to implement
their own code for how each cell should react. The simulation file does not directly keep track of border logic, different shapes,
or neighborhood patterns, so these features can be changed separately and easily integrated by simply changing the values in the sim
file's metadata.

_Grid_ - The grid classes keep track of the cell's current and future states for any given simulation, and have functionality to 
change cell values. This allows the simulations to update the current state of the simulation. The grid's also have methods to get
the neighbors of a specific cell, which is useful for knowing how cells should change between iterations. The functionality for what
is defined as a neighbor is not contained in this class, allowing the neighborhood patterns and shapes to be changed without any
changes in the grid. Each grid does, however, keep track of the border pattern, and can dictate where, out of the neighbors, a cell
is able to go.

_Shape Manager_ - The shape manager's role is primarily to dictate the location of all surrounding cells relative to a given cell.
This allows us to create triangle, hexagon, or square grid and know which cells are considered adjacent. This doesn't directly determine
what the neighbors of a cell are, as the neighborhood pattern can be nearly anything, but it does create some fundamental rules for the structure
of our grid.

_NeighborhoodPattern_ - The neighborhood pattern class determines which cells are neighbors of another cell. These classes use the adjacency
provided by the shape of the cells to determine which cells are considered in its "neighborhood." For example, if you have a square grid, and
you want to say a neighbor is any adjacent cell above the current cell, the neighborhood pattern would find which cells follow this rule.

## Assumptions that Affect the Design

#### Features Affected by Assumptions


## Significant differences from Original Plan

Our original plan revealed the data structure for the grid, which we used a 2-D array for. This was not good practice,
so we decided to abstract this out so that our data structure was not unnecessarily revealed. Now, some methods 
do not require a 2-D array as a parameter anymore, which is very restricting. We changed our grid to be a Map of Coordinates
to Cells, where each Coordinate holds one Cell. These are objects that we created in our design. 

## New Features HowTo

_New Simulation type:_ To add a new simulation type, you can extend the Simulation main class, save any relevant metadata variables in the constructor,
and change the updateGridFromCell method to implement the rules of any given simulation. You also must add an initialization method in the LogicController.
This only requires you to add one method, as the LogicController uses reflection to select the simulation type.

_New neighborhood patterns:_ To add a new neighborhood pattern, you need to create a child class of the NeighborhoodPattern class. This class
has an abstract method, getNeighborhoodGroup, in which you consolidate the cells that are considered neighbors and return thier coordinates as a list. 
To find these coordinates, you will need to use the getNeighborX methods in the shape manager, which returns the adjacent cells in any given direction. The neighborhood patterns
are also implemented using reflection, so you don't need to add any new methods to use them.

_New border pattern:_ To add a new border pattern, you need to extend the Grid class and update the getNeighbors method to reflect the behavior of the
border. You can use the NeighborhoodPattern's getNeighborhoodGroup to return all cell coordinates that correspond to a neighbor, and then use those coordinates
to determine what cells are actually available for the simulations use. For example, the infinite border pattern would check for any invalid coordinates, and create
a new cell at that location, while the finite border just returns the valid coordinates' cells.

_New Shapes:_ To add a new shape, you start by creating a new ShapeManager class. This has a group of abstract methods you need to fill out to specify
how to determine the location of a cell's adjacent cells.

[TODO: Talk about the display]

#### Easy to Add Features

#### Other Features not yet Done

One thing we did not implement is showing the property values from the SIM file on the display. If we were to do this, all
we would have to do is pass the property values from the LogicController class, where they are parsed, into the Controller
class so that the Controller could pass it to the Display and the Display could create text for the property values.

We also did not implement using image files for the cell states instead of just colors, but this would be easy as well. You
would just pass the filepath parsed in LogicController over to Controller and have it pass the image over to the Display.
The Display would have to update the COLOR_MAP variable to use the values from Controller, so we would have to remove 
the hardcoded colors that are in there right now. 

We did not implement the user being able to choose which grid edge policy they wanted for the simulation. All this would
require is a new button in the ButtonManager class and on the event that the button is clicked, it would need to call
a new method in the LogicController which makes the grid take in the new grid type and create a grid instance of the
specific edge policy.

