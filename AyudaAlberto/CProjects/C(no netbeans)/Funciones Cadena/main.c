#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	//Declaramos un array de caracteres
	char cadena [50] = "Ya estamos en 2019";
	//Obtenemos y mostramos la longitud de la cadena
	int longitud = strlen(cadena);
	printf("La cadena es de %d caracteres \n",longitud);
	printf("----------------------------------------\n");
	
	//Leemos una cadena desde teclado (con scanf)
	printf("Escribe un texto sin espacios: ");
	scanf("%s",cadena);
	longitud = strlen (cadena);
	printf("La cadena es de %d caracteres\n",longitud);
	printf("----------------------------------------\n");
	fflush(stdin);
	
	//Leemos una cadena desde teclado (con gets)
	printf("Escribe un texto con o sin espacios: ");
	gets(cadena);
	longitud = strlen(cadena);
	printf("La cadena es de %d caracteres\n",longitud);
	printf("----------------------------------------\n");
	
	//Comparamo cadenas de caracteres
	char nombre1 [50];
	char nombre2 [50];
	printf("Dame un nombre: ");
	gets(nombre1);
	printf("Dame otro nombre");
	gets(nombre2);
	int resultadoComparacion = strcmp(nombre1,nombre2);
	if (resultadoComparacion<0){
		printf("%s < %s\n",nombre1,nombre2);
		}else if(resultadoComparacion==0){
			printf("%s == %s\n",nombre1,nombre2);
				}else{
					printf("%s > %s\n",nombre1,nombre2);
}
	

	
	system("pause");
	return 0;
}
