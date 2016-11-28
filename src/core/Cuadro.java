package core;

public class Cuadro {
	private char c; //COLOR puede ser objeto de Color (?)
	private int i; //posx
	private int j; //posi
	private boolean esBorde;
	
	/** TODO 
	 * [x] inicializar: default color Blanco
	 * [x] gets de coordenadas y color
	 * [x] cambiar color
	 * [x] tipo borde (Se podria hacer una subclase en vez de un booleano(?))
	 * **/
	
	Cuadro(int x,int y){
		c = 'B';// parte en blanco
		i = x+1;
		j = y+1;
		esBorde();
	}
	
	public char getcol(){
		return c;
	}
	
	public int getposx(){
		return i;
	}
	
	public int getposy(){
		return j;
	}
	
	public void col(char color){
		c=color;
	}
	
	public boolean borde(){
		return esBorde;
	}

	private void esBorde(){
		if(i==1 || i==50 || j==1 || j==50){
			esBorde = true;
		} else {
			esBorde = false;
		}
		
	}
}
