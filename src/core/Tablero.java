package core;

public class Tablero {
	private Cuadro[][] grid; 
	private int maxX=50;
	private int maxY=50;
	
	/** TODO 
	 * [x] Inicializar, default: 50x50
	 * [x] identificar borde
	 * [x] colorear cuadro
	 * [x] consultar color (?)
	 * [] deploy
	 * **/
	
	public Tablero(){
		grid= new Cuadro[maxX][maxY];
		for (int i = 0 ; i < maxX ; i++){
			for ( int j = 0 ; j < maxY ; j++){
				grid[i][j]= new Cuadro(i,j);
			}			
		}
	}
	
	public boolean borde(int x, int y){
		return grid[x][y].borde();
	}
	
	public char getCol(int x, int y){
		return grid[x][y].getcol();
	}
	
	public void col(int x, int y, char c){
		grid[x][y].col(c);
	}
	
	public String deploy(){
		StringBuilder str=new StringBuilder();
		
		for (int i = 1 ; i <= maxX ; i++){
			for (int j = 1 ; j <= maxY ; j++){
				str.append(getCol(i,j));
			}
			str.append('\n');		
		}
		return str.toString();
	}
	
	public int getMaxX(){
		return maxX;
	}
	
	public int getMaxY(){
		return maxY;
	}
}
