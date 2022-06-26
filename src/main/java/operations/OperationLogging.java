package operations;

import data_structures.CellStatus;

public class OperationLogging
{

    private static int cellMarks = 0;

    public static void markLogging(int row, int column, CellStatus cellStatus, String digit)
    {
        System.out.println("Line: " + cellMarks + "  row: " + row + "  column: " + column + "  status: " +
                CellStatus.calculated.toString() + " " + digit );
        cellMarks++;
    }
}
