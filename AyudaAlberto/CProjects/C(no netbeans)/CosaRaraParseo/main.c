#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	
	char QUERY_STRING[1024]="nombre=Adrian&password=1234&tieneCoche=si&selectsexo=mujer&estadoCivil=soltero";
	
	char datos[1024];
		strcpy(datos,QUERY_STRING);
	//strcpy(datos,getenv("PATH"));

	char nombres[4][128];
	char valores[4][128];
	int numParametros;
	
	int i; //Para recorrer toda la QUERY_STRING
	int p; //Indice que apunta a cada uno de los parámetros en la tabla de parámetros
	int j; //Indice destino en nombre/valor
	char zona; //Indica con n(ombre)/v(alor) donde estamos (dentro de la query) 
	int ultimo_j_nombre;
	int longitudQuery = strlen(datos);
	
	p=0;
	j=0;
	zona = 'n';
	for(i=0;i<longitudQuery;i++){
		if(datos[i]=='='){//Cambio de nombre a valor
			//Finalizamos la cadena nombres índice p
			nombres[p][j] = '\0';
			ultimo_j_nombre=j;
			zona='v';
			j=0;
			printf("Detecto =\n");
		}else if(datos[i]=='&'){//Cambio de valor a nombre
			//Finalizamos la cadena valores de índice p
			valores[p][j] = '\0';
			zona='n';
			j=0;
			p++;
			printf("Detecto &\n");
		}else{
			if(zona=='n'){//Copiamos el carácter de la query al nombres[p][j]
			nombres[p][j] = datos[i];
				printf("Copio %c a nombres[%d][%d]\n",datos[i],p,j);	
			}else{//zona=='v' //Copiamos el carácter de la query al valores[p][j]
				valores[p][j] = datos[i];
				printf("Copio %c a valores[%d][%d]\n",datos[i],p,j);
			}
			j++;
			
		}		
	}
	//Como el último par nombre/valor no va seguido de &, lo hacemos ahora
	nombres[p][ultimo_j_nombre+1] = '\0';
	valores[p][j] = '\0';
	p++;
	
	numParametros = p;
	
	printf("\n\n");
	for(p=0;p<numParametros;p++){
		printf("%s = %s\n",nombres[p],valores[p]);
	}
	
	printf("\n");
	
	system("pause");	
	return 0;
}
