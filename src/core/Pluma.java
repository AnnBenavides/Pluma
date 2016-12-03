package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Damian Ibarra Z.
 *
 */
public class Pluma {
	private int posx; 		//x axis position
	private int posy; 		//y axis position
	private char col; 	//color (A/R/V/B/N)
	private char dir;		//direction (S/N/E/O)
	private String mod;		//mode (arriba/abajo)

	//Since the viable colors depend merely on the quill,
	//there's no need to create a class for colors.
	//Even though, it could be created.
	private final List<Character> validColor = new ArrayList<Character>(Arrays.asList('A', 'R', 'V', 'B', 'N'));
	private final List<Character> validDir = new ArrayList<Character>(Arrays.asList('S', 'N', 'E', 'O'));
	private final List<String> validModes = new ArrayList<String>(Arrays.asList("arriba", "abajo"));
	
	private Tablero board;	//the board in which the quill's painting
	
	public Pluma() {
		setPosition(1, 1);
		setMode("arriba");
		setColor('B');
		setDirection('N');
	}
	
	/**
	 * Use methods of Tablero class
	 * to get the dimensions of the grid
	 * getMaxX() & getMaxY()
	 * 
	 * @return		True if the position is on the board
	 * 				False if it's off the board
	 * **/
	private boolean checkValidPosition(int x, int y) {
		if (x > board.getMaxX() | x < 1 | y > board.getMaxY() | y < 0){
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Moves the quill according to its own variables.
	 * 
	 * @param distance		Amount of squares the quill will move.
	 * 
	 * @return			True if the quill was able to move.
	 * 					False otherwise.
	 */
	public boolean move(int distance) {
		// NullObject Pattern???
		if (board == null) {
			return false;
		}
		
		if (dir == 'N') {
			setPosition(posx, posy + distance);
		} else if (dir == 'S') {
			setPosition(posx, posy - distance);
		} else if (dir == 'E') {
			setPosition(posx + distance, posy);
		} else if (dir == 'O') {
			setPosition(posx - distance, posy);
		}
		
		if (!checkValidPosition(posx, posy)) {
			return false;
		}
		
		if (mod.equals("abajo")) {
			board.col(posx, posy, col);
		}
		
		return true;
	}
	
	/**
	 * Sets new position for the quill.
	 * 
	 * @param x		New position in board's x axis.
	 * @param y		New position in board's y axis.
	 */
	public boolean setPosition(int x, int y) {
		posx = x;
		posy = y;
		return true;
	}
	
	/**
	 * Sets quill's mode (up or down).
	 * 
	 * @param newMode						Has to be either "arriba" or "abajo"
	 * 
	 * @throws IllegalArgumentException		When given argument isn't one of the two valid options.
	 */
	public boolean setMode(String newMode) throws IllegalArgumentException {
		if (!validModes.contains(newMode)) {
			throw new IllegalArgumentException("Mode has to be either 'arriba' or 'abajo'");
		}
		if (!newMode.equals(mod)) {
			mod = newMode;
			if (mod.equals("abajo")) {
				board.col(posx, posy, col);
			}
		}
		return true;
	}
	
	/**
	 * Sets quill's color
	 * 
	 * @param color							Quill's color. Has to be either R, V, A, B, or N.
	 * @throws IllegalArgumentException		When given an invalid color as argument.
	 */
	public boolean setColor(char color) throws IllegalArgumentException {
		if (!validColor.contains(color)) {
			throw new IllegalArgumentException("Invalid color.\n"
					+ "Should be either R, V, A, B or N.");
		}
		col = color;
		return true;
	}
	
	/**
	 * Sets quill's direction for movement.
	 * 
	 * @param direction						Quill's direction. Has to be either S, N, E or O.
	 * @throws IllegalArgumentException		When given an invalid direction as argument.
	 */
	public boolean setDirection(char direction) throws IllegalArgumentException {
		if (!validDir.contains(direction)) {
			throw new IllegalArgumentException("Invalid direction.\n"
					+ "Should be either S, N, E, or O.");
		}
		dir = direction;
		return true;
	}
	
	/**
	 * Sets the board over which the quill will draw.
	 * 
	 * @param newBoard		The board of class "Tablero".
	 * 						Once set, can't be changed.
	 */
	public void setBoard(Tablero newBoard) {
		//NullPattern?? (?)
		if (board != null) {
			return;
		}
		board = newBoard;
	}
	
	/**
	 * Gets quill's position in x axis.
	 * 
	 * @return	Quill's current position in board's x axis.
	 */
	public int getPosX() {
		return posx;
	}
	
	/**
	 * Gets quill's position in y axis.
	 * 
	 * @return	Quill's current position in board's y axis.
	 */
	public int getPosY() {
		return posy;
	}
	
	/**
	 * Gets quill's direction.
	 * 
	 * @return	Quill's direction. Has to be either S, N, E or O.
	 */
	public char getDirection() {
		return dir;
	}
	
	/**
	 * Gets quill's color.
	 * 
	 * @return	Quill's color. Has to be either R, V, A, B, or N.
	 */
	public char getColor() {
		return col;
	}
	
	/**
	 * Gets quill's mode.
	 * 
	 * @return	A string representing quill's mode ("arriba" or "abajo")
	 */
	public String getMode() {
		return mod;
	}

	public void P(int i, int j, char c, String m, char d){
		// TODO execute all orders needed to get the given set of parameters
	}
}
