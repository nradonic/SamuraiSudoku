package operations;

import data_structures.SudokuCell;
import data_structures.SudokuCellAndLocation;
import data_structures.SudokuCells;

import java.util.ArrayList;
import java.util.Iterator;

public class BoardScore
{


    public static ArrayList<SudokuCellAndLocation> BoardScores(SudokuCells sudokuCells)
    {
        Iterator<SudokuCell> iterator = sudokuCells.iterator();
        ArrayList<SudokuCellAndLocation> sudokuCellAndLocationArrayList = new ArrayList<>();
        while (iterator.hasNext())
        {
            sudokuCellAndLocationArrayList.add(new SudokuCellAndLocation(iterator.next(), sudokuCells.iteratorRow(), sudokuCells.iteratorColumn()));
        }

        return sudokuCellAndLocationArrayList;
    }
}
