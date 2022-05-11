package data_structures;

public class ValidityCheck
{

    public static boolean isDigitFound(Block3x3 block3x3, int blockRow, int blockCol, int row, int col, int digit)
    {
        // Check for digit across line
        boolean result = false;
        result |= block3x3.containsDefiniteDigitInRow(blockRow, row, digit);

        // Check for digit in column
        result |= block3x3.containsDefiniteDigitInColumn(blockCol, col, digit);

        // Check for digit in SmallBlock
        result |= block3x3.containsDefiniteDigitInSmallBlock(blockRow, blockCol, digit);

        // if a definite digit is not found, return true, else return false
        return result;
    }


}
