#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	
	//Pedimos y leemos con gets una cadena de texto
	char cadena[1024];
	printf("Dame tu nombre completo: ");
	gets(cadena);
	
	//Mostramos la cadena leida
	printf("Tu nombre es: %s\n", cadena);
	
	return 0;
}
