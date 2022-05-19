package data_structures;

import operations.ExpandedGridIO;
import operations.GridToBox;
import operations.PromoteFixedValues;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
        assertTrue(expandedGrid.assertValid());

        Integer[][] blockPositions = expandedGrid.getblockPositions();
        int boards = blockPositions.length;

        Block3x3[] sudokus = new Block3x3[boards];

        extractSudokus(expandedGrid, blockPositions, sudokus);
        boolean collectedChanges = true;
        while (collectedChanges)
        {
            collectedChanges = false;
            for (int i = 0; i < boards; i++)
            {
                boolean boardChange = true;
                System.out.println("\nCoords: " + blockPositions[i][0] + "  " + blockPositions[i][1] + "\n" + sudokus[i].reportBlock3x3() + "\n\n");
                while (boardChange)
                {
                    boardChange = PromoteFixedValues.promoteProbableSingles(sudokus[i]);
                    if (boardChange)
                    {
                        collectedChanges = true;
                    }
                }
                if (collectedChanges)
                {
                    System.out.println("\nCoords: " + blockPositions[i][0] + "  " + blockPositions[i][1] + "\n" + sudokus[i].reportBlock3x3() + "\n");
                }
            }
            propagateCalculatedValuesToExpandedGrid(expandedGrid, boards, blockPositions, sudokus);
            System.out.println("*******************  Next Loop  *******************");
        }
    }

    private void propagateCalculatedValuesToExpandedGrid(ExpandedGrid expandedGrid, int boards, Integer[][] blockPositions, Block3x3[] sudokus)
    {
        for (int board = 0; board<boards;board++)
        {
            System.out.println(sudokus[board].reportBlock3x3());
            var x = sudokus[board].changesList();
            for (int j = 0; j<x.size();j++){
                System.out.println(); //TBD print out list of changes....and metadata
            }
            int a=1;
        }
    }

    private void extractSudokus(ExpandedGrid expandedGrid, Integer[][] blockPositions, Block3x3[] sudokus)
    {
        Integer[] coords;
        for (int i = 0; i < blockPositions.length; i++)
        {
            coords = blockPositions[i];
            sudokus[i] = GridToBox.extractBlockFromGrid(expandedGrid, coords[0], coords[1]);
            System.out.println("Coords: " + coords[0] + ":" + coords[1] + "\n" + sudokus[i].reportBlock3x3() + "\n\n");
        }
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


}
