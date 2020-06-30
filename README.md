# Robot-Simulation
## Java Application

# Task
Write a program to move the stack of blocks from source to target with specific order and constraints (Some of these constraints will be automatically imposed by the Robot object). Movement of Robot arms, picking and dropping are controlled using the Robot methods described below.


## The methods of Robot class:
up(), down(), extend(), contract(), raise(), lower(), pick(), drop(),speedUp(), slowDown()
• The height (h) of the main arm can be changed by 1 unit using methods up() and
down(). The height (h) of the this arm must lie in the range 2 to 14, and is initially set
to 2.
• The second arm can be moved horizontally by 1 unit using extend() or contract(). The
width (w) must lie in the range 1 to 10, and is initially set to 1.
• The depth (d) of the third arm can be changed by 1 unit using lower() or raise(). The
depth cannot be less than 0 but must be less than the height of the main arm ( d < h ).
In the initial position depth d is set to 0 (it is not visible).
• The target column for all blocks is in column 1 while source is in column 10. The
column 9 can be used as a temporary location for parts D.
• An item can be picked from the top of the stack of blocks at source using pick().
• It can be dropped at the top of the stack of blocks using drop() (subject to specific
constraints in parts D). The height of blocks can be between 1 and 4.
• Robot class has two other methods slowDown(int factor) and speedUp(int factor) to
adjust the time between moves by the specified factor.

## Other Features
Collisions with other blocks or bars will cause the program to be aborted immediately
with an appropriate error message.

## The Tasks
Part A
The height of bars are pre-determined and set at 4.The height of the blocks are
predetermined and set at 2.

Part B
The user provides the heights of bars at runtime, but values supplied must be in the
range 1 – 8.

Part C
The user also provides the heights of blocks at runtime, but values supplied must be in
the range 1 – 4. In addition the total height of all the blocks cannot exceed 12.

Part D
The user can also specify an order in which the blocks should be placed at the
destination

## Examples for Parts A to D:
Part A: All blocks at source are of size 2 and all bars are of size 4.
Part B: All blocks at source are of height 2 as before but the bar heights may vary.
Part C: Block sizes can be specified in the range 1-4 Bar sizes can be specified in the range 1 to 8.
Example Input: java Robot 543623 34121
Part D: This part allows user to specify the bar heights and block heights as well the order in which
blocks must be placed in the Target. 
Example Input: java Robot 23456 2341 1234
Note the required order in the target must be a permutation of the block heights specified.

## Standard Requirements: 
A. Assume all the bars are of height 4 units and the blocks are of height 2 units respectively.
Complete the control() method of the RobotControl class to move all the blocks to the
respective targets.
(No input needed).

B. Allow the user to supply the heights of the 6 bars as command line arguments where the
heights of the bars may vary from 1 to 8.
Complete the control() method of the RobotControl class to move all the blocks to the
respective targets.
Sample input (bar heights): 734561. Based on these inputs, int array barHeights[] will be
automatically set as in: barHeights[0] = 7; barHeights[1] = 3; …. barHeights[5] = 1;

C. Allow the user to supply the heights of the 6 obstacles as well as the height of the four blocks
as command line arguments. Complete the control() method of the RobotControl class to
move all the blocks to the respective targets.
Sample Input bar heights and block heights : 876054 2312.
Based on these inputs int arrays barHeights[], blockHeights[]) will be automatically set as in.
barHeights[0] = 8; barHeights[1] = 7; …. barHeights[5] = 4;
blockHeights[0] = 2; blockHeights[1] = 3; … blockHeights[3] = 2;

D. Allow the blocks to be placed in the specified order. The required order will be passed as the
array argument required[] which you can make use in the program.
Sample Input bar heights and block heights : 876054 2312 3221
barHeights[0] = 8; barHeights[1] = 7; …. barHeights[5] = 4;
blockHeights[0] = 2; blockHeights[1] = 3; … blockHeights[3] = 2;
required[0] = 3; required[1] = 2; … required[3] = 1;

# Run Locally

• Pull the folder in Eclipse IDE For Java Developers.

• Run the program by clicking on "Run Configurations" as following:
You can specify the bar and block heights (for sections B to D) by selecting “Run” →
“Run Configurations” from the menu, then clicking on the “Arguments” tab and
typing in your arguments in the text box (for example, 734561 231231 for section C and for rest refer "Sample Input" in above section)
