package operations;

import data_structures.Block3x3;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardIOTest
{
    @Test
    void loadBoard()
    {
        String fileName = "start1.sudoku";
        Block3x3 block3x3 = BoardIO.loadBoardFromResources(fileName);

        assert (block3x3 != null);
        assertEquals("P  3 56  9 - F      7   - F 2        - P  3 5   9 - F1         - P  3 56  9 - F       8  - F   4      - P  3 56  9\n" +
                "P1 3 56 89 - P1  456 8  - P1  456 89 - P 2345  89 - P 23456 89 - P  3 56789 - P123 567 9 - P123 567   - P 23 567 9\n" +
                "P1 3 56 89 - P1  456 8  - P1  456 89 - P 2345  89 - P 23456 89 - P  3 56789 - P123 567 9 - P123 567   - P 23 567 9\n" +
                "P12  56789 - P12  56 8  - P1   56789 - P1 345  89 - P  345  89 - P1 3 5  89 - P 234567   - P 23 5678  - P 2345678 \n" +
                "F   4      - F  3       - P    5  8  - F     6    - F      7   - F 2        - P    5     - F        9 - F1        \n" +
                "P12  56789 - P12  56 8  - P1   56789 - P1 345  89 - P  345  89 - P1 3 5  89 - P 234567   - P 23 5678  - P 2345678 \n" +
                "P12  5678  - P12 456 8  - P1  45678  - P123 5  89 - P 23 56 89 - P1 3 56 89 - P1234567 9 - P123 5678  - P 23456789\n" +
                "P12  5678  - P12 456 8  - P1  45678  - P123 5  89 - P 23 56 89 - P1 3 56 89 - P1234567 9 - P123 5678  - P 23456789\n" +
                "P12  56 8  - F        9 - F  3       - F      7   - P 2  56 8  - F   4      - P12  56    - P12  56 8  - P 2  56 8 ", block3x3.reportBlock3x3());
    }

    @Disabled
    @Test
    void manualFileLoadTest()
    {
        Block3x3 block3x3 = BoardIO.loadBoard();

        System.out.print("File Loaded: \n" + block3x3.reportBlock3x3() + "\n");
    }


    @Test
    void saveBoardToTempDirectory()
    {
        String fileName = "start1.sudoku";
        Block3x3 block3x3 = BoardIO.loadBoardFromResources(fileName);

        String writeFileName = "/tmp/testWrite.sudoku";
        BoardIO.saveBoard(block3x3, writeFileName);

        Block3x3 block3x3_1 = BoardIO.loadBoardFromFile(writeFileName);
        assertEquals(block3x3.reportBlock3x3(), block3x3_1.reportBlock3x3(),"block compare write -- read");
    }
}
