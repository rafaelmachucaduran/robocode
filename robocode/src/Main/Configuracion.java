package Main;

public class Configuracion {
	int _numObstaculos, _semilla, _algoritm;
	
	int numPixelFila, numPixelCol, _tamCelda, nfila, nColumna	;
	
	// Establecer parámetros de la batalla
			int numberOfRounds		= 5;
			long inactivityTime 	= 1000000000;
			double gunCoolingRate	= 1.0;
			int sentryBorderSize 	= 50;
			boolean hideEnemyNames 	= false;
			
			
	public Configuracion( ){
		_numObstaculos	=  20;		//COMO UTILIZAR LA VARIABLE ALGORITM
		_semilla		=  20;		//Poner a 0 para Amplitud y 4 movimientos
		_algoritm		= 3;    	//Poner a 1 para Amplitud y 8 movimientos
		cfg_ini();					//Poner a 2 para A* y 4 movimientos
	}								//Poner otro numero para A* y 8 movimientos
	
	
	Configuracion(int obstaculos, int semilla){
		
		_numObstaculos = obstaculos;
		_semilla		   = semilla;
		
		cfg_ini();
	}
	
	private void cfg_ini() {
		 numPixelFila	= 800;
		 numPixelCol	= 600;
		 _tamCelda 		= 50;
		
		 nfila      =  numPixelFila / _tamCelda;
		 nColumna	= numPixelCol  / _tamCelda;
	}
	
	public int getNumObstaculos () {
		return _numObstaculos;
	}
	
	public int getTamCelda() {
		return _tamCelda;
	}
	
	public int getNumPixelFila() {
		return numPixelFila;
	}
	
	public int getnumPixelCol() {
		return numPixelCol;
	}
	
	public int getNumFila() {
		return nfila;
	}
	
	public int getNumColumna() {
		return nColumna;
	}

	public int getAlgoritm() { return _algoritm; }
}