package data_structures;

import static org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils.leftPad;

public class SudokuCell implements Comparable<SudokuCell>
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
        if (b) return leftPad("F" + whichDigitValues(CellStatus.fixed), 10);

        b = existsStatus(CellStatus.possible);
        if (b) return leftPad("P" + whichDigitValues(CellStatus.possible), 10);

        b = existsStatus(CellStatus.calculated);
        if (b) return leftPad("C" + whichDigitValues(CellStatus.calculated), 10);

        b = existsStatus(CellStatus.exclude);
        if (b) return leftPad(" ", 10);

        //return "Error no fixed, calculated, or possible cell value";
        return "..........";
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

    protected String whichDigitValues(CellStatus status)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < digits; i++)
        {
            if (sudokuValues[i] == status)
            {
                result.append(i);
                continue;
            }
            result.append(" ");
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

    public Score scoreCell()
    {
        boolean b = existsStatus(CellStatus.fixed);
        if (b) return new Score(CellStatus.fixed, whichDigitValues(CellStatus.fixed).replaceAll(" ", ""));

        b = existsStatus(CellStatus.calculated);
        if (b) return new Score(CellStatus.calculated, whichDigitValues(CellStatus.calculated).replaceAll(" ", ""));

        b = existsStatus(CellStatus.possible);
        if (b) return new Score(CellStatus.possible, whichDigitValues(CellStatus.possible).replaceAll(" ", ""));

        b = existsStatus(CellStatus.notpossible);
        if (b) return new Score(CellStatus.notpossible, whichDigitValues(CellStatus.notpossible).replaceAll(" ", ""));


        return new Score(CellStatus.exclude, whichDigitValues(CellStatus.exclude).replaceAll(" ", ""));

    }

    @Override
    public int compareTo(SudokuCell o)
    {
        Score scoreExternal = o.scoreCell();
        Score scoreInternal = scoreCell();
        int k = scoreInternal.cellStatus.weight() - scoreExternal.cellStatus.weight();
        if (k != 0)
        {
            return (int) Math.signum(k);
        }
        if (scoreInternal.cellStatus != CellStatus.possible)
        {
            return 0;
        }
        return (int) Math.signum(
                o.whichDigitValues(CellStatus.possible)
                        .replaceAll(" ", "")
                        .length()
                        - whichDigitValues(CellStatus.possible)
                        .replaceAll(" ", "")
                        .length());
    }

    public SudokuCell deepClone()
    {
        SudokuCell sudokuCell1 = new SudokuCell();
        for (int i = 0; i < digits; i++)
        {
            sudokuCell1.markDigit(i, sudokuValues[i]);
        }
        return sudokuCell1;
    }

    public void excludeCell()
    {
        for (int i = 0; i < digits; i++)
        {
            markDigit(i,CellStatus.exclude);
        }
    }
}
