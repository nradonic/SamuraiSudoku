package sudoku;

import data_structures.CellStatus;
import data_structures.ExpandedGrid;
import data_structures.SudokuCellAndLocation;
import data_structures.SudokuCells;
import grid_operations.ScanGrid;
import operations.BoardScore;
import operations.ExpandedGridIO;
import operations.GridStatistics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Start
{
    public static void main(String[] args)
    {
//  Either from a fixed resource file or from an external JSON text file. See the resources wapo file for necessary format
        // blockPositions represent 9x9 grids that are overlapping and must be internally consistent to be solved
        boolean testFile = true;
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
        GridStatistics.countAndReportStatistics(sudokuGrid);
        System.out.println("----------------------------------------\n\n\n");
        ScanGrid.loopOverGrid(sudokuGrid);
        System.out.println("After first loop: promote Fixed values across Possibles\n" + sudokuGrid.reportCells());

        GridStatistics.countAndReportStatistics(sudokuGrid);
        SudokuCells sudokuCells99 = copyAndProcess(sudokuGrid);
        GridStatistics.countAndReportStatistics(sudokuCells99);
        int a = 1;

    }

    private static SudokuCells copyAndProcess(SudokuCells sudokuCells)
    {
        SudokuCells sudokuCells1 = sudokuCells.deepClone();

        ArrayList<SudokuCellAndLocation> possibleCells = countPossibleCells(sudokuCells1);

        System.out.println("Number of Possible cells: " + possibleCells.size());

        var first = possibleCells.get(0);
        var l = possibleCells.get(0).getSudokuCell().scoreCell();
        String digits = l.getDigits();

        System.out.println("\nCopied cells:\n" + sudokuCells1.reportCells());
        System.out.println("Speculative setting of digits: " + digits.substring(0, 1) + "  at: " + first.getRow() + " : " + first.getColumn());
        sudokuCells1.markCell(first.getRow(), first.getColumn(), Integer.parseInt(digits.substring(0, 1)), CellStatus.calculated);
        ScanGrid.loopOverGrid(sudokuCells1);

        System.out.println("\nProcessed cells:\n" + sudokuCells1.reportCells());
        possibleCells = BoardScore.boardScores(sudokuCells1);
        possibleCells.sort(Comparator.reverseOrder());
        var k = possibleCells.stream().filter(e -> e.getSudokuCell().scoreCell().getCellStatus() == CellStatus.possible).collect(Collectors.toList());

        System.out.println("Number of Possible cells: " + k.size());
        return sudokuCells1;
    }

    private static ArrayList<SudokuCellAndLocation> countPossibleCells(SudokuCells sudokuCells)
    {
        ArrayList<SudokuCellAndLocation> sudokuCellAndLocationArrayList = BoardScore.boardScores(sudokuCells);
        sudokuCellAndLocationArrayList.sort(Comparator.reverseOrder());

        var k = sudokuCellAndLocationArrayList.stream().filter(e -> e.getSudokuCell().scoreCell().getCellStatus() == CellStatus.possible).collect(Collectors.toList());

        return (ArrayList<SudokuCellAndLocation>) k;
    }

}
