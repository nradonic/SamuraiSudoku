package data_structures;

public class SudokuCells
{
    SudokuCell[][] data;
    int rows;
    int columns;

    public SudokuCells(int rows0, int columns0)
    {
        data = new SudokuCell[rows0][columns0];
        for (int i = 0; i < rows0; i++)
        {
            for (int j = 0; j < columns0; j++)
            {
                data[i][j] = new SudokuCell();
            }
        }
        rows = rows0;
        columns = columns0;
    }

    public SudokuCells(ExpandedGrid expandedGrid)
    {
        this(expandedGrid.getRows(), expandedGrid.getColumns());
        mapCells(expandedGrid);
    }

    private void mapCells(ExpandedGrid expandedGrid)
    {
        for (int i = 0; i < expandedGrid.getRows(); i++)
        {
            for (int j = 0; j < expandedGrid.getColumns(); j++)
            {
                String k = expandedGrid.reportCell(i, j);
                if (k.compareTo("0") > 0)
                {
                    int l = Integer.parseInt(k);
                    data[i][j].markDigit(l, CellStatus.fixed);
                }
            }
        }
    }

    public String reportCells()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < rows; row++)
        {
            for (int column = 0; column < columns; column++)
            {
                String k = data[row][column].report();
                String l = k.replaceAll("P123456789", "          ");
                stringBuilder.append(l);
                stringBuilder.append(".");
                if ((column + 1) % 3 == 0)
                {
                    stringBuilder.append(".");
                }
                if ((column + 1) % 9 == 0)
                {
                    stringBuilder.append(".");
                }
                if ((column + 1) % 12 == 0)
                {
                    stringBuilder.append(".");
                }
            }
            stringBuilder.append("\n");
            if ((row + 1) % 3 == 0)
            {
                stringBuilder.append("\n");
            }
            if ((row + 1) % 9 == 0)
            {
                stringBuilder.append("\n\n");
            }
            if ((row + 1) % 12 == 0)
            {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
