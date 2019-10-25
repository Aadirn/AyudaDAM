#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	//Pedir multiplicando y multiplicador, devolver resultado, la multiplicacion tiene que ser solo con sumas
	int multiplicando, multiplicador;
	int contador;
	int suma=0;
	
	printf("Dame un multiplicando: ");
	
	scanf("%d",&multiplicando);
	
	printf("Dame un multiplicador: ");
	
	scanf("%d",&multiplicador);
	
	// Operacion con sumas que tengo que averiguar como carajo lo hago
	
	for(contador=1; contador<=multiplicador;contador++){
		suma+=multiplicando;
	}
	printf("El resultado de multiplicar %d y %d es: %d", multiplicando, multiplicador, suma);
	
	return 0;
}
