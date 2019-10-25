#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	
	int numero, cuadrado, multiplo;
	
	for (numero = 1; numero <= 30; numero++){
		
		cuadrado = numero * numero;
		
		multiplo = cuadrado%3;
		
		if (multiplo == 0){
		printf("Los diez primeros numeros cuyo cuadrado es multiplo de 3 son: %d \t %d \n", numero, cuadrado);
}
}
	system ("pause");
	return 0;
}
