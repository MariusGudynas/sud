package sudoku;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;
import java.io.*;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	int[][] rows = {
				{6, 0, 0, 0, 9, 0, 0, 0, 5},
				{0, 9, 0, 2, 0, 0, 6, 0, 0},
				{8, 0, 0, 4, 0, 0, 3, 2, 0},
				{9, 0, 0, 7, 3, 0, 1, 0, 0},
				{0, 0, 0, 6, 0, 1, 0, 0, 0},
				{0, 0, 6, 0, 8, 5, 0, 0, 3},
				{0, 8, 9, 0, 0, 2, 0, 0, 7},
				{0, 0, 5, 0, 0, 6, 0, 9, 0},
				{7, 0, 0, 0, 1, 0, 0, 0, 2}
			};
			
	int[][] columns = {
				{6, 0, 8, 9, 0, 0, 0, 0, 7},
				{0, 9, 0, 0, 0, 0, 8, 0, 0},
				{0, 0, 0, 0, 0, 6, 9, 5, 0},
				{0, 2, 4, 7, 6, 0, 0, 0, 0},
				{9, 0, 0, 3, 0, 8, 0, 0, 1},
				{0, 0, 0, 0, 1, 5, 2, 6, 0},
				{0, 6, 3, 1, 0, 0, 0, 0, 0},
				{0, 0, 2, 0, 0, 0, 0, 9, 0},
				{5, 0, 0, 0, 0, 3, 7, 0, 2}
			};
			
	int[][] squares = {
				{6, 0, 0, 0, 9, 0, 8, 0, 0},
				{0, 9, 0, 2, 0, 0, 4, 0, 0},
				{0, 0, 5, 6, 0, 0, 3, 2, 0},
				{9, 0, 0, 0, 0, 0, 0, 0, 6},
				{7, 3, 0, 6, 0, 1, 0, 8, 5},
				{1, 0, 0, 0, 0, 0, 0, 0, 3},
				{0, 8, 9, 0, 0, 5, 7, 0, 0},
				{0, 0, 2, 0, 0, 6, 0, 1, 0},
				{0, 0, 7, 0, 9, 0, 0, 0, 2}
			};
			
	int[][] solved = {
				{6, 4, 2, 1, 9, 3, 7, 8, 5},
				{3, 9, 7, 2, 5, 8, 6, 1, 4},
				{8, 5, 1, 4, 6, 7, 3, 2, 9},
				{9, 2, 8, 7, 3, 4, 1, 5, 6},
				{5, 7, 3, 6, 2, 1, 9, 4, 8},
				{4, 1, 6, 9, 8, 5, 2, 7, 3},
				{1, 8, 9, 3, 4, 2, 5, 6, 7},
				{2, 3, 5, 8, 7, 6, 4, 9, 1},
				{7, 6, 4, 5, 1, 9, 8, 3, 2}
			};
			
	private Board board  = new Board();

	@Test
	public void squareCalculationTest(){
		
		
		assertEquals ( "Square number at board position 5;5", 4,  board.findSquareNumber(5, 5));
		assertEquals ( "Square number at board position 5;5", 8,  board.findSquarePosition(5, 5) );
		
		assertNotSame ( "Square number at board position 5;5", 5, board.findSquareNumber(5, 5));
		
		
	}
	
	@Test
	public void copyRowsToColumnsTest(){
		board.rows = rows;
		board.copyRowsToColumns();
		
		for(int i = 0; i < 9; i++){
			assertArrayEquals(board.columns[i], columns[i]);
		}
	}
	
	@Test
	public void copyRowsToSquaresTest(){
		board.rows = rows;
		board.copyRowsToSquares();
		
		for(int i = 0; i < 9; i++){
			assertArrayEquals(board.squares[i], squares[i]);
		}
	}
	
	@Test
	public void numberInsertionTest(){
		board.rows = rows;
		board.columns = columns;
		board.squares = squares;
		
		board.fillNumber(4, 2, 9);
		
		assertEquals ( "row number 4;2 should be 9", 9,  board.rows[4][2]);
		assertEquals ( "column number 2;4 should be 9", 9,  board.columns[2][4]);
		assertEquals ( "square number 2;4 should be 9", 9,  board.squares[3][5]);
	}
	
	@Test
	public void findClosestEmptyTest(){
		board.rows = rows;
		
		Coordinate to_check  = board.findEmptySpot();
		Coordinate correct = new Coordinate(1,0);
		
		assertEquals( "Next emtpy should be 1;0", correct, to_check);
	}
	
}
