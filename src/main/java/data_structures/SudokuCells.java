package data_structures;

import grid_operations.MarkGridCell;
import junit.framework.Assert;

import java.util.Iterator;

public class SudokuCells
{
    ExpandedGrid expandedGrid;
    SudokuCell[][] data;
    int rows;
    int columns;

    int smallBlockRows = 0;
    int smallBlockColumns = 0;
    SmallBlock[][] smallBlocks;

    Block3x3[] block3x3s;
    Integer[][] blockPositions9x9; // array of two position arrays: [][row,column]
    int boards = 0;

    private static int iteratorRow = 0;
    private static int iteratorColumn = 0;
    private static int lastDeliveredIteratorRow = 0;
    private static int lastDeliveredIteratorColumn = 0;

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
        Assert.assertTrue("\nExpect nonzero rows and columns and divisible by 3 rows:" + rows + "  columns:" + columns,
                rows % 3 == 0 && columns % 3 == 0 && rows > 0 && columns > 0);
    }

    public SudokuCells(ExpandedGrid expandedGrid)
    {
        this(expandedGrid.getRows(), expandedGrid.getColumns());

        mapIndividualCells(expandedGrid);
        mapSudokuStructures();
        System.out.println("Report All:\n" + reportBlock3x3s());
    }

    public String reportBlock3x3s()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int b = 0; b < boards; b++)
        {
            stringBuilder.append("\nBoard: " + b + "  at: " + blockPositions9x9[b][0] + ":" + blockPositions9x9[b][1] + "\n");
            stringBuilder.append(block3x3s[b].reportBlock3x3() + "\n\n");
        }
        return stringBuilder.toString();
    }

    private void mapSudokuStructures()
    {

        smallBlockRows = rows / 3;
        smallBlockColumns = columns / 3;
        blockPositions9x9 = expandedGrid.getblockPositions();
        boards = blockPositions9x9.length;

        mapSmallBlocks();
        mapBlocks3x3();
    }

    private void mapBlocks3x3()
    {
        block3x3s = new Block3x3[blockPositions9x9.length];
        for (int b = 0; b < boards; b++)
        {
            Integer[] baseCoords = blockPositions9x9[b];
            int baseBlockRow = baseCoords[0] / 3;
            int baseBlockColumn = baseCoords[1] / 3;
            SmallBlock[][] smallBlocksTemp = new SmallBlock[3][3];

            for (int blockRow = 0; blockRow < 3; blockRow++)
            {
                for (int blockColumn = 0; blockColumn < 3; blockColumn++)
                {
                    smallBlocksTemp[blockRow][blockColumn] = smallBlocks[blockRow + baseBlockRow][blockColumn + baseBlockColumn];
                }
            }
            block3x3s[b] = new Block3x3(smallBlocksTemp);
        }
    }

    private void mapSmallBlocks()
    {
        smallBlocks = new SmallBlock[smallBlockRows][smallBlockColumns];
        for (int blockRow = 0; blockRow < smallBlockRows; blockRow++)
        {
            for (int blockColumn = 0; blockColumn < smallBlockColumns; blockColumn++)
            {
                SudokuCell[][] smallBlocksTemp = new SudokuCell[3][3];
                for (int row1 = 0; row1 < 3; row1++)
                {
                    for (int column1 = 0; column1 < 3; column1++)
                    {
                        int rowOffset = blockRow * 3 + row1;
                        int columnOffset = blockColumn * 3 + column1;
                        smallBlocksTemp[row1][column1] = data[rowOffset][columnOffset];
                    }
                }
                smallBlocks[blockRow][blockColumn] = new SmallBlock(smallBlocksTemp);
            }
        }
    }

    private void mapIndividualCells(ExpandedGrid expandedGrid)
    {
        this.expandedGrid = expandedGrid;
        for (int i = 0; i < expandedGrid.getRows(); i++)
        {
            for (int j = 0; j < expandedGrid.getColumns(); j++)
            {
                String k = expandedGrid.reportCell(i, j);
                if (k.compareTo("0") > 0)
                {
                    int value = Integer.parseInt(k);
                    data[i][j].markDigit(value, CellStatus.fixed);
                    MarkGridCell.markDigit(expandedGrid, this, i, j, value, CellStatus.fixed);
                }
            }
        }
    }

    public String reportCells()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--------------------\n");

        for (int row = 0; row < rows; row++)
        {
            for (int column = 0; column < columns; column++)
            {
                String k = data[row][column].report();
                String l = k.replaceAll("P123456789", "          ");
                stringBuilder.append(l);
                if ((column + 1) % 3 == 0 && (column + 1) != 9 && (column + 1) != 12)
                {
                    stringBuilder.append(" | ");
                }
                if ((column + 1) == 9)
                {
                    stringBuilder.append(" || ");
                }
                if ((column + 1) == 12)
                {
                    stringBuilder.append(" || ");
                }
            }

            stringBuilder.append("\n");
            if ((row + 1) % 3 == 0)
            {
                stringBuilder.append("\n");
            }
            if ((row + 1) == 9)
            {
                stringBuilder.append("\n\n");
            }
            if ((row + 1) == 12)
            {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public void markCell(int row, int column, int value, CellStatus cellStatus)
    {
        data[row][column].markDigit(value, cellStatus);
        for (int b = 0; b < boards; b++)
        {
            if (isInBlock(row, column, blockPositions9x9[b]))
            {
                Block3x3 sudoku = block3x3s[b];

                int boxRow = row - blockPositions9x9[b][0];
                int smallBlockHorizontal = boxRow / 3;
                int smallBlockRow = boxRow - 3 * smallBlockHorizontal;

                int boxColumn = column - blockPositions9x9[b][1];
                int smallBlockVertical = boxColumn / 3;
                int smallBlockColumn = boxColumn - smallBlockVertical * 3;

                sudoku.markCell(smallBlockHorizontal, smallBlockVertical, smallBlockRow, smallBlockColumn, value, cellStatus);
            }
        }
    }

    private boolean isInBlock(int row, int column, Integer[] integers)
    {
        boolean result = row >= integers[0] && row < integers[0] + 9
                && column >= integers[1] && column < integers[1] + 9;
        return result;
    }

    public SudokuCell getCell(int row, int column)
    {
        return data[row][column];
    }

    public Iterator<SudokuCell> iterator()
    {
        iteratorRow = 0;
        iteratorColumn = 0;
        lastDeliveredIteratorRow = 0;
        lastDeliveredIteratorColumn = 0;

        return new Iterator<SudokuCell>()
        {
            @Override
            public boolean hasNext()
            {
                return iteratorRow < rows && iteratorColumn < columns;
            }

            @Override
            public SudokuCell next()
            {
                lastDeliveredIteratorRow = iteratorRow;
                lastDeliveredIteratorColumn = iteratorColumn;

                SudokuCell k = data[iteratorRow][iteratorColumn];

                iteratorColumn++;
                if (iteratorColumn >= columns)
                {
                    iteratorRow++;
                    iteratorColumn = 0;
                }
                return k;
            }
        };
    }

    public int iteratorRow()
    {
        return lastDeliveredIteratorRow;
    }

    public int iteratorColumn()
    {
        return lastDeliveredIteratorColumn;
    }
}
