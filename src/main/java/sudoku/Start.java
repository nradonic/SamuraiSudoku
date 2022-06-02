package sudoku;

import data_structures.CellStatus;
import data_structures.ExpandedGrid;
import data_structures.SudokuCellAndLocation;
import data_structures.SudokuCells;
import grid_operations.ScanGrid;
import operations.BoardScore;
import operations.ExpandedGridIO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Start
{
    public static void main(String[] args)
    {
//  Either from a fixed resource file or from an external JSON text file. See the resources wapo file for necessary format
        // blockPositions represent 9x9 grids that are overlapping and must be internally consistent to be solved
        boolean testFile = false;
        ExpandedGrid expandedGrid;

        if (testFile)
        {
            // test file
            expandedGrid = ExpandedGridIO.loadBoardFromResources("wapo_5_1_21x21.sudoku");
        } else
        {
            // select a file from the file system
            expandedGrid = ExpandedGridIO.loadExpandedGrid();
        }
        // map expanded grid to a grid of Sudoku cells
        SudokuCells sudokuGrid = new SudokuCells(expandedGrid);

        System.out.println("Original Grid:\n" + sudokuGrid.reportCells());
        ScanGrid.loopOverGrid(sudokuGrid);
        System.out.println("After first loop: promote Fixed values across Possibles\n" + sudokuGrid.reportCells());


        SudokuCells sudokuCells99 = copyAndProcess(sudokuGrid);

        // --------------------

//        ArrayList<SudokuCellAndLocation> sudokuCellAndLocationArrayList = BoardScore.BoardScores(sudokuGrid);
//        sudokuCellAndLocationArrayList.sort(Comparator.reverseOrder());
//        var k = sudokuCellAndLocationArrayList.stream().filter(e -> e.getSudokuCell().scoreCell().getCellStatus() == CellStatus.possible).collect(Collectors.toList());
//
//        System.out.println("Number of Possible cells: " + k.size());
//
//        SudokuCells sudokuCells = sudokuGrid.deepClone();
//        var first = k.get(0);
//        var l = k.get(0).getSudokuCell().scoreCell();
//        String digits = l.getDigits();
//
//        System.out.println("\nCopied cells:\n" + sudokuCells.reportCells());
//        System.out.println("Speculative setting of digits: " + digits.substring(0, 1) + "  at: " + first.getRow() + " : " + first.getColumn());
//        sudokuCells.markCell(first.getRow(), first.getColumn(), Integer.parseInt(digits.substring(0, 1)), CellStatus.calculated);
//        ScanGrid.loopOverGrid(sudokuCells);

        // --------------------

//        ArrayList<SudokuCellAndLocation> sudokuCellAndLocationArrayList6 = BoardScore.BoardScores(sudokuCells);
//        sudokuCellAndLocationArrayList6.sort(Comparator.reverseOrder());
//        var k6 = sudokuCellAndLocationArrayList6.stream().filter(e -> e.getSudokuCell().scoreCell().getCellStatus() == CellStatus.possible).collect(Collectors.toList());
//
//        System.out.println("Number of Possible cells: " + k6.size());
//        SudokuCells sudokuCells6 = sudokuCells.deepClone();
//        var first6 = k6.get(0);
//        var l6 = k6.get(0).getSudokuCell().scoreCell();
//        String digits6 = l6.getDigits();
//
//        System.out.println("\nCopied cells:\n" + sudokuCells6.reportCells());
//        System.out.println("Speculative setting of digits: " + digits6.substring(0, 1) + "  at: " + first6.getRow() + " : " + first6.getColumn());
//        sudokuCells6.markCell(first6.getRow(), first6.getColumn(), Integer.parseInt(digits6.substring(0, 1)), CellStatus.calculated);
//        ScanGrid.loopOverGrid(sudokuCells6);
//
//        //----------------------
//
//
//        ArrayList<SudokuCellAndLocation> sudokuCellAndLocationArrayList7 = BoardScore.BoardScores(sudokuCells6);
//        sudokuCellAndLocationArrayList7.sort(Comparator.reverseOrder());
//        var k7 = sudokuCellAndLocationArrayList7.stream().filter(e -> e.getSudokuCell().scoreCell().getCellStatus() == CellStatus.possible).collect(Collectors.toList());
//
//        System.out.println("Number of Possible cells: " + k7.size());
//        SudokuCells sudokuCells7 = sudokuCells6.deepClone();


//        System.out.println("\nLooped once: \n" + sudokuCells7.reportCells());

        int a = 1;

    }

    private static SudokuCells copyAndProcess(SudokuCells sudokuCells)
    {
        SudokuCells sudokuCells1 = sudokuCells.deepClone();

        ArrayList<SudokuCellAndLocation> sudokuCellAndLocationArrayList = BoardScore.BoardScores(sudokuCells1);
        sudokuCellAndLocationArrayList.sort(Comparator.reverseOrder());

        var k = sudokuCellAndLocationArrayList.stream().filter(e -> e.getSudokuCell().scoreCell().getCellStatus() == CellStatus.possible).collect(Collectors.toList());

        System.out.println("Number of Possible cells: " + k.size());

        var first = k.get(0);
        var l = k.get(0).getSudokuCell().scoreCell();
        String digits = l.getDigits();

        System.out.println("\nCopied cells:\n" + sudokuCells1.reportCells());
        System.out.println("Speculative setting of digits: " + digits.substring(0, 1) + "  at: " + first.getRow() + " : " + first.getColumn());
        sudokuCells1.markCell(first.getRow(), first.getColumn(), Integer.parseInt(digits.substring(0, 1)), CellStatus.calculated);
        ScanGrid.loopOverGrid(sudokuCells1);

        System.out.println("\nProcessed cells:\n" + sudokuCells1.reportCells());
        sudokuCellAndLocationArrayList = BoardScore.BoardScores(sudokuCells1);
        sudokuCellAndLocationArrayList.sort(Comparator.reverseOrder());
        k = sudokuCellAndLocationArrayList.stream().filter(e -> e.getSudokuCell().scoreCell().getCellStatus() == CellStatus.possible).collect(Collectors.toList());

        System.out.println("Number of Possible cells: " + k.size());
        return sudokuCells;
    }

}
