# SamuraiSudoku
This Sudoku solver takes in a text file (JSON syntax) specifying the fixed cell values for a Samarai Sudoku puzzle.
Taken from the Washington Post. Fixed cell values are 1-9. Unknown cells are 0. Excluded cells are X or x.
There is one text row for every puzzle row. And one column for every puzzle column. In addition the bounds of the 9x9
sub-Sudoku blocks are specified in a numerical array pattern [[0,0],[6,6],[0,12],[12,0],[12,12]] with the pair being the
top left corner positions for the sub-Sudoku puzzle.

See the test sudoku text files for example JSON text data.

By changing the testFile flag, the user can bring up a file chooser and read in external files. 

This is running on Java 17, because why not. I don't think I'm using anything past Java 11, but haven't tested it.

Lessons:
-after all the simple cells are decided, pick any other cell and try all the values to see if that solves more cells or
comes up with an 'impossible' condition where all values are excluded. 

-I started by collecting all possible digit values in every cell, but after solving it this way, see that you only need
to track assigned cells, else you could call the cell values 'undecided' until you need to calculate their possible values.
But I'm not going back to rewrite it now. I'm done.

-also found out about deep cloning and iterative reporting of cellular data structures, and trying to make the process
linear and incremental. Easaier to trouble shoot one-step-at-a-time operations.

Lot's of fun. 


Nick
June 2022
