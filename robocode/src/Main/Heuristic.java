package Main;

public class Heuristic {
    public Heuristic(){}

    public int h(Casilla inicial, Casilla fin) {
        double fila = fin.getFila()-inicial.getFila();
        double columna = fin.getColumna()-inicial.getColumna();
        return (int) Math.sqrt(Math.pow(fila,2)+Math.pow(columna,2));
    }
}
