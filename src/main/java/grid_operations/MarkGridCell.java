package grid_operations;

import data_structures.CellStatus;
import data_structures.ExpandedGrid;
import data_structures.SudokuCells;

public class MarkGridCell
{
    public static void markDigit(ExpandedGrid expandedGrid, SudokuCells sudokuCells, int row, int column, int value, CellStatus cellStatus)
    {
        if (isValueValidForThisCell(expandedGrid, row, column, value, cellStatus))
        {
            sudokuCells.markCell(row, column, value, cellStatus);
        }

    }

    private static boolean isValueValidForThisCell(ExpandedGrid expandedGrid, int row, int column, int value, CellStatus cellStatus)
    {
        return false;
    }
}
