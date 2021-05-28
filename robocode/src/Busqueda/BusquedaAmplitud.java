package Busqueda;

import Acciones.Accion;
import Acciones.FuncionTransicion;
import Acciones.FuncionTransicionCuatro;
import Acciones.FuncionTransicionOcho;
import Main.Casilla;
import Main.Configuracion;
import Main.Problema;

import java.util.ArrayList;

public class BusquedaAmplitud extends Busqueda {
	FuncionTransicion ftc;

	public BusquedaAmplitud(Problema p, FuncionTransicion f) {
		super(p);
		ftc = f;
	}

	@Override
	public boolean ejecutar() {

		Casilla act = null;
		ArrayList<Casilla> sig;
		abierto.add (_inicial);
		boolean encontradoFinal = false;

		while (!abierto.empty() & !encontradoFinal) {
			act = (Casilla) abierto.remove(0);         //Coger el siguiente nodo abierto
			cerrados.add(act);                               //Cerrar el nodo una vez lo hemos expandido y utilizado

			if(act.getFila()==_final.getFila() && act.getColumna() == _final.getColumna()){
				encontradoFinal = true;                      //Comprobar si estamos ya en el nodo final
			} else {
				sig = ftc.calcularEstados(act,_p);           //Expandir todos los nodos cercanos posibles
				for (Casilla c : sig) {
					if (!cerrados.contains(c)){              //Comprobar que nos los nodos no esten ya cerrados
						abierto.add (c);                     //Añadir los nuevos nodos a la lista de abiertos
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
