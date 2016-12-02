package core;

/** Clase principal donde se corre todo
 * TODO
 * [] recibe como argumento el nombre del archivo de texto
 * [x] se inicializa la pluma y el tablero
 * [] stack de operaciones (?) 
 * [] hacer correr el parser (flex y cup)
 * [] mostrar
 * **/

public class Driver {

	public static void main (String [] args){
		Tablero t= new Tablero();
		Pluma p= new Pluma();
		p.setBoard(t);
	}
}
