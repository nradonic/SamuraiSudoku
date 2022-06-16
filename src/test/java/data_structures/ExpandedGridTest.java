package data_structures;

import operations.ExpandedGridIO;
import operations.GridToBox;
import operations.PromoteFixedValues;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpandedGridTest
{

    @Test
    void loadGridFromResourceFile()
    {
        ExpandedGrid expandedGrid = getExpandedGridFromResourceFile();
        assertEquals("0", expandedGrid.reportCell(0, 0));
        assertEquals("7", expandedGrid.reportCell(0, 1));
        System.out.println(expandedGrid.report());

    }

    private ExpandedGrid getExpandedGridFromResourceFile()
    {
        ExpandedGrid expandedGrid = ExpandedGridIO.loadBoardFromResources("wapo_5_1_21x21.sudoku");
        return expandedGrid;
    }


    @Disabled
    @Test
    void manualLoadExpandedGridFromFile()
    {
        ExpandedGrid expandedGrid = ExpandedGridIO.loadExpandedGrid();
        System.out.println("\nExpanded Grid: " + "\n" + expandedGrid.report() + "\n");
        expandedGrid.bumpRevision();
        System.out.println("\nExpanded Grid: " + "\n" + expandedGrid.report() + "\n");
        assertTrue(expandedGrid.assertValid(), "assert valid expanded grid");
    }

    @Test
    void selectAllSubPuzzles()
    {
        ExpandedGrid expandedGrid = getExpandedGridFromResourceFile();
        System.out.println("***************************\nStarting point 0:\n" + expandedGrid.report());

        assertTrue(expandedGrid.assertValid());

        Integer[][] blockPositions = expandedGrid.getblockPositions();
        int boards = blockPositions.length;

        Block3x3[] sudokus = new Block3x3[]{};

        boolean collectedChanges = true;
        while (collectedChanges)
        {
            sudokus = extractSudokus(expandedGrid, blockPositions, boards);
            System.out.println("***************************\nStarting point 1:\n" + expandedGrid.report());
            collectedChanges = false;
            for (int i = 0; i < boards; i++)
            {
                boolean boardChange = true;
                while (boardChange)
                {
                    boardChange = PromoteFixedValues.promoteProbableSingles(sudokus[i]);
                    if (boardChange)
                    {
                        collectedChanges = true;
                    }
                }
            }
            findCalculatedValues(expandedGrid, boards, blockPositions, sudokus);

            System.out.println("Changes: " + collectedChanges + "  *******************  Next Loop  *******************\n");//+ expandedGrid.report());
        }
    }

    private void findCalculatedValues(ExpandedGrid expandedGrid, int boards, Integer[][] blockPositions, Block3x3[] sudokus)
    {
        for (int board = 0; board < boards; board++)

        {
            var x = sudokus[board].changesList();
            System.out.println("C changes list: Board: " + blockPositions[board][0] + ":" + blockPositions[board][1]);
            for (int j = 0; j < x.size(); j++)
            {
                System.out.println(x.get(j)[0] + ":" + x.get(j)[1] + ":" + x.get(j)[2] + ":" + x.get(j)[3] + ":"
                        + x.get(j)[4]); //TBD print out list of changes....and metadata
            }
            insertChangesIntoExpandedGrid(expandedGrid, blockPositions[board], x);
            int a = 1;
        }
    }

    private void insertChangesIntoExpandedGrid(ExpandedGrid expandedGrid, Integer[] blockPositions, ArrayList<Integer[]> x)
    {
        int blockRow = 3;
        int blockColumn = 3;
        int rowIndex = 2;
        int columnIndex = 3;
        int valuePosition = 4;

        //System.out.println("Original:\n" + expandedGrid.report() + "\n");

        for (int i = 0; i < x.size(); i++)
        {
            int row = blockPositions[0] + x.get(i)[0] * blockRow + x.get(i)[2];
            int column = blockPositions[1] + x.get(i)[1] * blockColumn + x.get(i)[3];
            int value = x.get(i)[4];
            expandedGrid.setCell(row, column, value);
            System.out.println("Set cell: Row: " + row + "  Column: " + column + " Value: " + value);
        }
    }

    private Block3x3[] extractSudokus(ExpandedGrid expandedGrid, Integer[][] blockPositions, int boards)
    {
        Block3x3[] sudokus = new Block3x3[boards];

        Integer[] coords;
        for (int i = 0; i < blockPositions.length; i++)
        {
            coords = blockPositions[i];
            sudokus[i] = GridToBox.extractBlockFromGrid(expandedGrid, coords[0], coords[1]);
            System.out.println("Coords: " + coords[0] + ":" + coords[1] + "\n" + sudokus[i].reportBlock3x3() + "\n");

        }
        return sudokus;
    }

    @Test
    void testModificationOfOriginalGrid()
    {
        ExpandedGrid expandedGrid = getExpandedGridFromResourceFile();
        System.out.println("Original:\n" + expandedGrid.report() + "\n");
        expandedGrid.setCell(0, 0, 9);
        String result = expandedGrid.reportCell(0, 0);
        assertEquals("9", result, "modified cell 0,0 should be 9");
        System.out.println("\nModified:\n" + expandedGrid.report() + "\n");

    }


    @Test
    void stripNonDigits()
    {
        ExpandedGrid expandedGrid = getExpandedGridFromResourceFile();
        var result = expandedGrid.gameDigitFilter("0 1 2 3 4asdf,sdnfewijeawofi 5678 9 xX xx");
        assertEquals("0123456789XXXX", result,"filtering game characters");
    }

}
