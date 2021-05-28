package Main;

import java.util.Random;

public class Problema {

	Casilla [] [] 	campoBatalla;
	private Casilla c_inicial, c_final, obs;
	int _semilla, nfila, nColumna;

	public Problema (Configuracion cfg){

		//GENERAR TODAS LAS CASILLAS NECESARIAS: INICIAL, FINAL Y OBSTÁCULOS
		_semilla = cfg._semilla;
		Random aleatorios = new Random(cfg._semilla);
		nColumna = cfg.nColumna;
		nfila = cfg.nfila;

		//GENERAR CASILLA INICIAL
		int FilaInicial = aleatorios.nextInt(nfila);
		int ColumnaInicial = aleatorios.nextInt(nColumna);
		c_inicial = new Casilla(FilaInicial, ColumnaInicial);

		//GENERAR CASILLA FINAL
		int FilaFinal = aleatorios.nextInt(nfila);
		int ColumnaFinal = aleatorios.nextInt(nColumna);
		c_final = new Casilla(FilaFinal,ColumnaFinal);

		//GENERAR POSICIONES PARA OBSTÁCULOS
		int n = 0;
		campoBatalla = new Casilla[nfila][nColumna];

		while(n < cfg._numObstaculos){
			int FilaObs = aleatorios.nextInt(nfila);
			int ColumnaObs = aleatorios.nextInt(nColumna);
			obs = new Casilla(FilaObs,ColumnaObs);
			if(!esIgual(obs,campoBatalla,c_final,c_inicial)){
				campoBatalla [FilaObs] [ColumnaObs] = obs;
				n++;
			}
		}
	}
	public Casilla getInicial(){return c_inicial;}
	public Casilla getFinal(){return c_final;}
	public Casilla[][] getMalla(){return campoBatalla;}
	public int getnFilas(){return nfila;}
	public int getnColumnas(){return nColumna;}
	private boolean esIgual(Casilla obs, Casilla[][] campoBatalla, Casilla c_final, Casilla c_inicial){
		//COMPROBAR QUE LAS NUEVAS CASILLAS NO ESTEN ANTERIORMENTE
		boolean v = false;
		if((obs.getFila() == c_final.getFila()) &&
				(obs.getColumna() == c_final.getColumna())){          //Comprobar que la nueva casilla no se igual a la inicial
			v = true;
		} else if((obs.getFila() == c_inicial.getFila()) &&
				(obs.getColumna() == c_inicial.getColumna())){        //Comprobar que la nueva casilla no se igual a la final
			v = true;
		} else {
			for (int i = 0;i<16;i++){
				for (int j=0; j<12;j++){
					if(campoBatalla[i][j] != null &&
							(campoBatalla[i][j].getFila()==obs.getFila()) &&
							(campoBatalla[i][j].getColumna()==obs.getColumna())){    //Comprobar que la nueva casilla no se igual a ningún obstáculo
						v=true;
					}
				}
			}
		}
		return v;
	}
	public void pintarProblema(){
		//IMPRIMIR UNA MATRIZ CON LA CONFIGURACIÓN DEL PROBLEMA POR CONSOLA
		for (int i = 0;i<1;i++){
			System.out.print("     ");
			for (int j=0; j<12;j++){						//Imprimir la primera fila de números
				System.out.print(+j + "  ");
			}
			System.out.println();
		}
		for (int i = 0;i<16;i++){
			if(i<10) {
				System.out.print(" "+i + "|  ");
			} else {										//Imprimir la columna vertical de números
				System.out.print(i + "|  ");
			}
			for (int j=0; j<12;j++){
				if(campoBatalla[i][j] != null) {
					System.out.print("X  ");
				} else if (c_inicial.getFila()==i && c_inicial.getColumna() == j){
					System.out.print("I  ");										//Imprimir las posiciones
				} else if (c_final.getFila()==i && c_final.getColumna() == j){      //iniciales, finales y de los obstáculos
					System.out.print("F  ");
				} else {
					System.out.print("-  ");
				}
			}
			System.out.println("");
		}
	}
}
