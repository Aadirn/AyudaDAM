#include <stdio.h>
#include <stdlib.h>
#define TRUE 1
#define FALSE 0

int muestraSituacionEsperaEvento(int estado){
	int lx[3]={0,1,1};
	int ly[3]={0,0,1};
	int evento;
	/*switch(estado){
		case 0:
			lx=0; ly=0;
			break;
		case 1:
			lx=1; ly=0;
			break;
		case 2:
			lx=1; ly=1;
			break;
	}*/
	printf("Estado actual:%d   LX:%d   LY:%d  Nuevo evento(0,1)?:",estado,lx[estado],ly[estado]);
	scanf("%d",&evento);
	
	return evento;	
}

int evaluaNuevoEstado(int estadoActual, int evento){
		
		int estadoSiguiente[3][2]={{1,0},{2,0},{2,0}};
		
		return estadoSiguiente[estadoActual][evento];
	}

int main(int argc, char *argv[]) {
	
	int estado; //0,1,2	
	int evento;
	
	//Inicializamos estado='A'
	estado = 0;
	do{
		evento = muestraSituacionEsperaEvento(estado);
		estado = evaluaNuevoEstado(estado,evento);			
							
	}while(TRUE);
	
	system("pause");	
	return 0;
}
