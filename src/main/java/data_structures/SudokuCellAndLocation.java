package data_structures;

public class SudokuCellAndLocation implements Comparable<SudokuCellAndLocation>
{
    private SudokuCell sudokuCell;
    private int row = -1;
    private int column = -1;

    public SudokuCellAndLocation(SudokuCell sudokuCell, int row, int column)
    {
        this.sudokuCell = sudokuCell;
        this.row = row;
        this.column = column;
    }

    public SudokuCell getSudokuCell()
    {
        return sudokuCell;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public int compareTo(SudokuCellAndLocation o)
    {
        Score scoreExternal = o.getSudokuCell().scoreCell();
        Score scoreInternal = getSudokuCell().scoreCell();

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
                o.getSudokuCell().whichDigitValues(CellStatus.possible)
                        .replaceAll(" ", "")
                        .length()
                        - getSudokuCell().whichDigitValues(CellStatus.possible)
                        .replaceAll(" ", "")
                        .length());
    }
}
