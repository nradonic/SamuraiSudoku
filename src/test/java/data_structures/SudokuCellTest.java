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
        assertEquals("F3", sudokuCell.report());
    }

    @Test
    void printCellCalculated()
    {
        SudokuCell sudokuCell = new SudokuCell();
        sudokuCell.markDigit(5, CellStatus.calculated);
        assertEquals("C5", sudokuCell.report());
    }

    @Test
    void printCellNotPossible()
    {
        SudokuCell sudokuCell = new SudokuCell();
        sudokuCell.markDigit(7, CellStatus.notpossible);
        assertEquals("P12345689", sudokuCell.report());
    }

    @Test
    void printCellError()
    {
        SudokuCell sudokuCell = new SudokuCell();
        sudokuCell.markDigit(7, CellStatus.fixed);
        sudokuCell.markDigit(7, CellStatus.notpossible);

        assertEquals("Error no fixed, calculated, or possible cell value", sudokuCell.report());
    }
}
