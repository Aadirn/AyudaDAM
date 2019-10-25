#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int numero,i;
	do{
		printf("Introduzca un numero entre 1 y 10: ");
		scanf("%d",&numero);			
	}while(numero < 1 | numero > 10);
	for(i=0 ;i<numero ;i++){
		printf("Dios mio, que listo soy! \n");
	}
	return 0;
}
