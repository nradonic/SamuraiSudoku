package sudoku;

import data_structures.ExpandedGrid;
import data_structures.SudokuCells;
import operations.ExpandedGridIO;

public class Start
{
    public static void main(String[] args)
    {

        ExpandedGrid expandedGrid = ExpandedGridIO.loadBoardFromResources("wapo_5_1_21x21.sudoku");

        // map expanded grid to a grid of Sudoku cells
        SudokuCells sudokuGrid = new SudokuCells(expandedGrid);

        System.out.println(sudokuGrid.reportCells());
    }

}
