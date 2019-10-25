#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	//Este programa me muestra los cuadrados y cubos de los primeros 10 numeros (1...10)
	int numero, cuadrado, cubo;
	for (numero= 1; numero <= 10; numero ++){
		cuadrado = numero * numero;
		cubo = cuadrado * numero;
		printf("%d \t %d \t %d \n", numero, cuadrado , cubo);
	}
	system ("pause");
	return 0;
}
