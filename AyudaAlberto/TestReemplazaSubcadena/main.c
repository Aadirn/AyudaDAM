#include <stdio.h>
#include <string.h>
#include <stdlib.h>

//Busca "buscada" en "busqueda" y la sustituye por "sustituida"
char* reemplazaSubcadena(char *busqueda, const char *buscada, const char *sustituida){
	char *inicio = strstr(busqueda,buscada);
	char *fin = inicio + strlen(buscada) -1;
	char *resto = malloc(strlen(fin + 1)/*esto es el resto de la cadena*/ * sizeof (char));
	strcpy(resto,fin+1);
	strcat(strcpy(inicio,sustituida),resto);
	free(resto);
	return busqueda;
}

int main(int argc, char **argv)
{
	char cadena[1024] = "El #p1# de la persona es #p2# importante";
	reemplazaSubcadena(cadena,"#p1#","n");
	reemplazaSubcadena(cadena,"#p2#","muy");
	printf("%s\n",cadena);
	
	system("pause");
	return 0;
}
