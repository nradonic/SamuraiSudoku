package operations;

import data_structures.ExpandedGrid;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpandedGridIOTest
{

    @Test
    void loadGridFromResourceFile()
    {
        ExpandedGrid expandedGrid = ExpandedGridIO.loadBoardFromResources("wapo_5_1_21x21.sudoku");
        assertTrue(expandedGrid.getRows() == 21, "21 rows");
        assertTrue(expandedGrid.getColumns() == 21, "21 columns");
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
