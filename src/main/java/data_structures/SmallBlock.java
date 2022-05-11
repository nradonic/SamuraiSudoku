package data_structures;

public class SmallBlock
{
    final int width = 3;
    final int height = 3;

    SudokuCell[][] smallBlock = new SudokuCell[height][width];

    public SmallBlock()
    {
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                smallBlock[i][j] = new SudokuCell();
            }
        }
    }

    public String reportSmallBlock()
    {
        String result = "";
        for (int i = 0; i < height; i++)
        {
            result += reportRow(i);
            result += "\n";
        }
        return result;
    }

    public String reportRow(int row)
    {
        String result = "";
        result += String.join("-", smallBlock[row][0].report(),
                smallBlock[row][1].report(),
                smallBlock[row][2].report());
        return result;
    }

    public String setCell(int row, int col, int digit, CellStatus status)
    {
        if (!isValidInSmallBlock(digit))
        {
            return "Invalid";
        }
        smallBlock[row][col].markDigit(digit, status);
        unPossible(row,col,digit);
        return "Valid";
    }

    private boolean isValidInSmallBlock(int digit)
    {
        return !isDigitInSmallBlock(digit);
    }

    public boolean isDigitInSmallBlock(int digit)
    {
        for (int i = 0; i < height; i++)
        {
            if (isDigitInRow(i, digit))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isDigitInRow(int row, int digit)
    {
        for (int col = 0; col < width; col++)
        {
            if (smallBlock[row][col].containsDefiniteDigit(digit))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isDigitInColumn(int col, int digit)
    {
        for (int row = 0; row < width; row++)
        {
            if (smallBlock[row][col].containsDefiniteDigit(digit))
            {
                return true;
            }
        }
        return false;
    }

    public void unPossible(int row, int col, int digit)
    {
        for (int rowIndex = 0; rowIndex < 3 ; rowIndex++)
        {
            for (int colIndex = 0; colIndex < 3; colIndex++)
            {
                if (rowIndex!=row || colIndex != col)
                {
                    smallBlock[rowIndex][colIndex].markDigitNotPossible(digit);
                }
            }
        }
    }

}
