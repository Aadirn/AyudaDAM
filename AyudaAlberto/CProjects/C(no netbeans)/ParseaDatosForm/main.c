#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	
	char QUERY_STRING[1024]="nombre=Adrian&password=1234&tieneCoche=si&selectsexo=mujer&estadoCivil=soltero";
	
	char datos[1024];
	
	strcpy(datos,QUERY_STRING);
	int i,j,k=0;
	int longitud = strlen(datos);
	for(i=0; i < longitud;i++){
		if(datos[i]==38){
			datos[i]=" ";
			printf("\n");
		}else{
			printf("%c",datos[i]);
		}
	}
	char tablita [4][128];
	while(datos[i]!='&'){
		tablita[0][i]=datos[i];
		
	}
	return 0;
}
