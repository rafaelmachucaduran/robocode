package Robot;

import Acciones.Accion;
import Acciones.FuncionTransicionCuatro;
import Acciones.FuncionTransicionOcho;
import Busqueda.Busqueda;
import Busqueda.BusquedaAStar;
import Busqueda.BusquedaAmplitud;
import Main.Casilla;
import Main.Configuracion;
import Main.Heuristic;
import Main.Problema;
import robocode.Robot;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import static robocode.util.Utils.normalRelativeAngleDegrees;

/**
 * Apellidos, Nombre: Machuca Durán, Rafael
 * Titulación: Ing. Informática
 * Grupo: 2º A
 * Repositorio GitHub: https://github.com/rafaelmachucaduran/robocode.git
 */
public class RobotBusqueda extends Robot {

	Configuracion cfg;
	Problema p;
	Busqueda b;

	public void run() {

		cfg = new Configuracion(); //Generar la configuración

		setAllColors(Color.red); // Nuestro robot será rojo

		//GENERAR EL PROBLEMA DE BÚSQUEDA
		p = new Problema(cfg);

		//BUSCAR LA SOLUCIÓN CON UN ALGORITMO
		if(cfg.getAlgoritm() == 0) b = new BusquedaAmplitud(p, new FuncionTransicionCuatro());                    //Generar la busqueda de tipo amplitud con 4 movimientos
		else if(cfg.getAlgoritm() == 1) b = new BusquedaAmplitud(p, new FuncionTransicionOcho());                 //Generar la busqueda de tipo amplitud con 8 movimientos
		else if(cfg.getAlgoritm() == 2) b = new BusquedaAStar(p, new Heuristic(), new FuncionTransicionCuatro()); //Generar la busqueda de tipo A* con 4 movimientos
		else b = new BusquedaAStar(p, new Heuristic(), new FuncionTransicionOcho());                              //Generar la busqueda de tipo A* con 8 movimientos
		boolean solucion = b.ejecutar();                                                                          //Ejecutar el algoritmo de búsqueda

		//IMPRIMIR EN CONSOLA
		System.out.println("Matriz del problema");
		p.pintarProblema();     //Imprimir problema en consola
		System.out.println();
		System.out.println("Acciones para solucionar el problema");
		b.imprimirCamino();     //Imprimir acciones solución
		System.out.println();
		System.out.println("Coste total: "+b.getCoste());   //Imprimir el coste
		System.out.println();

		//EJECUTAR LA SOLUCIÓN ENCONTRADA
		System.out.println("Iniciando ejecución del robot");
		turnRight(normalRelativeAngleDegrees(0 - getHeading())); //Orientamos inicialmente el robot hacia arriba
		if (solucion) {  //A continuación nuestro robot recorrerá el camino solución, si ésta se ha encontrado
			for (Accion a : b.getAccionesSolucion()) {
				int grados = a.getGrados();
				turnRight(normalRelativeAngleDegrees(grados - getHeading()));
				if(grados == 0 || grados == 90 || grados == 180 || grados == 270)
					ahead(cfg.getTamCelda());
				else
					ahead(cfg.getTamCelda()*Math.sqrt(2.0));
			}
		}
	}

	/***
	 * Esté método se ejecutará cuándo se pulse el botón Pintar
	 */
	public void onPaint(Graphics2D g) {

		int fila  		= cfg.getNumFila();
		int tamCelda 	= cfg.getTamCelda();
		int filaPixels 	= cfg.getNumPixelFila();
		int columnaPixels = cfg.getnumPixelCol();
		int tamCuadrado = 10;

		//PINTAR MALLA BLANCA
		g.setPaint((Color.white));
		for(int i = 0; i<fila; i++) {
			g.drawLine(0, i*tamCelda, filaPixels, i*tamCelda);
			g.drawLine(i*tamCelda, 0, i*tamCelda, columnaPixels);
		}

		//PINTAR CASILLA INICIAL VERDE
		g.setColor(Color.GREEN);
		g.fillRect((tamCelda*p.getInicial().getFila()+(tamCelda-tamCuadrado)),
				(tamCelda*p.getInicial().getColumna()+(tamCelda-tamCuadrado)),tamCuadrado,tamCuadrado);

		//PINTAR CASILLA FINAL ROJO
		g.setColor(Color.RED);
		g.fillRect((tamCelda*p.getFinal().getFila()+(tamCelda-tamCuadrado)),
				(tamCelda*p.getFinal().getColumna()+(tamCelda-tamCuadrado)),tamCuadrado,tamCuadrado);


		//PINTAR CASILLAS CERRADAS AMARILLO
		//HashSet<Casilla> cerradas = bampli.getCerrados();
		HashSet<Casilla> cerradas = b.getCerrados();
		g.setColor(Color.YELLOW);
		for(Casilla a: cerradas){
			g.fillRect((tamCelda*a.getFila()),
					(tamCelda*a.getColumna()),tamCuadrado,tamCuadrado);
		}

		//PINTAR CASILLAS ABIERTAS ROSA
		//Stack<Casilla> abiertos = bampli.getAbierto();
		Stack<Casilla> abiertos = b.getAbierto();
		g.setColor(Color.PINK);
		for(Casilla a: abiertos){
			g.fillRect((tamCelda*a.getFila()),
					(tamCelda*a.getColumna()),tamCuadrado,tamCuadrado);
		}

		//PINTAR CAMINO SOLUCION AZUL
		//LinkedList<Accion> acciones_solucion = bampli.getAccionesSolucion();
		LinkedList<Accion> acciones_solucion = b.getAccionesSolucion();
		g.setColor(Color.BLUE);
		for(Accion a: acciones_solucion){
			g.fillRect((tamCelda*a.getFilIr()),
					(tamCelda*a.getColIr()),tamCuadrado,tamCuadrado);
		}
	}
}
