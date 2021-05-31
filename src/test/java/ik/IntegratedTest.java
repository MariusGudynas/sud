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
 public class IntegratedTest
 {
	int[] numbersToFil1l = {6,3,5,4,9,2,0,1,7};
	int[] numbersToFill2 = {6,3,5,4,9,0,0,0,7};
			
	private FilledNumbers numbers;
			
	@Test
	public void fillingAndCheckingForSingleTest(){
		numbers  = new FilledNumbers();
		
		numbers.fillNumbersByArray(numbersToFil1);
		
		assertEquals ( "Number should be 8", 8,  numbers.checkForSingleNumber());
	}
	
	@Test
	public void UnusedNumbersTest(){
		numbers  = new FilledNumbers();
		int[] correct = {1, 2, 8};
		
		numbers.fillNumbersByArray(numbersToFill2);

		assertEquals( "Number should be 8", 3,  numbers.countUnusedNumbers());
		
		assertArrayEquals( "Incorrect returned array", correct,  numbers.returnUnusedNumbers());
	}
	
	
	
	
	
 }