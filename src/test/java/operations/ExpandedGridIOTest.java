package operations;

import data_structures.ExpandedGrid;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpandedGridIOTest
{

    @Test
    void loadGridFromResourceFile()
    {
        ExpandedGrid expandedGrid = ExpandedGridIO.loadBoardFromResources("wapo_5_1_21x21.sudoku");
        assertEquals(21, expandedGrid.getRows(), "21 rows");
        assertEquals(21, expandedGrid.getColumns(), "21 columns");
        assertTrue(expandedGrid.assertValid(), "expandedGrid validity test");
    }

    @Disabled
    @Test
    void manualFileLoadTest()
    {
        ExpandedGrid expandedGrid = ExpandedGridIO.loadExpandedGrid();

        assertTrue(expandedGrid.assertValid(), "assert valid expanded grid");
    }

}
