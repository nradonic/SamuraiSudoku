package operations;

import data_structures.Block3x3;
import data_structures.CellStatus;
import data_structures.ExpandedGrid;

public class GridToBox
{

    public static Block3x3 extractBlockFromGrid(ExpandedGrid expandedGrid, int baseRow, int baseColumn)
    {
        Block3x3 block3x3 = new Block3x3();
        boolean success = true;

        for (int rowIndex = 0; rowIndex < 9; rowIndex++)
        {
            int blockRow = rowIndex / 3;
            int row = rowIndex % 3;

            for (int columnIndex = 0; columnIndex < 9; columnIndex++)
            {
                int blockColumn = columnIndex / 3;
                int column = columnIndex % 3;

                String s = expandedGrid.reportCell(baseRow + rowIndex, baseColumn + columnIndex);
                int digit = Integer.parseInt(s);
                CellStatus status = CellStatus.fixed;
                if (digit != 0)
                {
                    String marksuccess = block3x3.markCell(blockRow, blockColumn, row, column, digit, status);
                    success &= marksuccess.equals("Valid");
                }
            }

        }
        if (!success)
        {
            System.out.println("unable to load 9x9 block form grid: " + baseRow + " " + baseColumn);
            System.exit(9);
        }
        return block3x3;

    }
}
