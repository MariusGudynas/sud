package sudoku;

import java.util.Arrays;

/**
*	Class to calculate how many times certain numbers appear in arrays
*/
public class FilledNumbers{
	
	int[] numbers;														//Array to hold how many times numbers repeat
	int empty_number_count = 0;											// Holds how many numbers do not repeat
	
	FilledNumbers(){
		numbers = new int[10];
	}
	
	FilledNumbers(int max_num){
		numbers = new int[max_num];
	}
	
	/** 
	*	Resets all numbers to 0 and empty number count to 0
	*/
	public void resetNumbers(){
		Arrays.fill(numbers, 0);
		empty_number_count = 0;
	}
	
	/** 
	*	Takes an array and fills how many times numbers appear in given array
	*	@param Given array used as reference
	*/
	public void fillNumbersByArray(int[] line){
		for(int i = 0; i < line.length; i++){									//Go through an entire array
			numbers[line[i]]++;											//Fill a coresponding spot in the array by given element
		}
	}
	
	/** 
	*	Finds out which single number does not appear in the array
	*	@return which number does not appear in the arrays, if mutiple numbers return 0
	*/
	public int checkForSingleNumber(){
		int empty_number = 0;
		
		for(int i = 1; i < numbers.length; i++){
			if(numbers[i] == 0){
				empty_number_count++;
				empty_number = i;
			}
		}
		
		if(empty_number_count == 1){
			return empty_number;
		}else{
			return 0;
		}
		
	}
	
	/** 
	*	Finds out how many unused numbers appear in the array
	*	@return amount of unused numbers
	*/
	public int countUnusedNumbers(){
		int empty_number = 0;
		
		for(int i = 1; i < numbers.length; i++){
			if(numbers[i] == 0){
				empty_number++;
			}
		}
		return empty_number;
	}
	
	/** 
	*	Creates a list of unused numbers
	*	@return an array of numbers that do not appear in the held array
	*/
	public int[] returnUnusedNumbers(){
		int[] freeNumbers = new int[countUnusedNumbers()];
		int count = 0;
		
		for(int i =1; i< numbers.length; i++){
			if(numbers[i] == 0){
				freeNumbers[count] = i;
				count++;
			}
		}
		return freeNumbers;
	}
}