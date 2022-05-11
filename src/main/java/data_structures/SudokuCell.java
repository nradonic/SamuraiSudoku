package data_structures;

public class SudokuCell
{
    final int digits = 10;

    CellStatus[] sudokuValues = new CellStatus[digits];

    public SudokuCell()
    {
        sudokuValues = new CellStatus[digits];
        for (int i = 1; i < digits; i++)
        {
            {
                sudokuValues[i] = CellStatus.possible;
            }
        }
    }

    public String report()
    {
        boolean b = existsStatus(CellStatus.fixed);
        if (b) return "F" + whichValues(CellStatus.fixed);

        b = existsStatus(CellStatus.possible);
        if (b) return "P" + whichValues(CellStatus.possible);

        b = existsStatus(CellStatus.calculated);
        if (b) return "C" + whichValues(CellStatus.calculated);

        return "Error no fixed, calculated, or possible cell value";
    }

    private boolean existsStatus(CellStatus status)
    {
        for (int i = 1; i < digits; i++)
        {
            if (sudokuValues[i] == status)
            {
                return true;
            }
        }
        return false;
    }

    private String whichValues(CellStatus status)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < digits; i++)
        {
            if (sudokuValues[i] == status)
            {
                result.append(i);
            }
        }
        return result.toString();
    }

    public void markDigitNotPossible(int digit)
    {
        sudokuValues[digit] = CellStatus.notpossible;
    }

    public void markDigit(int digit, CellStatus cellStatus)
    {
        sudokuValues[digit] = cellStatus;
        if (cellStatus == CellStatus.notpossible || cellStatus == CellStatus.possible)
        {
            return;
        }
        if (cellStatus == CellStatus.calculated || cellStatus == CellStatus.fixed)
        {
            clearOtherDigits(digit);
        }
    }

    private void clearOtherDigits(int digit)
    {
        for (int i = 1; i < digits; i++)
        {
            if (i == digit)
            {
                continue;
            }
            markDigitNotPossible(i);
        }
    }

    public boolean containsDefiniteDigit(int digit)
    {
        return sudokuValues[digit] == CellStatus.fixed ||
                sudokuValues[digit] == CellStatus.calculated;
    }

}
