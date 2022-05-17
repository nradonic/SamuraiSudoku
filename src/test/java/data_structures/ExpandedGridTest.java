package data_structures;

import operations.ExpandedGridIO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpandedGridTest
{

    @Test
    void loadGridFromResourceFile()
    {
        ExpandedGrid expandedGrid = ExpandedGridIO.loadBoardFromResources("wapo_5_1_21x21.sudoku");
        assertEquals("0", expandedGrid.reportCell(0, 0));
        assertEquals("7", expandedGrid.reportCell(0, 1));
        System.out.println(expandedGrid.report());

    }


    @Disabled
    @Test
    void manualLoadExpandedGridFromFile()
    {
        ExpandedGrid expandedGrid = ExpandedGridIO.loadExpandedGrid();
        System.out.println("\nExpanded Grid: " + "\n" + expandedGrid.report() + "\n");
        expandedGrid.bumpRevision();
        System.out.println("\nExpanded Grid: " + "\n" + expandedGrid.report() + "\n");

    }


}
