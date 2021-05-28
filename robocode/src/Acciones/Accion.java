package Acciones;

import Main.Casilla;

public class Accion {
	public enum accion {izquierda, derecha, arriba, abajo, izquierdaNorte, izquierdaSur, derechaNorte, derechaSur};
	accion dir;
	int actF, actC, sigF, sigC;

	public Accion(Casilla act, Casilla sig){
		super();
		actF=act.getFila(); actC=act.getColumna(); sigF=sig.getFila(); sigC=sig.getColumna();  //Añadir los datos de las filas y columnas

		if(actF==sigF){
			if(actC < sigC){
				dir = accion.arriba;
			} else {
				dir = accion.abajo;
			}
		} else {
			if(actF < sigF){                                 //Comparar la posición de las casillas para así
				if(actC == sigC){                            //poder saber hacia que dirección debemos mover el robot
					dir = accion.derecha;
				} else if (actC < sigC){
					dir = accion.derechaNorte;
				} else {
					dir = accion.derechaSur;
				}
			} else {
				if(actC == sigC){
					dir = accion.izquierda;
				} else if(actC < sigC) {
					dir = accion.izquierdaNorte;
				} else {
					dir = accion.izquierdaSur;
				}
			}
		}
	}
	public int getGrados(){
		int grados = 0;
		switch (dir){
			case arriba: grados = 0; break;
			case derechaNorte: grados = 45; break;
			case derecha: grados = 90; break;
			case derechaSur: grados = 135; break;		   //Una vez tengamos la dirección hacia donde tiene que ir
			case abajo: grados = 180; break;               //el robot, debemos convertir la dirección en grados de movimientos
			case izquierdaSur: grados = 225; break;
			case izquierda: grados = 270; break;
			case izquierdaNorte: grados = 315; break;
		}
		return grados;
	}
	public int getFilIr(){return sigF;}
	public int getColIr(){return sigC;}
	public int getGasto (){
		if(dir == accion.abajo || dir == accion.arriba || dir == accion.izquierda || dir == accion.derecha){
			return 100;
		} else {
			return 150;
		} 
	}
	public String toString(){
		return "Actual: ("+actF+","+actC+")"+"| Siguiente: ("+sigF+","+sigC+")"+" Accion: "+dir;
	}


}
