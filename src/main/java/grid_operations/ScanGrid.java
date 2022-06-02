package grid_operations;

import data_structures.CellStatus;
import data_structures.ExpandedGrid;
import data_structures.SudokuCell;
import data_structures.SudokuCells;
import junit.framework.Assert;

import java.util.Iterator;

public class ScanGrid
{

    public static String loopOverGrid(SudokuCells sudokuCells)
    {
        StringBuilder result = new StringBuilder();
        boolean cellsPromoted = false;
        do
        {
            cellsPromoted = false;
            Iterator<SudokuCell> sudokuCellIterator = sudokuCells.iterator();
            while (sudokuCellIterator.hasNext())
            {
                SudokuCell sudokuCell = sudokuCellIterator.next();
                int row = sudokuCells.iteratorRow();
                int column = sudokuCells.iteratorColumn();
                String s = sudokuCell.report();
                String status = s.substring(0, 1);
                String digits = s.substring(1).replaceAll(" ", "");
                if (digits.length() == 1)
                {
                    switch (status)
                    {
                        case "P":
                            sudokuCells.markCell(row, column, Integer.parseInt(digits), CellStatus.calculated);
                            System.out.println("row: " + row + "  column: " + column + "  status: " + CellStatus.calculated.toString() + " " + digits + "\n");
                            cellsPromoted = true;
                            break;
                        case "F":
                            sudokuCells.markCell(row, column, Integer.parseInt(digits), CellStatus.fixed);
                            break;
                        case "N":
                            Assert.assertTrue("Not Possible Status: " + status, false);
                        case "C":
                            sudokuCells.markCell(row, column, Integer.parseInt(digits), CellStatus.calculated);
//                            System.out.println("row: " + row + "  column: " + column + "  status: " + CellStatus.calculated.toString() + " " + digits + "\n");
                            break;
                        default:
                            Assert.assertTrue("Base case: " + status, false);
                    }
                }
                int a = 1;
//                System.out.println("Row: " + row + " Column: " + column + " :" + s);
            }
        } while (cellsPromoted);
        return result.toString();
    }

    private static boolean isCalculatedSingleValue(ExpandedGrid expandedGrid, int row, int column)
    {

        return false;
    }
}
