package data_structures;

import operations.ExpandedGridIO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpandedGridTest
{

    @Test
    void loadGridFromResourceFile()
    {
        ExpandedGrid expandedGrid = ExpandedGridIO.loadBoardFromResources("wapo_5_1_21x21.sudoku");
        assertTrue("0".equals(expandedGrid.reportCell(0,0)));
        assertTrue("7".equals(expandedGrid.reportCell(0,1)));


    }
}
