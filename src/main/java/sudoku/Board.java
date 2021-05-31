package sudoku;

import java.io.BufferedReader; 
import java.io.FileReader;	
import java.io.IOException;


public class Board{
	String file_name;
	
	public int[][] original;														//Original board that was given at startup in rows
	public int[][] rows;															//Board in the perspective of rows
	public int[][] columns;														//Board in the perspective of columns
	public int[][] squares;														//Board in the perspective of squares
	
	public int recursion = 0;
	
	/** Initializes board elements */
	public Board(){
		original = new int[9][9];													//Original board that was given at startup in rows
		rows = new int[9][9];													//Board in the perspective of rows
		columns = new int[9][9];													//Board in the perspective of columns
		squares = new int[9][9];													//Board in the perspective of squares
	}
	
	/** Initializes board elements and fills them using provided file
	*	@param file_name name of the file in directory to read from
	*/
	public Board(String file_name){
		original = new int[9][9];
		rows = new int[9][9];
		columns = new int[9][9];
		squares = new int[9][9];
		readDataFromFile(file_name);												//If file name was given read from it
	}
	
	
	/** Copy row elements to appropriate column places 
	*	@param file_name name of the file in directory to read from
	*/
	public void readDataFromFile(String file_name) {
		this.file_name = file_name;
		BufferedReader reader;
		
		String data_line = "";
		String[] data_array = new String[9];
		int row_count = 0;
		
		try {
			reader = new BufferedReader( new FileReader( file_name ) );
			
			while((data_line = reader.readLine()) != null){
				data_array =data_line.split(",");
				
				for(int i =0; i< data_array.length; i++){
					rows[row_count][i] = Integer.parseInt(data_array[i]);
				}
				row_count++;
			}
		}catch ( Exception e ) {
			e.printStackTrace();
		}
		
		copyFromRowsToOriginal();												//Copies rows to original
		copyRowsToColumns();													//Copies rows to columns
		copyRowsToSquares();													//Copies rows to squares
	}
	
	/** Copy row elements to appropriate column places */
	public void copyRowsToColumns(){
		for(int i =0; i< rows.length; i++){
			for(int j =0; j< rows.length; j++){
				columns[i][j]= rows[j][i];
			}
		}
	}
	
	/** Copies rows to original placement for later use */
	public void copyFromRowsToOriginal(){
		for(int i =0; i< rows.length; i++){
			for(int j =0; j< rows.length; j++){
				original[i][j]= rows[i][j];
			}
		}
	}
	
	/** Copy row elements to appropriate square places */
	public void copyRowsToSquares(){
		for(int i =0; i< rows.length; i++){
			for(int j =0; j< rows.length; j++){
				int squareNr = findSquareNumber(i, j);
				int squarePosition = findSquarePosition(i, j);
				squares[squareNr][squarePosition]= rows[i][j];
			}
		}
	}
	
	/** Calculates to which 3x3 square the coordinates belong to
	*	@param i Coordinate y.
	*	@param j Coordinate x.
	*/
	public int findSquareNumber(int i, int j){ 
		return j/3+(i/3)*3;
	}
	
	/** Calculates in which 3x3 square position the coordinates belong to
	*	@param i Coordinate y.
	*	@param j Coordinate x.
	*/
	public int findSquarePosition(int i, int j){
		return j%3+(i%3)*3;
	}
	
	/** Fills appropriate coordinate to all different perspective arrays
	*	@param row_number Coordinate y
	*	@param row_position Coordinate x.
	*	@param number Whole number to be filled in.
	*/
	public void fillNumber(int row_number, int row_position, int number){
		rows[row_number][row_position] = number;																//Fills row perspective
		columns[row_position][row_number] = number;																//Fills column perspective
		squares[findSquareNumber(row_number, row_position)][findSquarePosition(row_number, row_position)] = number;			//Fills square perspective
	}
	
	/** Outs whole board onto the screen. Original symbols are white, new symbols are red */
	public void writeBoardToConsole(){
		System.out.println ("╔═══════════╦═══════════╦═══════════╗");
		for(int i =0; i< 9; i++){
			
			for(int j =0; j< 9; j++){
				if(j%3 == 0){
					System.out.print("║");
				}else{
					System.out.print("│");
				}
				
				if(rows[i][j] != original[i][j]){ 																	//If number does not match origianl color it red
					
					System.out.print("\033[91m "+ rows[i][j] +" \033[0m");
				}else{																					//Else keep it white
					System.out.print(" " + rows[i][j] + " ");
				}
			}
			System.out.println("║");
			
			if((i+1)%3 == 0 && i != 8){
				System.out.println("╠═══════════╬═══════════╬═══════════╣");
			}else if(i != 8){
				System.out.println("║ ─   ─   ─ ║ ─   ─   ─ ║ ─   ─   ─ ║");
			}
		}
		System.out.println ("╚═══════════╩═══════════╩═══════════╝");
		System.out.println();
	}
	
	/** Tries to solve sudoku with a simple method, can't solve everything, but is quicker*/
	public void solveSimpleMethod(){
		FilledNumbers filled_numbers = new FilledNumbers();
		
		int filled_this_itteration = 1;												//How many numbers got filled this iteration
		int square_number = 0;													//Square in which the coordiante exists
		int missing_numbers = 0;													//Which number is missing from the spot
		
		while(filled_this_itteration != 0){											//If nothing was filled this iteration its pointless to continue
			filled_this_itteration = 0;												//Reset filled iteration count
			
			for(int i =0; i< 9; i++){
				for(int j =0; j< 9; j++){
					if(rows[i][j] == 0){
						recursion++;
						
						square_number = findSquareNumber(i, j);						//Find in which square the coordinates exist
						
						filled_numbers.fillNumbersByArray(rows[i]);					//Fills number array with row numbers
						filled_numbers.fillNumbersByArray(columns[j]);					//Fills number array with columns numbers
						filled_numbers.fillNumbersByArray(squares[square_number]);		//Fills number array with squares numbers
						
						missing_numbers = filled_numbers.checkForSingleNumber();		//Find out if only one number can be writen in the square 
						
						if(missing_numbers != 0){
							fillNumber(i, j, missing_numbers);						//Fill in the missing number
							filled_this_itteration++;								//Increase how many spots were filled this iteration
						}
						filled_numbers.resetNumbers();								//Reset number count back to 0				
					}	
				}
			}
		}
	}
	
	/** Solves sudoku with a complex method, can solve everything, but takes a lot of time*/
	public boolean solveComplexMethod(){
		FilledNumbers filled_numbers = new FilledNumbers();
		Coordinate coordinate = findEmptySpot();												//Finds an empty spot on board
		boolean solved = false;															//Sets solved state to false
		
		if(coordinate.x == -1){
			return true;																//If coordinate is not found that means that board is not empty thus solved
		}
		
		int square_number = findSquareNumber(coordinate.y, coordinate.x);							//Finds a square type coordinate for later use
		
		filled_numbers.fillNumbersByArray(rows[coordinate.y]);									//Fills number count with row numbers
		filled_numbers.fillNumbersByArray(columns[coordinate.x]);									//Fills number count with column numbers
		filled_numbers.fillNumbersByArray(squares[square_number]);								//Fills number count with square numbers
		
		int[] possibleNumbers = filled_numbers.returnUnusedNumbers();							//Find out how many numbers could be filled into the spot
		if(possibleNumbers.length == 0){
			return false;																//If there there are no available numbers go back and insert different number in previous space
		}
		
		for(int i =0; i< possibleNumbers.length; i++){
			fillNumber(coordinate.y, coordinate.x, possibleNumbers[i]);								//Fill a possible number into the board
			
			recursion++;																//Recursion counting number for statistics
			solved = solveComplexMethod();												//Initiate recursion to fill next empty spot and returns true in case the board gets solved
			
			if(solved){
				return true;															//Returns from recursion with solved board
			}
		}
		fillNumber(coordinate.y, coordinate.x, 0);												//In case the board was not solved but number was inserted it has to be reset to 0
		
		return false;																	//Return value that board was not solved to previous recursion
	}
	
	
	/** Tries to solve sudoku with a simple method, can't solve everything, but is quicker
	*	@return Coordinate element with x and y values corresponding to rows of spot with an empty element 0, if not found returns coordinate with -1 -1
	*/
	public Coordinate findEmptySpot(){
		Coordinate coordinate = new Coordinate();
		
		for(int i =0; i< 9; i++){
			for(int j =0; j< 9; j++){														//runs through whole board looking for  and returns its value
				if(rows[i][j] == 0){
					coordinate.y = i;
					coordinate.x = j;
					return coordinate;
				}
			}
		}
		coordinate.y = -1;
		coordinate.x = -1;
		return coordinate;																//If none is found returns -1 -1 position
	}
}