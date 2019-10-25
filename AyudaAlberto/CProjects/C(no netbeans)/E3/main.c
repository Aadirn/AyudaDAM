#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int x,y,suma,resta,multiplicacion,division;
	
	char accion;
	//Tras cada scan, limpio el buffer del teclado
	printf("Dame un numero (x) ");
	scanf("%d",&x);
	fflush(stdin);
	printf("Dame un numero para (y): ");
	scanf("%d",&y);
	fflush(stdin);
	//Escribo el menú
	printf("S-Suma X+Y.\n");
	printf("R-Resta X-Y.\n");
	printf("M-Multiplicacion X*Y.\n");
	printf("D-Division X/Y.\n");
	printf("Selecciona una opcion: \n");
	
	fflush(stdin);
	scanf("%c",&accion);
	fflush(stdin);
	
	//Hago las operaciones (menos la division por causar problemas)
	suma=x+y;
	resta=x-y;
	multiplicacion=x*y;
	
	//Logica del programa, donde permití que se admitiera que la letra fuese minúscula o mayúscula
	
	switch (accion){
			case 's':
			case 'S':
			printf("La suma es de %d y %d es: %d.\n",x,y,suma);
		break;
			case 'r':
			case 'R':
			printf("La resta es de %d y %d es: %d.\n",x,y,resta);
		break;
			case 'm':
			case 'M':
			printf("La multiplicacion es de %d y %d es: %d.\n",x,y,multiplicacion);
		break;
			case 'd':
			case 'D':
				if (y == 0 && x>0){
					printf("+INF");
						}else if ( y == 0 && x<0){
							printf("-INF");
								}else{
									//Como si hacía la division antes de comprobar si y=0, crasheaba, introduje la división tras la comprobacion
									division=x/y;
									printf("La division es de %d y %d es: %d.\n",x,y,division);
					}
				break;
		default:
			printf("Peticion no valida");
		}
	printf("\n");
	system("pause");
	return 0;
}
