package data_structures;

public class Score
{
    CellStatus cellStatus;
    String digits;

    public Score(CellStatus status, String setdigits)
    {
        cellStatus = status;
        digits = setdigits;
    }

    public CellStatus getCellStatus()
    {
        return cellStatus;
    }

    public String getDigits()
    {
        return digits;
    }
}
