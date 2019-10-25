#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int numero, cuadrado, cubo;
	
	numero = 1;
	while (numero <=10){
		cuadrado = numero * numero;
		cubo = cuadrado * numero;
		printf ("%d \t %d \t %d\n", numero, cuadrado, cubo);
		numero++;
	}
	system("pause");
	return 0;
}
