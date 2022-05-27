package sudoku;

import data_structures.ExpandedGrid;
import data_structures.SudokuCells;
import grid_operations.ScanGrid;
import operations.ExpandedGridIO;

public class Start
{
    public static void main(String[] args)
    {
//  Either from a fixed resource file or from an external JSON text file. See the resources wapo file for necessary format
        // blockPositions represent 9x9 grids that are overlapping and must be internally consistent to be solved

        // test file
        ExpandedGrid expandedGrid = ExpandedGridIO.loadBoardFromResources("wapo_5_1_21x21.sudoku");

        // select a file from the file system
        // ExpandedGrid expandedGrid = ExpandedGridIO.loadExpandedGrid();

        // map expanded grid to a grid of Sudoku cells
        SudokuCells sudokuGrid = new SudokuCells(expandedGrid);

        System.out.println(sudokuGrid.reportCells());
        ScanGrid.loopOverGrid(sudokuGrid);
        System.out.println(sudokuGrid.reportCells());
        ScanGrid.loopOverGrid(sudokuGrid);
        System.out.println(sudokuGrid.reportCells());



    }

}
