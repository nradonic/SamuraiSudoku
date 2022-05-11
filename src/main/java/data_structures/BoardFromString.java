package data_structures;

import java.util.ArrayList;

public class BoardFromString
{
    public static Block3x3 createBoard(String board)
    {
        Block3x3 block3x3 = new Block3x3();
        String b2 = board.replaceAll(" ", "");
        String[] rows = b2.split("\n");
        // 9 lines
        assert (rows.length == 9);

        for (int rowIndex = 0; rowIndex < 9; rowIndex++)
        {
            String[] dataBits = rows[rowIndex].split("-");
            assert (dataBits.length == 9);

            int blockRow = rowIndex / 3;
            int row = blockRow % 3;

            for (int column = 0; column < 9; column++)
            {
                int blockCol = column / 3;
                int col = column % 3;

                String item = dataBits[column];
                CellStatus status = CellStatus.getStatusFromString(item);
                if(status == CellStatus.fixed)
                {
                    int digit = Integer.parseInt(item.substring(1,2));
                    block3x3.markCell(blockRow, blockCol, row, col, digit, status);
                }
            }
        }
        return block3x3;
    }


    private static int[] stripStatus(String item)
    {
        ArrayList<Integer> results = new ArrayList<>();

        for (int i = 1; i < item.length(); i++)
        {
            results.add(Integer.parseInt(item.substring(i, i)));
        }
        int[] arr = new int[results.size()];

        for (int i = 0; i < results.size(); i++)
        {
            arr[i] = results.get(i);
        }

        return arr;
    }
}
