package operations;

import data_structures.CellStatus;
import data_structures.SudokuCell;
import data_structures.SudokuCellAndLocation;
import data_structures.SudokuCells;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class BoardScore
{


    public static ArrayList<SudokuCellAndLocation> boardScores(SudokuCells sudokuCells)
    {
        Iterator<SudokuCell> iterator = sudokuCells.iterator();
        ArrayList<SudokuCellAndLocation> sudokuCellAndLocationArrayList = new ArrayList<>();
        while (iterator.hasNext())
        {
            sudokuCellAndLocationArrayList.add(new SudokuCellAndLocation(iterator.next()
                    , sudokuCells.iteratorRow()
                    , sudokuCells.iteratorColumn()));
        }

        return sudokuCellAndLocationArrayList;
    }

    public static ArrayList<SudokuCellAndLocation> selectCellsByStatus(SudokuCells sudokuCells, CellStatus cellStatus)
    {
        var sudokuCellAndLocationArrayList = boardScores(sudokuCells);

        var filteredList = sudokuCellAndLocationArrayList.stream()
                .filter(e -> e.getSudokuCell().scoreCell().getCellStatus() == cellStatus)
                .collect(Collectors.toList());

        return (ArrayList<SudokuCellAndLocation>) filteredList;
    }



    public static int countCellsByStatus(SudokuCells sudokuCells, CellStatus cellStatus)
    {
        ArrayList<SudokuCellAndLocation> sudokuCellAndLocationArrayList = selectCellsByStatus(sudokuCells, cellStatus);

        return sudokuCellAndLocationArrayList.size();
    }

}
