package Acciones;

import Main.Casilla;
import Main.Configuracion;
import Main.Problema;

import java.util.ArrayList;

public abstract class FuncionTransicion {
    int nfilas, nColumnas;
    Casilla[][] _mallaNodos;
    Problema p;
    Configuracion cfg;


    public FuncionTransicion(){
        cfg = new Configuracion();
        p = new Problema(cfg);
        nfilas = p.getnFilas();
        nColumnas = p.getnColumnas();
        _mallaNodos = p.getMalla();
    }
    abstract public ArrayList<Casilla> calcularEstados(Casilla act, Problema p);

    protected boolean posibleEstado(int _fila, int _columna){
        return movValido(_fila,_columna) && esObstaculo(_fila,_columna);
    }
    protected boolean esObstaculo(int _fila, int _columna){
        boolean obstaculo = false;

        if(p.getMalla()[_fila][_columna] != null){                   //Comprobar si existe un obstaculo en la casilla
            obstaculo = true;                                        //a la que queremos expandirnos
        }

        return !obstaculo;
    }
    protected boolean movValido(int _fila, int _columna){
        boolean valido = true;
        if(_fila>cfg.getNumFila()-1 || _columna>cfg.getNumColumna()-1
                || _fila < 0 || _columna < 0){                              //Comprobar que no nos salimos del mapa generado
            valido = false;
        }
        if(_fila == p.getInicial().getFila() && _columna == p.getInicial().getColumna()){
            valido = false;                                         //Comprobar que no volvemos a la casilla inicial
        }
        return valido;
    }
}
