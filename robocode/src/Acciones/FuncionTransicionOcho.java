package Acciones;

import Main.Casilla;
import Main.Problema;

import java.util.ArrayList;

public class FuncionTransicionOcho extends FuncionTransicion {

    public ArrayList<Casilla> calcularEstados(Casilla act, Problema p){
        ArrayList<Casilla> sig = new ArrayList<Casilla>();
        int _fila = act.getFila(), _columna = act.getColumna();

        //COMPROBAR QUE ESTADOS SON POSIBLES PARA LA EXPANSIÓN
        if(esObstaculo(_fila, _columna)){
            if(movValido(_fila-1,_columna) && esObstaculo(_fila-1,_columna))
                sig.add(new Casilla(_fila-1, _columna,act));           //Izquierda
            if(movValido(_fila-1,_columna+1) && esObstaculo(_fila-1,_columna+1) && esObstaculo(_fila-1,_columna)&& esObstaculo(_fila,_columna+1))
                sig.add(new Casilla(_fila-1, _columna+1,act));           //IzquierdaNorte
            if(movValido(_fila-1,_columna-1) && esObstaculo(_fila-1,_columna-1) && esObstaculo(_fila-1,_columna) && esObstaculo(_fila,_columna-1))
                sig.add(new Casilla(_fila-1, _columna-1,act));           //IzquierdaSur
            if(movValido(_fila+1,_columna) && esObstaculo(_fila+1,_columna))
                sig.add(new Casilla(_fila+1, _columna,act));           //Derecha
            if(movValido(_fila+1,_columna+1) && esObstaculo(_fila+1,_columna+1) && esObstaculo(_fila,_columna+1) && esObstaculo(_fila+1,_columna))
                sig.add(new Casilla(_fila+1, _columna+1,act));           //DerechaNorte
            if(movValido(_fila+1,_columna-1) && esObstaculo(_fila+1,_columna-1) && esObstaculo(_fila+1,_columna) && esObstaculo(_fila,_columna-1))
                sig.add(new Casilla(_fila+1, _columna-1,act));           //DerechaSur
            if(movValido(_fila,_columna+1) && esObstaculo(_fila,_columna+1))
                sig.add(new Casilla(_fila, _columna+1,act));           //Arriba
            if(movValido(_fila,_columna-1) && esObstaculo(_fila,_columna-1))
                sig.add(new Casilla(_fila, _columna-1,act));           //Abajo
        }
        return sig;
    }

}