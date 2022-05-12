package operations;

import data_structures.Block3x3;
import data_structures.CellStatus;

import static org.apache.maven.surefire.shade.org.apache.maven.shared.utils.StringUtils.substring;

public class PromoteFixedValues
{
    //TBD promote Fixed entries across other cells, decreasing the possible values in other cells
    //TBD promote possible values to Calculated if appropriate

    public static boolean promoteProbableSingles(Block3x3 block3x3)
    // int blockRow, int blockCol, int row, int col, int digit, CellStatus status)
    {
        boolean promoted = false;
        for (int bigblockRow = 0; bigblockRow < 3; bigblockRow++)
        {
            for (int bigblockCol = 0; bigblockCol < 3; bigblockCol++)
            {
                for (int smallblockRow = 0; smallblockRow < 3; smallblockRow++)
                {
                    for (int smallblockCol = 0; smallblockCol < 3; smallblockCol++)
                    {
                        String cellStatus = block3x3.reportCell(bigblockRow, bigblockCol, smallblockRow, smallblockCol);
                        if (probableAndSingle(cellStatus))
                        {
                            System.out.println("Changes: " + bigblockRow + ":" + bigblockCol + ":" + smallblockRow + ":" + smallblockCol + ":" + getDigit(cellStatus));

                            block3x3.markCell(bigblockRow, bigblockCol, smallblockRow, smallblockCol, getDigit(cellStatus), CellStatus.calculated);
                            promoted = true;
                        }
                    }
                }
            }
        }
        return promoted;
    }

    private static int getDigit(String status)
    {
        int digit = Integer.parseInt(status.replaceAll(" ", "").substring(1, 2));
        return digit;
    }

    private static boolean probableAndSingle(String status)
    {
        boolean possible = status.substring(0, 1).equals(CellStatus.possible.getSymbol());
        String digits = substring(status, 1).replaceAll(" ", "");
        boolean length2 = digits.length() == 1;
        return possible && length2;
    }

}
