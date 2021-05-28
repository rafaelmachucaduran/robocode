package Busqueda;

import Acciones.Accion;
import Main.Casilla;
import Main.Problema;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public abstract class Busqueda {
	Stack<Casilla> abierto;
	HashSet <Casilla> cerrados;
	LinkedList<Accion> acciones_solucion;
	Casilla [] [] _mallaNodos;
	int nfilas, nColumnas;
	Problema _p;
	Casilla _inicial, _final;

	public Busqueda (Problema p){
		abierto = new Stack<Casilla>();
		cerrados = new HashSet<Casilla>();

		_p = p;
		_inicial = p.getInicial();
		_final = p.getFinal();
		_mallaNodos = p.getMalla();

		acciones_solucion = new LinkedList<Accion>();

		nfilas = p.getnFilas();
		nColumnas = p.getnColumnas();
	}

	abstract public boolean ejecutar();

	public Stack <Casilla> getAbierto(){
		return abierto;
	}

	public HashSet	<Casilla> getCerrados() {
		return  cerrados;
	}

	public LinkedList<Accion> getAccionesSolucion(){return acciones_solucion;}

	public void imprimirCamino(){
		for (Accion a: acciones_solucion) {
			System.out.println(a);
		}
	}

	public int getCoste (){
		int gasto = 0;
		for (int i=0;i<acciones_solucion.size();i++) {
			gasto +=acciones_solucion.get(i).getGasto();
		}
		return gasto;
	}
}