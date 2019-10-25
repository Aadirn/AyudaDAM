#include <stdio.h>
#include <stdlib.h>

	int main(int argc, char *argv[]) {
		//Dividir es numero de veces enteras que al dividendo se le puede restar el divisor, yese numero de veces es el cociente, lo que sobra es el resto
	int cociente, dividendo, divisor, resto;
	
    printf("Introduzca dividendo: " );
    scanf( "%d", &dividendo );
    printf( "Introduzca divisor: " );
    scanf( "%d", &divisor );
    if(divisor==0){
    	printf("No se pueden  realizar divisiones entre 0/n");
    	return 0;
	}
	
	cociente = 0;
	resto = dividendo;

	while (resto >= divisor){
		resto -= divisor;
		cociente++; //Aumenta por que es el numero de restas que se realizan hasta que el resto sea menor que el divisor
	}
	
	printf("%d entre %d = %d	(Resto = %d) \n", dividendo, divisor, cociente, resto);
	system("pause");
	return 0;
}
