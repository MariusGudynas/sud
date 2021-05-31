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
 public class SystemTest
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
	public void solveSimpleTest(){
		board.rows = rows;
		board.columns = columns;
		board.squares = squares;
		board.solveSimpleMethod();
		
		for(int i = 0; i < 9; i++){
			assertArrayEquals(board.rows[i], solved[i]);
		}
	}
	
	@Test
	public void solveComplexTest(){
		board.rows = rows;
		board.columns = columns;
		board.squares = squares;
		board.solveComplexMethod();
		
		for(int i = 0; i < 9; i++){
			
			assertArrayEquals("Wrong senquence in row " + i, board.rows[i], solved[i]);
		}
	}
 }