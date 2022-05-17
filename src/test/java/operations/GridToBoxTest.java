package operations;

import data_structures.Block3x3;
import data_structures.ExpandedGrid;
import org.junit.jupiter.api.Test;

class GridToBoxTest
{
    @Test
    void loadTopLEftBlock()
    {
        ExpandedGrid expandedGrid = ExpandedGridIO.loadBoardFromResources("wapo_5_1_21x21.sudoku");
        Block3x3 block3x3 = GridToBox.extractBlockFromGrid(expandedGrid, 0, 0);
        System.out.println("Top left corner:\n" + block3x3.reportBlock3x3());
    }
}
