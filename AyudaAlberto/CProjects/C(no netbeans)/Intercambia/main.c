#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
//por aqui es por donde yo se lo paso por eso ahora funciona el intercambio
void swap(int* pa, int* pb){
	int aux;
	aux=*pa;
	*pa=*pb;
	*cpb=aux;
}

int main(int argc, char *argv[]) {
	int x =3;
	int y = 6;
	
	printf("Antes intercambio -> x:%d y:%d \n",x,y);
	//llamamos a la funcion de intercambio
	//&x es la direccion de x en si su valor original, el que yo le he puesto
	swap(&x,&y);
	
	printf("Despues intercambio -> x:%d y:%d \n",x,y);
	return 0;
}
