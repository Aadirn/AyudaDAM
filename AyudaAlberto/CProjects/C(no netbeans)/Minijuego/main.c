#include <stdio.h>
#include <stdlib.h>

#define FALSE 0
#define TRUE 1
#define FILAS 10 
#define COLUMNAS 10


//Declaramos el tablero donde se mueve el monigote
char tablero[FILAS][COLUMNAS];
//Declaramos variables para la posición actual del monigote;
int fa = 0;
int ca = 0;


//Llena todo el tablero de '_' salvo la posición 0,0 que tiene el momigote '*'
void inicializaTablero(){
	int f,c;
	//Llenamos la matriz de '_'
	for(f=0;f<FILAS;f++){
		for(c=0;c<COLUMNAS;c++){
			tablero[f][c]='_';
		}
	}
	//Colocamos al inicio '*'
	tablero[2][2] = '*';
	fa=2;
	ca=2;	
}

//Dibuja en pantalla el tablero
void pintaTablero(){
	int f,c;
	
	//Recorremos las filas
	for(f=0;f<FILAS;f++){
		//Recorremos las columnas
		for(c=0;c<COLUMNAS;c++){
			printf("%c",tablero[f][c]);
		}
		printf("\n");
	}	
}

//Pide al usuario una tecla R,L,U,D y la devuelve como carácter
char obtenMovimiento(){
	char tecla;
	printf("Pulsa una tecla (w,a,s,d): ");
	scanf("%c",&tecla);
	return tecla;	
}

//Recibe el char con el movimiento deseado ('R','L','U','D'}, e intenta mover el
//monigote '*' a la nueva posición
void actualizaTablero(char m){
	tablero[fa][ca]='_';
	switch(m){
		case 'd':
			if(ca<COLUMNAS-1) ca++;
			break;
		case 'a':
			if(ca>0) ca--;
			break;
		case 'w':
			if(fa>0) fa--;
			break;
		case 's':
			if(fa<FILAS-1) fa++;
			break;	
	}
	tablero[fa][ca]='*';
	
}

int main(int argc, char *argv[]) {
	char movimiento;
	
	//Inicializamos tablero
	inicializaTablero();
	pintaTablero();	
	do{
		
		movimiento = obtenMovimiento();
		actualizaTablero(movimiento);
		system("cls");
		
		pintaTablero();			
	}while(TRUE);
	
	
	system("pause");	
	return 0;
}
