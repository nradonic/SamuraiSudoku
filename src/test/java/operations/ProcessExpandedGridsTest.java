package operations;

import data_structures.ExpandedGrid;
import org.junit.jupiter.api.Test;

class ProcessExpandedGridsTest
{

    private ExpandedGrid getExpandedGridFromResourceFile()
    {
        ExpandedGrid expandedGrid = ExpandedGridIO.loadBoardFromResources("wapo_5_1_21x21.sudoku");
        return expandedGrid;
    }



    @Test
    void testProcessResourceFile()
    {
        ExpandedGrid expandedGrid = getExpandedGridFromResourceFile();
        System.out.println(expandedGrid.report());
        ProcessExpandedGrids.processSudokus(expandedGrid);
        System.out.println(expandedGrid.report());

    }
}
