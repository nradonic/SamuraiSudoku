package data_structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmallBlockTest
{

    @Test
    void printSmallBlock()
    {
        SmallBlock k = new SmallBlock();
        String expected = "P123456789-P123456789-P123456789\n" +
                "P123456789-P123456789-P123456789\n" +
                "P123456789-P123456789-P123456789\n";
        assertEquals(expected, k.reportSmallBlock(), "small block comparison initialized");
    }

    @Test
    void printInitializedFixedSmallBlock()
    {
        SmallBlock k = new SmallBlock();
        k.setCell(0, 0, 1, CellStatus.fixed);
        String expected = "F1-P123456789-P123456789\n" +
                "P123456789-P123456789-P123456789\n" +
                "P123456789-P123456789-P123456789\n";
        assertEquals(expected, k.reportSmallBlock(), "small block comparison Fixed");
    }

    @Test
    void printInitializedCalculatedSmallBlock()
    {
        SmallBlock k = new SmallBlock();
        k.setCell(2, 1, 6, CellStatus.calculated);
        String expected = "P123456789-P123456789-P123456789\n" +
                "P123456789-P123456789-P123456789\n" +
                "P123456789-C6-P123456789\n";
        assertEquals(expected, k.reportSmallBlock(), "small block comparison Calculated");
    }

    @Test
    void isValidSmallBlockTrue()
    {
        SmallBlock k = new SmallBlock();
        assertEquals("Valid", k.setCell(0, 0, 1, CellStatus.fixed));
        assertEquals("Valid", k.setCell(0, 1, 2, CellStatus.fixed));
        assertEquals("Valid", k.setCell(0, 2, 3, CellStatus.fixed));
        assertEquals("Valid", k.setCell(1, 0, 4, CellStatus.fixed));
        assertEquals("Valid", k.setCell(1, 1, 5, CellStatus.fixed));
        assertEquals("Valid", k.setCell(1, 2, 6, CellStatus.fixed));
        assertEquals("Valid", k.setCell(2, 0, 7, CellStatus.fixed));
        assertEquals("Valid", k.setCell(2, 1, 8, CellStatus.fixed));
        assertEquals("Valid", k.setCell(2, 2, 9, CellStatus.fixed));

    }


    @Test
    void isValidSmallBlockFalse()
    {
        SmallBlock k = new SmallBlock();
        assertEquals("Valid", k.setCell(0, 0, 1, CellStatus.fixed));
        assertEquals("Valid", k.setCell(0, 1, 2, CellStatus.fixed));
        assertEquals("Valid", k.setCell(0, 2, 3, CellStatus.fixed));
        assertEquals("Valid", k.setCell(1, 0, 4, CellStatus.fixed));
        assertEquals("Valid", k.setCell(1, 1, 5, CellStatus.fixed));
        assertEquals("Valid", k.setCell(1, 2, 6, CellStatus.fixed));
        assertEquals("Valid", k.setCell(2, 0, 7, CellStatus.fixed));
        assertEquals("Valid", k.setCell(2, 1, 8, CellStatus.fixed));
        assertEquals("Valid", k.setCell(2, 2, 9, CellStatus.fixed));
        assertEquals("Invalid", k.setCell(0, 0, 2, CellStatus.fixed));

        String expected = "F1-F2-F3\n" +
                "F4-F5-F6\n" +
                "F7-F8-F9\n";

        assertEquals(expected, k.reportSmallBlock());

        System.out.println(k.reportSmallBlock());

    }
}
