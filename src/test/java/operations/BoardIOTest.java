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
        assertEquals("P123456789-F7-F2-P123456789-F1-P123456789-F5-F4-P123456789\n" +
                "F1-P123456789-P123456789-P123456789-P123456789-P123456789-P123456789-P123456789-F7\n" +
                "P123456789-P123456789-F5-F2-P123456789-F7-F8-P123456789-P123456789\n" +
                "F9-F3-P123456789-P123456789-F7-P123456789-P123456789-F5-F1\n" +
                "P123456789-P123456789-P123456789-F6-P123456789-F2-P123456789-P123456789-P123456789\n" +
                "F4-F2-P123456789-P123456789-F5-P123456789-P123456789-F9-F6\n" +
                "P123456789-P123456789-F3-F7-P123456789-F4-P123456789-P123456789-P123456789\n" +
                "P123456789-F8-P123456789-P123456789-P123456789-P123456789-P123456789-P123456789-P123456789\n" +
                "P123456789-F9-F4-P123456789-F3-P123456789-P123456789-P123456789-P123456789", block3x3.reportBlock3x3());
    }

    @Disabled
    @Test
    void manualFileLoadTest()
    {
        Block3x3 block3x3 = BoardIO.loadBoard();

        System.out.print("File Loaded: \n" + block3x3.reportBlock3x3ShortFormat());
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
