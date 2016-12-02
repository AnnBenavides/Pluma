/* ------------------------Codigo copiado al inicio de la clase---------------------- */
/* Esta seccion contiene codigo que se copiara literalmente al principio de la clase, aqui
se define el paquete y los import necesarios. */

// imports para la clase
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.Symbol;

%%
/* ------------------------Configuracion de flex y declaraciones---------------------- */
/* Esta seccion contiene codigo para configurar la clase creada y declaraciones de macros 
y estados para usar en las expresiones regulares */

// configuracion de la clase. -> public class Lexer implements sym { ... }
%class Lexer
%implements sym
%public

// compatibilidad con cup y simbolos unicode. 
%unicode
%cup

// conteo de columnas y lineas.
%column	// disponible en variable yycolumn.
%line	// disponible en variable yyline.


// todo lo que va aqui dentro se copia textual en la clase Lexer.
%{   
	// variable e inicializadores para trabajar con cup.
    private ComplexSymbolFactory symbolFactory;
    
	public Lexer(ComplexSymbolFactory sf, java.io.InputStream is){
		this(is);
        symbolFactory = sf;
    }
	public Lexer(ComplexSymbolFactory sf, java.io.Reader reader){
		this(reader);
        symbolFactory = sf;
    }
    
    
	// funciones para generar simbolos (con y sin valor asociado).
    public Symbol symbol(String name, int code){
		return symbolFactory.newSymbol(name, code,
						new Location(yyline+1,yycolumn+1, yychar),
						new Location(yyline+1,yycolumn+yylength(), yychar+yylength())
				);
    }
    public Symbol symbol(String name, int code, Object value){
		return symbolFactory.newSymbol(name, code, 
						new Location(yyline+1, yycolumn +1, yychar), 
						new Location(yyline+1,yycolumn+yylength(), yychar+yylength()), 
						value);
    }
    
    
    // funcion para enviar errores.
    private void send_error(String error) {
    	throw new Error("at line: " + (yyline+1) + " column: " + yycolumn + ". " + error);
    }
%}

%eofval{
    return symbol("EOF", EOF); 
%eofval}

// STATE declarations
%xstate EXPR, IF, THEN, ELSE
<EXPR>{
	"tablero-col" 		{ return symbol("col", TCOL) }
	{col}					{ return symbol("c", C, yytext()) }
	"borde"				{ return symbol("borde", BORDE) }
	"pluma-dir"			{ return symbol("dir", PDIR) }
	{dir}					{ return symbol("d",D, yytext()) }
	"pluma-col"			{ return symbol("col", PCOL) }
	{col}					{ return symbol("c", C, yytext()) }
	"pluma-arriba"		{ return symbol("parriba", PARRIBA) }
	"pluma-abajo"		{ return symbol("pabajo", PABAJO) }
	{whitespace}			{ /* ignorar espacios */ }
	/* defindir and, or y not */
	"not"				{ yybegin(NOT); return symbol(); }
	"and"				{ yybegin(AND); return symbol(); }
	"or"				{ yybegin(OR); return symbol(); }
	[^]					{ send_error("error en condicion del IF"); }
	
}
<IF>{	
	[]			{ yybegin(EXPR); return symbol();}
}
<THEN>{
	[]			{ yybegin(EXPR); return symbol();}
}
<ELSE>{
	[]			{ yybegin(EXPR); return symbol();}
}
<NOT>{
	[] { yybegin(EXPR); return symbol(); }
}
<AND>{
	[] { yybegin(EXPR); return symbol(); }
}
<OR>{
	[] { yybegin(EXPR); return symbol(); }
}
// MACRO
whitespace = [ \n]
[a-zA-Z]+
num=[0-9]+
dir=[SNOE]
col=[ARVBN]

%%
/* ------------------------Reglas lexicas---------------------- */
/* esta seccion contiene expresiones regulares y codigo java asociado que se ejecutara cada vez que se encuentre la expresion. 
El orden en el que estan las expresiones define la precedencia de una sobre otra. */

"bajar-pluma"				{ return symbol("bpluma", BPLUMA); } 
"levantar-pluma"			{ return symbol("lpluma", LPLUMA); }
"color-pluma"				{ return symbol("col", CPLUMA); }
{col} 							{ return symbol("c", C, yytext()); }
"direccion-pluma"			{ return symbol("dir", DPLUMA); }
{dir}							{ return symbol("d", D, yytext()); }
"avanzar"					{ return symbol("num", AVANZAR);}
{num}							{ return symbol("numero", NUM, Integer.parseInt(yytext()));}

/* definir ifs y otros 
"if" 			{ yybegin(IF); return symbol(); }
"then"			{ yybegin(THEN); return symbol(); }
"else"			{ yybegin(ELSE); return symbol();}
*/

"if"			{ return symbol("if", IF); }
"then"			{ return symbol("then", THEN); }
"else"			{ return symbol("else", ELSE); }

/* definir operadores booleanos */
"and"			{ return symbol("and", AND); }
"or"			{ return symbol("or", OR); }
"not"			{ return symbol("not", NOT); }

/* else */
";"				{ return symbol("semicolon", SEMICOLON); }

{whitespace}			{; /* ignorar espacios */ }
[^]					{ send_error("expresion invalida"); }
