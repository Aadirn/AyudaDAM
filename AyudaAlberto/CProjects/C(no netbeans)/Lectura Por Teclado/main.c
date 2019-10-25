#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	
	int x,y;
	
	int suma,resta,multiplicacion,cociente,resto;
	
	//Pedimos y leemos desde teclado dos enteros
	printf("Dame un entero para x: ");
	
	scanf("%d",&x);
	
	printf("Dame un entero para y: ");
	
	scanf("%d",&y);
	
	//Hacemos los calculos
	suma=x+y;
	resta=x-y;
	multiplicacion=x*y;
	cociente=x/y;
	resto=x&y;
	
	//Vamos a mostrar las variables(sus dierecciones y contenido)
	printf("La variable x de tipo int vale %d, esta en la direccion de memoria %X y ocupa en memoria %d bytes\n", x, &x, sizeof (x));
	
	//Mostramos los resultados
	printf("La suma de %d y %d es %d.\n",x,y,suma);
	printf("La resta de %d y %d es %d.\n",x,y,resta);
	printf("La multiplicacion de %d y %d es %d.\n",x,y,multiplicacion);
	printf("El cociente de %d y %d es %d.\n",x,y,cociente);
	printf("El resto de %d y %d es %d.\n",x,y,resto);
	
	system("pause");
	return 0;
}
