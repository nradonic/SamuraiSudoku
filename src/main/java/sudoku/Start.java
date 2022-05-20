package sudoku;

import data_structures.ExpandedGrid;
import data_structures.SudokuCells;
import operations.ExpandedGridIO;

public class Start
{
    public static void main(String[] args)
    {

        ExpandedGrid expandedGrid = ExpandedGridIO.loadBoardFromResources("wapo_5_1_21x21.sudoku");
        int rows = expandedGrid.getRows();
        int columns = expandedGrid.getColumns();

        SudokuCells sudokuGrid = new SudokuCells(expandedGrid);
        System.out.println(sudokuGrid.reportCells());
    }

}
