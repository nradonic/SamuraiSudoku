package data_structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SudokuCellTest
{

    @Test
    void printCellInitial()
    {
        SudokuCell sudokuCell = new SudokuCell();
        assertEquals("P123456789", sudokuCell.report());
    }

    @Test
    void printCellFixed()
    {
        SudokuCell sudokuCell = new SudokuCell();
        sudokuCell.markDigit(3, CellStatus.fixed);
        assertEquals("F  3      ", sudokuCell.report());
    }

    @Test
    void printCellCalculated()
    {
        SudokuCell sudokuCell = new SudokuCell();
        sudokuCell.markDigit(5, CellStatus.calculated);
        assertEquals("C    5    ", sudokuCell.report());
    }

    @Test
    void printCellNotPossible()
    {
        SudokuCell sudokuCell = new SudokuCell();
        sudokuCell.markDigit(7, CellStatus.notpossible);
        assertEquals("P123456 89", sudokuCell.report());
    }

    @Test
    void printCellError()
    {
        SudokuCell sudokuCell = new SudokuCell();
        sudokuCell.markDigit(7, CellStatus.fixed);
        sudokuCell.markDigit(7, CellStatus.notpossible);

        assertEquals("Error no fixed, calculated, or possible cell value", sudokuCell.report());
    }


    @Test
    void scoreCell()
    {
        SudokuCell sudokuCell = new SudokuCell();
        sudokuCell.markDigit(7, CellStatus.notpossible);

        assertEquals("12345689", sudokuCell.scoreCell().digits);
    }

    @Test
    void compareCells()
    {
        SudokuCell sudokuCell1 = new SudokuCell();
        sudokuCell1.markDigit(7, CellStatus.fixed);

        SudokuCell sudokuCell2 = new SudokuCell();
        sudokuCell2.markDigit(6, CellStatus.fixed);

        assertEquals(0, sudokuCell1.compareTo(sudokuCell1), "Should match");
        assertEquals(0, sudokuCell1.compareTo(sudokuCell2), "Should match");

        SudokuCell sudokuCell3 = new SudokuCell();
        sudokuCell3.markDigit(5, CellStatus.notpossible);
        assertEquals(1, sudokuCell1.compareTo(sudokuCell3), "Fixed bigger than possible");

        SudokuCell sudokuCell4 = new SudokuCell();
        sudokuCell4.markDigit(4, CellStatus.calculated);
        assertEquals(1, sudokuCell1.compareTo(sudokuCell4), "Fixed bigger than calculated");
        assertEquals(1, sudokuCell4.compareTo(sudokuCell3), "Calculated bigger than possible");
        assertEquals(-1, sudokuCell4.compareTo(sudokuCell1), "Fixed bigger than calculated");


        SudokuCell sudokuCell5 = new SudokuCell();
        sudokuCell5.markDigit(7, CellStatus.notpossible);
        sudokuCell5.markDigit(8, CellStatus.notpossible);
        sudokuCell5.markDigit(9, CellStatus.notpossible);
        sudokuCell5.markDigit(3, CellStatus.notpossible);

        assertEquals(-1, sudokuCell3.compareTo(sudokuCell5), "Possible 6 digits  bigger than Possible 9 digits");

        Score score5 = sudokuCell5.scoreCell();
        assertEquals(CellStatus.possible,score5.cellStatus,"Possible");
        assertEquals("12456",score5.getDigits(),"Possible digits");

        int a=1;
    }

}
