package data_structures;

import java.util.ArrayList;

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

    public Block3x3(SmallBlock[][] smallBlocks)
    {
        for (int row = 0; row < 3; row++)
        {
            for (int column = 0; column < 3; column++)
            {
                block3x3[row][column] = smallBlocks[row][column];
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
                StringBuilder k = new StringBuilder();
                for (int blockCol = 0; blockCol < width; blockCol++)
                {
                    k.append(block3x3[blockRow][blockCol].reportRow(smallBlockRow));
                    if (blockCol < width - 1)
                    {
                        k.append(" - ");
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

    public String markCell(int blockRow, int blockCol, int row, int col, int digit, CellStatus status)
    {
        // is digit found - across lines or in column or in block? if so don't insert - return error
        boolean digitFound = ValidityCheck.isDigitFound(this, blockRow, blockCol, row, col, digit);
        if (digitFound)
        {
            return "Invalid";
        }
        String result = "";
        for (int blockRowIndex = 0; blockRowIndex < 3; blockRowIndex++)
        {
            for (int blockColIndex = 0; blockColIndex < 3; blockColIndex++)
            {
                if (blockRowIndex == blockRow && blockColIndex == blockCol)
                {
                    result = block3x3[blockRowIndex][blockColIndex].setCell(row, col, digit, status);
                    continue;
                }
                if (blockRowIndex == blockRow)
                {
                    block3x3[blockRowIndex][blockColIndex].unPossibleInRow(row, digit);
                    continue;
                }
                if (blockColIndex == blockCol)
                {
                    block3x3[blockRowIndex][blockColIndex].unPossibleInCol(col, digit);
                }
            }
        }

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
    }

    public String reportCell(int blockRow, int blockCol, int row, int col)
    {
        String cellStatus = block3x3[blockRow][blockCol].reportCell(row, col);
        return cellStatus;
    }

    public ArrayList<Integer[]> changesList()
    {
        ArrayList<Integer[]> changes = new ArrayList<>();

        for (int rowBlock = 0; rowBlock < 3; rowBlock++)
        {
            for (int columnBlock = 0; columnBlock < 3; columnBlock++)
            {
                for (int row = 0; row < 3; row++)
                {
                    for (int column = 0; column < 3; column++)
                    {

                        String temp = block3x3[rowBlock][columnBlock].reportCell(row, column);
                        Integer value = Integer.parseInt(temp.substring(1).replaceAll(" ", ""));
                        if (temp.startsWith("C"))
                        {
                            changes.add(new Integer[]{rowBlock, columnBlock, row, column, value});
                        }
                    }
                }
            }
        }
        return changes;
    }
}
