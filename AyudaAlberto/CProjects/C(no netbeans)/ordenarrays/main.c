#include <stdio.h>
#include <stdlib.h>
#define TRUE 1
#define FALSE 0

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int lista[5]={6,3,0,9,4};
	int i,j;
	int aux;
	int huboCambios;
	
	//array desordenado
	printf("El array desordenado es: ");
	for(i=0;i<5;i++){
	printf("%d", lista[i]);
}
	//ordenado
	j=3;
	do{
		huboCambios=FALSE;
		for(i=0;i<j;i++){
			if(lista[i]>lista[i+1]){
			aux=lista[i];
			lista[i]=lista[i+1];
			lista[i+1]=aux;
			huboCambios=TRUE;
			}
		}
		j--;
	}while(huboCambios==TRUE);
	
	//mostramos array ordenado
	printf("El array ordenado es: ");
	for(i=0;i<5;i++){
		printf("%d", lista[i]);
}
	return 0;
}





















/*for(i=0;i<3;i++){
		if(lista[i]>lista[i+1]){
			aux=lista[i];
			lista[i]=lista[i+1];
			lista[i+1]=aux;
		}
	}
	//mostramos array ordenado
	printf("El array ordenado es: ");
	for(i=0;i<5;i++){
	printf("%d", lista[i]);
}*/
