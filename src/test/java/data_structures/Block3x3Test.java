package data_structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Block3x3Test
{
    @Test
    void createBlock3x3()
    {
        String expected = "P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789\n" +
                "P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789\n" +
                "P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789\n" +
                "P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789\n" +
                "P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789\n" +
                "P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789\n" +
                "P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789\n" +
                "P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789\n" +
                "P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789 - P123456789";
        Block3x3 block3x3 = new Block3x3();
        assertEquals(expected, block3x3.reportBlock3x3());
        System.out.println(block3x3.reportBlock3x3() + "\n");
        System.out.println(block3x3.reportBlock3x3ShortFormat() + "\n");
    }


    @Test
    void fillGrid1()
    {
        Block3x3 block3x3 = new Block3x3();
        assertEquals("Valid",block3x3.markCell(0,0,0,1,7,CellStatus.fixed), "7");
        System.out.println(block3x3.reportBlock3x3() + "\n");
        System.out.println(block3x3.reportBlock3x3ShortFormat() + "\n");

    }

    @Test
    void fillGridAll()
    {
        Block3x3 block3x3 = new Block3x3();
        assertEquals("Valid",block3x3.markCell(0,0,0,1,7,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(0,0,0,2,2,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(0,1,0,1,1,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(0,2,0,0,5,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(0,2,0,1,4,CellStatus.fixed), "7");

        assertEquals("Valid",block3x3.markCell(0,0,1,0,1,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(0,2,1,2,7,CellStatus.fixed), "7");

        assertEquals("Valid",block3x3.markCell(0,0,2,2,5,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(0,1,2,0,2,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(0,1,2,2,7,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(0,2,2,0,8,CellStatus.fixed), "7");

        assertEquals("Valid",block3x3.markCell(1,0,0,0,9,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(1,0,0,1,3,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(1,1,0,1,7,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(1,2,0,1,5,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(1,2,0,2,1,CellStatus.fixed), "7");

        assertEquals("Valid",block3x3.markCell(1,1,1,0,6,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(1,1,1,2,2,CellStatus.fixed), "7");

        assertEquals("Valid",block3x3.markCell(1,0,2,0,4,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(1,0,2,1,2,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(1,1,2,1,5,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(1,2,2,1,9,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(1,2,2,2,6,CellStatus.fixed), "7");


        assertEquals("Valid",block3x3.markCell(2,0,0,2,3,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(2,1,0,0,7,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(2,1,0,2,4,CellStatus.fixed), "7");

        assertEquals("Valid",block3x3.markCell(2,0,1,1,8,CellStatus.fixed), "7");

        assertEquals("Valid",block3x3.markCell(2,0,2,1,9,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(2,0,2,2,4,CellStatus.fixed), "7");
        assertEquals("Valid",block3x3.markCell(2,1,2,1,3,CellStatus.fixed), "7");

        System.out.println(block3x3.reportBlock3x3() + "\n");
        assertEquals(true, block3x3.containsDefiniteDigitInRow(0, 0, 7),"Row 007" );
        assertEquals(true, block3x3.containsDefiniteDigitInColumn(1, 1, 3),"Column 113" );
        assertEquals(false, block3x3.containsDefiniteDigitInRow(0, 0, 9),"Row 007" );
        assertEquals(false, block3x3.containsDefiniteDigitInColumn(1, 1, 8),"Column 113" );
    }

}
