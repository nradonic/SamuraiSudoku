package sudoku;

import data_structures.CellStatus;
import data_structures.ExpandedGrid;
import data_structures.SudokuCellAndLocation;
import data_structures.SudokuCells;
import grid_operations.ScanGrid;
import operations.BoardScore;
import operations.ExpandedGridIO;
import operations.GridStatistics;
import operations.OperationLogging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Start
{
    public static void main(String[] args)
    {
//  Either from a fixed resource file or from an external JSON text file. See the resources wapo file for necessary format
        // blockPositions represent 9x9 grids that are overlapping and must be internally consistent to be solved
        boolean testFile = true;
        String testFileName = "wapo_5_1_21x21.sudoku";
        testFileName = "wapo_5_15_21x21a.sudoku";

        ExpandedGrid expandedGrid;

        expandedGrid = getInitialExpandedGrid(testFile, testFileName);

        // map expanded grid to a grid of Sudoku cells
        SudokuCells sudokuGrid = new SudokuCells(expandedGrid);

        System.out.println("Original Grid:\n" + sudokuGrid.reportCells());
        GridStatistics.countAndReportStatistics(sudokuGrid);
        System.out.println("----------------------------------------\n\n\n");

        ScanGrid.loopOverGrid(sudokuGrid);
        System.out.println("After first loop: promote Fixed values across Possibles\n" + sudokuGrid.reportCells());

        GridStatistics.countAndReportStatistics(sudokuGrid);

        SudokuCells k = solve(sudokuGrid);
        System.out.println("After solution loop: \n" + k.reportCells());
    }

    private static ExpandedGrid getInitialExpandedGrid(boolean testFile, String testFileName)
    {
        ExpandedGrid expandedGrid;
        if (testFile)
        {
            // test file
            expandedGrid = ExpandedGridIO.loadBoardFromResources(testFileName);
        } else
        {
            // select a file from the file system
            expandedGrid = ExpandedGridIO.loadExpandedGrid();
        }
        return expandedGrid;
    }


    private static ArrayList<SudokuCellAndLocation> collectPossibleCells(SudokuCells sudokuCells)
    {
        ArrayList<SudokuCellAndLocation> sudokuCellAndLocationArrayList = BoardScore.boardScores(sudokuCells);
        sudokuCellAndLocationArrayList.sort(Comparator.reverseOrder());

        var k = sudokuCellAndLocationArrayList.stream()
                .filter(e -> e.getSudokuCell().scoreCell().getCellStatus() == CellStatus.possible)
                .collect(Collectors.toList());

        return (ArrayList<SudokuCellAndLocation>) k;
    }

    private static SudokuCells solve(SudokuCells sudokuCells)
    {
        SudokuCells sudokuCells1 = sudokuCells.deepClone();
        ScanGrid.loopOverGrid(sudokuCells1);

        var possibleCells = collectPossibleCells(sudokuCells1);
        if (possibleCells.size() == 0)
        {
            return sudokuCells1;
        }
        var firstPossible = possibleCells.get(0);

        SudokuCells sudokuCellsNext1 = sudokuCells1;
        SudokuCells sudokuCellsNext2 = null;

        int minimumPossibles = possibleCells.size();

        while (collectPossibleCells(sudokuCellsNext1).size() != 0)
        {
            firstPossible = collectPossibleCells(sudokuCellsNext1).get(0);
            var possibleDigits = firstPossible.getSudokuCell()
                    .scoreCell()
                    .getDigits()
                    .split("");

            boolean wasPossible = false;
            System.out.println(" Row:Column " + firstPossible.getRow() + ":" + firstPossible.getColumn() + "  Possible digits: "
                    + Arrays.stream(possibleDigits).sequential()
                    .collect(Collectors.joining(",", "{", "}")));
            for (String s : possibleDigits)
            {
                SudokuCells sudokuCells2 = sudokuCellsNext1.deepClone();
                OperationLogging.markLogging(firstPossible.getRow(), firstPossible.getColumn(), CellStatus.calculated, s);
                sudokuCells2.markCell(firstPossible.getRow(), firstPossible.getColumn(), Integer.parseInt(s), CellStatus.calculated);
                ScanGrid.loopOverGrid(sudokuCells2);
                var t = BoardScore.countCellsByStatus(sudokuCells2, CellStatus.notpossible);
                if (t > 0)
                {
                    System.out.println("   XXXX   row:column " + firstPossible.getRow() + " : " + firstPossible.getColumn() + "   set to " + s + " impossible");
                    continue;
                }
                wasPossible = true;
                int localPossibles = collectPossibleCells(sudokuCells2).size();
                if (localPossibles < minimumPossibles)
                {
                    minimumPossibles = localPossibles;
                    sudokuCellsNext2 = sudokuCells2;
                }
            }
            assertTrue(wasPossible, "Failure of finding good possible value");
            sudokuCellsNext1 = sudokuCellsNext2;
            var testPoint = 1;
        }

        return sudokuCellsNext1;
    }

}
