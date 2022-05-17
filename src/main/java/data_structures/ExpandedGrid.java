package data_structures;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ExpandedGrid
{
    private Integer rows;
    private Integer columns;
    private String[] data;
    private Integer[][] blockPositions9x9;
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
        result &= blockPositions9x9.length >= 1;
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

    public String report()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Label: " + label + "\n")
                .append("Type: " + type + ", Revision: " + rev + "\n")
                .append("Rows: " + rows + " : " + "Columns: " + columns + "\n")
                .append("Data:\n" + dataToString() + "\n")
                .append("Block locations:\n" + blockPositions() + "\n");
        return stringBuilder.toString();
    }

    private String dataToString()
    {
        return Arrays.stream(data).collect(Collectors.joining("\n"));
    }

    private String blockPositions()
    {
        return Arrays.stream(blockPositions9x9).
                map(k -> "(" + k[0] + "," + k[1] + ")").
                collect(Collectors.joining("\n"));
    }

    public void bumpRevision()
    {
        rev++;
    }
}
