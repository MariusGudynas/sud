package sudoku;

import java.util.Scanner;
import java.io.File;
import java.io.FilenameFilter;

public class App 
{
	public static void main( String[] args )
	{
		File directoryPath = new File(".");
		FilenameFilter textFilefilter = new FilenameFilter(){
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".csv")) {
					return true;
				} else {
					return false;
				}
			}
		};
		
		String filesList[] = directoryPath.list(textFilefilter);
		int maxFileNumber = filesList.length;
		
		System.out.println("Pasirinkti faila 0-" + (maxFileNumber-1));
		for(int i =0; i< maxFileNumber; i++){
			System.out.println("[" + i + "] " + filesList[i]);
		}		
		
		String str;
		String file_name = "0";
		String solve_method = "0";
		
		int input = 0;
		
		Scanner in = new Scanner(System.in);
		while(file_name == "0"){
			
			str= in.nextLine();
			input = Integer.parseInt( str );
			
			if(checkInput(str, maxFileNumber)){
				file_name = filesList[input];
			}
		}
		
		Board board = new Board(file_name);
		
		board.writeBoardToConsole();
		
		while(solve_method == "0"){
			System.out.println("Select solving method:");
			System.out.println("[1] Simple");
			System.out.println("[2] Complex");
			str= in.nextLine();
			
			if(checkInput(str, 3)){
				input = Integer.parseInt( str );
			}
			
			switch(input) {
				case 1:
					board.solveSimpleMethod();
					solve_method = "simple";
				break;
				case 2:
					board.solveComplexMethod();
					solve_method = "complex";
				break;
			}
		}
		
		board.writeBoardToConsole();
		System.out.println(board.recursion);
		System.out.println("Solved " +  file_name + " with " + solve_method + " method");		
		
	}
	
	public static boolean checkInput(String input, int maxNumber){
		int option;
		
		try {
			option = Integer.parseInt( input );
		}
		catch( NumberFormatException e ) {
			System.out.println("Turi buti ivestas skaicius");
			return false;
		}			
		
		if(option > -1 && option < maxNumber){
			return true;
		}
		System.out.println("Pasirinkimas neteisingas nerastas pasirinkite nuo 0 iki " + (maxNumber-1));
		return false;
	}
}
