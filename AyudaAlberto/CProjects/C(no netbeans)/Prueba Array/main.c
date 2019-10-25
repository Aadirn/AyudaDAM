#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int edad [5];
	int i; //Indice para movernos por el array.
	int suma, media;
	//Pedimos y almacenamos edades.
	for(i = 0; i <= 4 ; i++){
		printf("Edad?:  \n");
		scanf("%d", &edad[i]);
	}
	//Calcular media.
	for (i=0 ; i <=4 ; i++){
	suma += edad [i];
	}
	media = suma/5;
	printf("\nLa edad media es: %d \n", media);
	system("pause");
	return 0;
}
