package data_structures;

public class ExpandedGrid
{
    private Integer rows;
    private Integer columns;
    private String[] data;
    private Integer[][] blocks9x9;
    private String type;
    private String label;
    private Integer rev;

    private ExpandedGrid()
    {
    }


    public int getRows()
    {
        return rows;
    }

    public int getColumns()
    {
        return columns;
    }

    public boolean assertValid()
    {
        boolean result = true;
        result &= rows >= 9;
        result &= columns >= 9;
        result &= data.length == rows;
        result &= blocks9x9.length >= 1;
        result &= !type.isEmpty();
        result &= rev != null;
        return result;
    }

    public String reportCell(int row, int column)
    {
        if (row < 0 || row >= rows || column < 0 || column >= columns)
        {
            System.out.println("Expanded Grid index out of legal range, row: " + row + " column: " + column);
            return "Invalid";
        }
        String line = data[row].replaceAll("[\\D]", "");
        return line.substring(column, column + 1);
    }
}
