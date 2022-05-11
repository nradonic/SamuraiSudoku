package operations;

import data_structures.CellStatus;

public class PromoteFixedValues
{
    //TBD promote Fixed entries across other cells, decreasing the possible values in other cells
    //TBD promote possible values to Calculated if appropriate

    public static void promoteValue(int blockRow, int blockCol, int row, int col, int digit, CellStatus status)
    {
        for (int bigblockRow = 0; bigblockRow < 3; bigblockRow++)
        {
            for (int bigblockCol = 0; bigblockCol < 3; bigblockCol++)
            {
                for (int smallblockRow = 0; smallblockRow < 3; smallblockRow++)
                {
                    for (int smallblockCol = 0; smallblockCol < 3; smallblockCol++)
                    {

                    }
                }
            }
        }
    }

}
