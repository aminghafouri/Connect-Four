# Connect4

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
