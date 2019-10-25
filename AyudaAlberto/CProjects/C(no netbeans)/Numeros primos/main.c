#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	
    int contador, numfor, primo;
    int x,y;
    
    do{
	printf("Dame un entero positivo mayor que 2: ");
	scanf("%d",&x);
	printf("Dame otro: ");
	scanf("%d",&y);
	if((x<0 || x<2)||(y<0||y<2)){
	printf("Algun numero no cumple las normas \n");
	}
	}while((x<0 || x<2)||(y<0||y<2));
	
    for ( numfor = x ; numfor <= y ; numfor++ ){
    primo = 1;
    contador = 2;

    while ( contador <= numfor / 2 && primo ){
    if ( numfor % contador == 0 ){
    primo = 0;
    }
	contador++;
	}
	if ( primo ){
	printf( "%d ", numfor );
	}
    }
	return 0;
}
