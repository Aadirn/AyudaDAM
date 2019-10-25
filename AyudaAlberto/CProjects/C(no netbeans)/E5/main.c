#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int ascii;
	printf("CHAR \t DEC \t HEX \n \n");
	
	//Recorro todos los numeros desde el inicio que yo le doy, en este caso 33 hasta el limite puesto en 126
	
	for(ascii=33 ;ascii<=126; ascii++){
		printf("%c \t %d \t %X \n", ascii,ascii,ascii); //Imprimo los numeros que el for recorre solo que indicando el formato en el cual salen, caracteres, decimal o hexadecimal
	}
	system("pause");
	return 0;
}
