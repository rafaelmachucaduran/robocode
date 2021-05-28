package Busqueda;

import Acciones.Accion;
import Acciones.FuncionTransicion;
import Acciones.FuncionTransicionCuatro;
import Main.Casilla;
import Main.Configuracion;
import Main.Heuristic;
import Main.Problema;

import java.util.ArrayList;

public class BusquedaAStar extends Busqueda {
    Heuristic _heuristic;
    FuncionTransicion f;
    public BusquedaAStar(Problema p, Heuristic heuristic, FuncionTransicion f){
        super(p);
        _heuristic = heuristic;
        this.f = f;
    }

    public boolean ejecutar() {
        Casilla act = null;
        ArrayList<Casilla> sig;
        int h = _heuristic.h(_inicial, _final);

        _inicial.setGasto(0);
        _inicial.setHeuristic(h);
        abierto.add (_inicial);
        boolean encontradoFinal = false;
        while (!abierto.empty() & !encontradoFinal) {
            act = (Casilla) abierto.remove(0);
            cerrados.add(act);

            if(act.equals(_final)){
                encontradoFinal = true;
            } else {
                sig = f.calcularEstados(act,_p);

                for (Casilla c : sig) {
                    if(!cerrados.contains(c)){
                        h = _heuristic.h(c,_final);
                        c.setHeuristic(h);
                        int gasto = act.getGasto();
                        c.setGasto (gasto+new Accion(act,c).getGasto());

                        if (abierto.contains(c)){
                            int index = abierto.indexOf(c);
                            Casilla casilla = abierto.get(index);

                            if(c.getFila() < casilla.getFila()){
                                abierto.remove(index);
                                abierto.add(c);
                            }
                        } else {
                            abierto.add(c);
                        }
                    }
                }

            }
        }
        if(encontradoFinal){
            Casilla actSol = act;
            Casilla sigSol = actSol.getSuperior();

            while (sigSol != null){
                Accion a = new Accion (sigSol, actSol);
                acciones_solucion.addFirst(a);
                //Realizar el camino inverso para buscar
                actSol = sigSol;                        //Camino solución
                sigSol = actSol.getSuperior();
            }
        }
        return encontradoFinal;
    }
    public void imprimirCamino(){
        for (Accion a: acciones_solucion) {
            System.out.println(a);
        }
    }
}