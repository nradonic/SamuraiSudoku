package data_structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidityCheckTest
{

    @Test
    void fillGridAll()
    {
        Block3x3 block3x3 = new Block3x3();
        assertEquals("Valid", block3x3.markCell(0, 0, 0, 1, 7, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(0, 0, 0, 2, 2, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(0, 1, 0, 1, 1, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(0, 2, 0, 0, 5, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(0, 2, 0, 1, 4, CellStatus.fixed), "7");

        assertEquals("Valid", block3x3.markCell(0, 0, 1, 0, 1, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(0, 2, 1, 2, 7, CellStatus.fixed), "7");

        assertEquals("Valid", block3x3.markCell(0, 0, 2, 2, 5, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(0, 1, 2, 0, 2, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(0, 1, 2, 2, 7, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(0, 2, 2, 0, 8, CellStatus.fixed), "7");

        assertEquals("Valid", block3x3.markCell(1, 0, 0, 0, 9, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(1, 0, 0, 1, 3, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(1, 1, 0, 1, 7, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(1, 2, 0, 1, 5, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(1, 2, 0, 2, 1, CellStatus.fixed), "7");

        assertEquals("Valid", block3x3.markCell(1, 1, 1, 0, 6, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(1, 1, 1, 2, 2, CellStatus.fixed), "7");

        assertEquals("Valid", block3x3.markCell(1, 0, 2, 0, 4, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(1, 0, 2, 1, 2, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(1, 1, 2, 1, 5, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(1, 2, 2, 1, 9, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(1, 2, 2, 2, 6, CellStatus.fixed), "7");


        assertEquals("Valid", block3x3.markCell(2, 0, 0, 2, 3, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(2, 1, 0, 0, 7, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(2, 1, 0, 2, 4, CellStatus.fixed), "7");

        assertEquals("Valid", block3x3.markCell(2, 0, 1, 1, 8, CellStatus.fixed), "7");

        assertEquals("Valid", block3x3.markCell(2, 0, 2, 1, 9, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(2, 0, 2, 2, 4, CellStatus.fixed), "7");
        assertEquals("Valid", block3x3.markCell(2, 1, 2, 1, 3, CellStatus.fixed), "7");

        String k = block3x3.reportBlock3x3();
        String l = k.replace("P123456789", "  ");
        System.out.println("\n" + k + "\n");

        System.out.println(l + "\n");

        assertTrue(ValidityCheck.isDigitFound(block3x3, 0, 0, 0, 0, 7));
        assertFalse(ValidityCheck.isDigitFound(block3x3, 0, 0, 0, 0, 6));

    }

}
