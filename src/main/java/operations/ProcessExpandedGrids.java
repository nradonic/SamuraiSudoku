package operations;

import data_structures.Block3x3;
import data_structures.ExpandedGrid;

public class ProcessExpandedGrids
{
    public static void processSudokus(ExpandedGrid expandedGrid)
    {
//        System.out.println("Starting Values:\n" + expandedGrid.report());

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
//                System.out.println("\nCoords: " + blockPositions[i][0] + "  " + blockPositions[i][1] + "\n" + sudokus[i].reportBlock3x3() + "\n\n");
                while (boardChange)
                {
                    boardChange = PromoteFixedValues.promoteProbableSingles(sudokus[i]);
                    if (boardChange)
                    {
                        collectedChanges = true;
                    }
                }
//                if (collectedChanges)
//                {
//                    System.out.println("\nCoords: " + blockPositions[i][0] + "  " + blockPositions[i][1] + "\n" + sudokus[i].reportBlock3x3() + "\n");
//                }
            }
            System.out.println("*******************  Next Loop  *******************");
        }
        pushCalculationsIntoSourceArray(expandedGrid, blockPositions, sudokus);
    }

    private static void extractSudokus(ExpandedGrid expandedGrid, Integer[][] blockPositions, Block3x3[] sudokus)
    {
        Integer[] coords;
        for (int i = 0; i < blockPositions.length; i++)
        {
            coords = blockPositions[i];
            sudokus[i] = GridToBox.extractBlockFromGrid(expandedGrid, coords[0], coords[1]);
            System.out.println("Coords: " + coords[0] + ":" + coords[1] + "\n" + sudokus[i].reportBlock3x3() + "\n\n");
        }
    }

    private static void pushCalculationsIntoSourceArray(ExpandedGrid expandedGrid, Integer[][] blockPositions, Block3x3[] block3x3s)
    {
        int boards = blockPositions.length;
        for (int i = 0; i < boards; i++)
        {
            putSudokuIntoExpandedGrid(expandedGrid, blockPositions[i], block3x3s);
        }
    }

    private static void putSudokuIntoExpandedGrid(ExpandedGrid expandedGrid, Integer[] positions, Block3x3[] block3x3s)
    {
        for (int blockRow = 0; blockRow < 3; blockRow++)
        {
            for (int blockColumn = 0; blockColumn < 3; blockColumn++)
            {
                for (int row = 0; row < 3; row++)
                {
                    for (int column=0;column<3;column++){

                    }
                }
            }
        }
    }
}
