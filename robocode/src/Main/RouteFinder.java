package Main;

import robocode.control.*;

/**
 * Apellidos, Nombre: Machuca Durán, Rafael
 * Titulación: Ing. Informática
 * Grupo: 2º A
 * Repositorio GitHub: https://github.com/rafaelmachucaduran/robocode.git
 */

public class RouteFinder {
	/**
	 * Configuración 
	 *
	 */

	//static String robocodeLocalization=  "C:\\Robocode"; //Windows
	static String	 robocodeLocalization= "/Users/rafaelmachucaduran/robocode"; //MAC o Linux"
	static String    nombreRobot ="Robot.RobotBusqueda*";

	/**
	 * Configuración 	 * 
	 */

	Configuracion 	cfg;
	RobocodeEngine  engine;
	BattlefieldSpecification battlefield;
	RobotSpecification[] modelRobots;
	RobotSpecification[] existingRobots;
	RobotSetup		  [] robotSetups ;
	Problema nuevoProblema;


	public  RouteFinder () {
		cfg = new Configuracion();
		nuevoProblema = new Problema (cfg); // Generamos un nuevo problema con la configuración que hemos creado.
	}

	/******************
	 * Configuración de la batalla
	 */

	public void cfgRobocode() {

		// Crear el RobocodeEngine desde una la instalación
		engine = new RobocodeEngine(new java.io.File(robocodeLocalization)); //Ojo configuración
		engine.setVisible(true); // Mostrar el simulador de Robocode

		// Crear el campo de batalla
		battlefield = new BattlefieldSpecification(cfg.getNumPixelFila(), cfg.getnumPixelCol());


		// En modelRobots recogemos la especificación de los robots que utilizaremos en la simulación.
		modelRobots = engine.getLocalRepository(nombreRobot+",sample.SittingDuck");

		// Incluiremos un robot sittingDuck por obstáculo, más nuestro propio robot.
		existingRobots =	new RobotSpecification[cfg.getNumObstaculos()+1];
		robotSetups 	= 	new RobotSetup[cfg.getNumObstaculos()+1];
	}

	/*************************************
	 *  Añado el Robot de Búsqueda
	 */

	public void addRobotBusqueda() {

		//Creamos primero nuestro propio robot y lo colocamos en la posición inicial del problema.

		double fila = (cfg._tamCelda*nuevoProblema.getInicial().getFila())+(cfg._tamCelda/2),
				columna = (cfg._tamCelda*nuevoProblema.getInicial().getColumna())+(cfg._tamCelda/2), arriba = 0.0;
		existingRobots[0] = modelRobots[0];
		robotSetups	  [0] = new RobotSetup(fila,  columna,  arriba);       //Orientación inicial de nuestro robot
	}

	/*************************************
	 *  Añado los obstáculos 
	 */

	public void addRobotObstaculos() {

		//Creamos un robot sittingDuck por cada obstáculo.

		Casilla [][] _malla = nuevoProblema.getMalla();

		double sittingDuckFila, sittingDuckColumna, arriba = 0.0;

		int n = 1;
		for (int i = 0;i<16;i++){
			for (int j=0; j<12;j++){
				if(_malla[i][j] != null) {
					sittingDuckFila = (cfg._tamCelda*_malla[i][j].getFila())+(cfg._tamCelda/2);
					sittingDuckColumna = (cfg._tamCelda*_malla[i][j].getColumna())+(cfg._tamCelda/2);
					existingRobots[n] 	= modelRobots[1];   //sittingDuck
					robotSetups[n]		= new RobotSetup(sittingDuckFila, sittingDuckColumna ,  0.0);
					n++;
				}
			}
		}

	}

	/***************
	 * Ejecución
	 */
	public void run () {

		/*
		 * Crear y desarrollar la batalla con los robots antes definidos
		 */

		BattleSpecification battleSpec =
				new BattleSpecification(battlefield,
						cfg.numberOfRounds,
						cfg.inactivityTime,
						cfg.gunCoolingRate,
						cfg.sentryBorderSize,
						cfg.hideEnemyNames,
						existingRobots,
						robotSetups);


		// Ejecutar la simulación el tiempo especificado
		engine.runBattle(battleSpec,true);
		// Cerrar la simulación
		engine.close();
		// Asegurarse de que la MV de Java se cierra adecuadamente.
		System.exit(0);
	}

	/********************************************
	 * Ejercutar
	 * @param args
	 */

	public static void main(String[] args) {

		RouteFinder practica = new RouteFinder();


		practica.cfgRobocode();
		practica.addRobotBusqueda();
		practica.addRobotObstaculos();
		practica.run();


	}

}
