package data_structures;

public class Block3x3
{
    final int width = 3;
    final int height = 3;
    SmallBlock[][] block3x3 = new SmallBlock[height][width];

    public Block3x3()
    {
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                block3x3[i][j] = new SmallBlock();
            }
        }

    }

    public String reportBlock3x3()
    {
        StringBuilder result = new StringBuilder();

        for (int blockRow = 0; blockRow < height; blockRow++)
        {
            for (int smallBlockRow = 0; smallBlockRow < height; smallBlockRow++)
            {
                String k = "";
                for (int blockCol = 0; blockCol < width; blockCol++)
                {
                    k += block3x3[blockRow][blockCol].reportRow(smallBlockRow);
                    if (blockCol < width - 1)
                    {
                        k += "-";
                    }
                }
                result.append(k);
                if (blockRow != height - 1 || smallBlockRow != height - 1)
                {
                    result.append("\n");
                }
            }
        }
        return result.toString();
    }

    public String reportBlock3x3ShortFormat()
    {
        String reportLong = this.reportBlock3x3();
        String reportShort = reportLong.replace("P123456789", "  ");
        reportShort += "\n";
        return reportShort;
    }

    public String markCell(int blockRow, int blockCol, int row, int col, int digit, CellStatus status)
    {
        // is digit found - across lines or in column or in block? if so don't insert - return error
        boolean digitFound = ValidityCheck.isDigitFound(this, blockRow, blockCol, row, col, digit);
        if (digitFound)
        {
            return "Invalid";
        }
        String result = "";
        result = block3x3[blockRow][blockCol].setCell(row, col, digit, status);
        return result;
    }

    public boolean containsDefiniteDigitInRow(int blockRow, int row, int digit)
    {
        boolean result = false;
        for (int blockCol = 0; blockCol < width; blockCol++)
        {
            SmallBlock smallBlock = block3x3[blockRow][blockCol];
            result |= smallBlock.isDigitInRow(row, digit);
        }
        return result;
    }


    public boolean containsDefiniteDigitInColumn(int blockCol, int col, int digit)
    {
        boolean result = false;
        for (int blockRow = 0; blockRow < width; blockRow++)
        {
            SmallBlock smallBlock = block3x3[blockRow][blockCol];
            result |= smallBlock.isDigitInColumn(col, digit);
        }
        return result;
    }

    public boolean containsDefiniteDigitInSmallBlock(int blockRow, int blockCol, int digit)
    {
        return block3x3[blockRow][blockCol].isDigitInSmallBlock(digit);
    };
}
