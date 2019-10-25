#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int x,y,z;
	
	//Se piden los valores por teclado
	
	printf("Dame un numero: ");
	
	scanf("%d",&x);
	printf("Dame otro numero: ");
	
	scanf("%d",&y);
	printf("Dame otro numero: ");
	
	scanf("%d",&z);
	
	//Se realizan las operaciones necesarias
	
	//Para el menor
	if (x<=y&&x<=z){
		printf("El menor es: %d.\n",x);
	}else if (y<=x&&y<=z){
		printf("El menor es: %d.\n",y);
	}else if (z<=x&&z<=y){
		printf("El menor es: %d.\n",z);
	}
	//Para el intemedio, aunque si dos números son iguales, el mayor y el intermedio o el menor y el intermedio aparecerá, el mismo
	if ((x<=y&&x>=z )|| (x>=y&&x<=z)){
		printf("El intermedio es: %d.\n",x);
	}else if ((y<=x&&y>=z )|| (y>=x&&y<=z)){
		printf("El intermedio es: %d.\n",y);
	}else if ((z<=x&&z>=y )|| (z>=x&&z<=y)){
		printf("El intermedio es: %d.\n",z);
	}
	//Para el mayor
	if (x>=y&&x>=z){
		printf("El mayor es: %d.\n",x);
	}else if (y>=x&&y>=z){
		printf("El mayor es: %d.\n",y);
	}else if (z>=x&&z>=y){
		printf("El mayor es: %d.\n",z);
	}
	printf("\n");
	system("pause");
	return 0;
}
