# Connect4
Connect Four is a two-player connection game in which the players first choose a color and then take turns dropping colored discs from the top into a seven-column, six-row vertically suspended grid. The pieces fall straight down, occupying the next available space within the column. The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own discs. For more details, click [here](https://en.wikipedia.org/wiki/Connect_Four).

## Functions

createPattern: creates the initial board

checkWinner: checks if the board is in a terminal state

printPattern: prints the board

dropWhitePattern: white player drops a disk

dropBlackPattern: black player drops a disk

insertDisk, removeDisk, isOdd, columnFull: functions for making the moves

main: main function

EvalB: evaluation function for black player

minValueB, maxValueB: Alpha beta algorithm for black

EvalW: evaluation function for white player

minValueW, maxValueW: Alpha beta algorithm for white

The search depth can be increased/decreased by changing the "depth" parameter.
