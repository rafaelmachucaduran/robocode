package Acciones;

import Main.Casilla;
import Main.Problema;

import java.util.ArrayList;

public class FuncionTransicionCuatro extends FuncionTransicion {

    public ArrayList<Casilla> calcularEstados(Casilla act, Problema p){
        ArrayList<Casilla> sig = new ArrayList<Casilla>();
        int _fila = act.getFila(), _columna = act.getColumna();

        //COMPROBAR QUE ESTADOS SON POSIBLES PARA LA EXPANSIÓN
        if(esObstaculo(_fila, _columna)){
            if(posibleEstado(_fila+1, _columna)){
                sig.add(new Casilla(_fila+1, _columna,act));
            }
            if (posibleEstado(_fila-1, _columna)){
                sig.add(new Casilla(_fila-1, _columna,act));
            }
            if(posibleEstado(_fila, _columna+1)){
                sig.add(new Casilla(_fila,_columna+1,act));
            }
            if(posibleEstado(_fila, _columna-1)){
                sig.add(new Casilla(_fila,_columna-1,act));
            }
        }
        return sig;
    }
}