package data_structures;

public class BoardFromString
{
    public static Block3x3 createBoard(String board)
    {
        Block3x3 block3x3 = new Block3x3();
        String[] rows = board.split("\n");
        assert (rows.length == 9);

        processRows(block3x3, rows);
        return block3x3;
    }

    private static void processRows(Block3x3 block3x3, String[] rows)
    {
        for (int rowIndex = 0; rowIndex < 9; rowIndex++)
        {
            String[] dataBits = rows[rowIndex].split("-");

            System.out.println("Row: " + rows[rowIndex] );
            assert (dataBits.length == 9);

            int blockRow = rowIndex / 3;
            int row = rowIndex % 3;

            processColumns(block3x3, dataBits, blockRow, row);
        }
    }

    private static void processColumns(Block3x3 block3x3, String[] dataBits, int blockRow, int row)
    {
        for (int columnIndex = 0; columnIndex < 9; columnIndex++)
        {
            int blockCol = columnIndex / 3;
            int col = columnIndex % 3;

            String item = dataBits[columnIndex].replaceAll(" ", "");
            if(!item.isEmpty())
            {
                setCell(block3x3, blockRow, row, blockCol, col, item);
            }
        }
    }

    private static void setCell(Block3x3 block3x3, int blockRow, int row, int blockCol, int col, String item)
    {
        CellStatus status = CellStatus.getStatusFromString(item);
        if (status == CellStatus.fixed)
        {
            int digit = Integer.parseInt(item.substring(1, 2));
            block3x3.markCell(blockRow, blockCol, row, col, digit, status);
        }
    }
}
