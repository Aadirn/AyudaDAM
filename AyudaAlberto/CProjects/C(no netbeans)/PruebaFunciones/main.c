#include <stdio.h>
#include <stdlib.h>

void pintaLineaSolida(int);
void pintaLineaHueca(int);
void pintaCuadradito(int, int);

int main(int argc, char *argv[]) {
	
	//Pintamos un cuadradito de 3x5
	pintaCuadradito(3,5);
	printf("\n\n");
	
	//Pintamos un cuadradito de 10x10
	pintaCuadradito(10,10);
	printf("\n\n");
	
	//Pintamos un cuadradito de 8x10
	pintaCuadradito(8,10);
	printf("\n\n");
	
	system("pause");	
	return 0;
}

//-------------------------------------------------------------
//Función que pinta un cuadrado hueco de ancho x alto *
void pintaCuadradito(int alto, int ancho){	
	int f,c;	
	//Pintamos la primera
	pintaLineaSolida(ancho);	
	//Cambiamos de línea
	printf("\n");
	
	//Pintamos alto-2 filas intermedias
	for(f=0;f<alto-2;f++){
		pintaLineaHueca(ancho);
		//Cambiamos de línea
		printf("\n");		
	}
			
	//Pintamos la última
	pintaLineaSolida(ancho);	
	//Dejamos el cursor en la línea siguiente (Cambiamos de línea)
	printf("\n");	
	
}

//Dibuja una linea con 1 *, ancho-2 espacios y un último *
void pintaLineaHueca(int ancho){
	int c;
	//Pintamos el * de la izda
	printf("*");
	//Pintamos ancho-2 espacios intermedios
	for(c=0;c<ancho-2;c++){
		printf(" ");
	}
	//Pintamos el * de la drcha
	printf("*");	
}
//Dibuja una linea de tantos * como indica "ancho"
void pintaLineaSolida(int ancho){
	
	int c;
	for(c=0;c<ancho;c++){
		printf("*");
	}	
}
