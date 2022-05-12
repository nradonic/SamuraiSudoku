package operations;

import data_structures.Block3x3;
import org.junit.jupiter.api.Test;

class PromoteFixedValuesTest
{
    @Test
    void promoteFixedValuesAcrossPossibles()
    {
        String fileName = "start1.sudoku";
        Block3x3 block3x3 = BoardIO.loadBoardFromResources(fileName);
        System.out.println("\nFile: " + fileName + "\n" + block3x3.reportBlock3x3() + "\n");

        boolean change = true;
        while (change)
        {
            change = PromoteFixedValues.promoteProbableSingles(block3x3);
            if (change)
            {
                System.out.println("\nSingles: " + fileName + "\n" + block3x3.reportBlock3x3() + "\n");
            }
        }
    }
}
