package operations;

import data_structures.CellStatus;
import data_structures.SudokuCellAndLocation;
import data_structures.SudokuCells;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class GridStatistics
{

    public static ArrayList<SudokuCellAndLocation> countPossibleCells(SudokuCells sudokuCells)
    {
        ArrayList<SudokuCellAndLocation> sudokuCellAndLocationArrayList = BoardScore.boardScores(sudokuCells);
        sudokuCellAndLocationArrayList.sort(Comparator.reverseOrder());

        var k = sudokuCellAndLocationArrayList.stream()
                .filter(e -> e.getSudokuCell().scoreCell().getCellStatus() == CellStatus.possible)
                .collect(Collectors.toList());

        return (ArrayList<SudokuCellAndLocation>) k;
    }


    public static void countAndReportStatistics(SudokuCells sudokuCells)
    {
        for (CellStatus cellStatus : CellStatus.values())
        {
            System.out.println(cellStatus.toString() + ": " + BoardScore.countCellsByStatus(sudokuCells, cellStatus) + "\n");
        }
    }
}
