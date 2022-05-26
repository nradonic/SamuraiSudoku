package grid_operations;

import data_structures.ExpandedGrid;
import data_structures.SudokuCell;
import data_structures.SudokuCells;

import java.util.Iterator;

public class ScanGrid
{

    public static String loopOverGrid(SudokuCells sudokuCells)
    {

        StringBuilder result = new StringBuilder();
        boolean cellsPromoted = false;
        do
        {
            Iterator<SudokuCell> sudokuCellIterator = sudokuCells.iterator();

        } while (cellsPromoted);


        return result.toString();
    }

    private static boolean isCalculatedSingleValue(ExpandedGrid expandedGrid, int row, int column)
    {

        return false;
    }
}
