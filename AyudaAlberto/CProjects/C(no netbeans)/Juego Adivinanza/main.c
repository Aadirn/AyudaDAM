#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int numerico, aleatorio;
	
	printf("Dame un numero secreto: ");
	scanf("%d",&aleatorio);
	
	do{
	printf("Intenta adivinar el numero: ");
	scanf("%d",&numerico);
	if (numerico<aleatorio){
		printf("Te has quedado corto! \n");
	} else if (numerico>aleatorio){
		printf("Te has pasado! \n");
	}else{
		printf("ENHORABUENA! Has acertado despues de intentos.Bye Bye.\n");
	}
	}while(numerico!=aleatorio);
	return 0;
}
