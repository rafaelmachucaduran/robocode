package Main;

public class Casilla {
	private int _fila, _columna;
	Casilla _c;
	int gasto, heuristic;

	public Casilla (int filaP, int columnaP){
		_fila 		= filaP;
		_columna 	= columnaP;
	}
	public Casilla (int filaP, int columnaP, Casilla c){
		_fila 		= filaP;
		_columna 	= columnaP;
		_c = c;
	}

	@Override
	public String toString() {
		return "("+_fila+","+_columna+')';
	}
	public int getFila(){return _fila;}
	public int getColumna(){return _columna;}
	public Casilla getSuperior(){ return _c;}
	public int getGasto(){return gasto;}
	public int setGasto(int gasto){return this.gasto = gasto;}
	public int getHeuristic(){return heuristic;}
	public int setHeuristic(int heuristic){return this.heuristic = heuristic;}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _columna;
		result = prime * result + _fila;
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Casilla other = (Casilla) obj;
		if (( other._columna == this._columna) && ( other._fila == this._fila) ) return true;
		else return false;

	}
}
