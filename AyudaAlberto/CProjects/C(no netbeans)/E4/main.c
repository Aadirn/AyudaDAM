#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int numero;
	// Me decidí por una estructura do-while pues me parece la más eficaz para este caso
	do{
		printf("Introduzca un numero entre 1 y 10: ");
		scanf("%d",&numero);			
	}while(numero < 1 || numero > 10);
	
	system("pause");
	return 0;
}
