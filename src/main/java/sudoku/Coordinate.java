package sudoku;

/** 
*Class to hold two coordinates for 2D area 
*/
class Coordinate
{
	public int x; 
	public int y;
	
	public Coordinate(){}
	
	/** 
	*	Constructor
	*	@param y Coordinate y
	*	@param x Coordinate x.
	*/
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object other){
		Coordinate cord = (Coordinate) other;
		boolean vienodi = false;
		
		if((x == cord.x) && (y == cord.y)){
			vienodi = true;
		}
		
		return vienodi;
	}
	
	public String toString(){
		return x + ";" + y;
	}
};