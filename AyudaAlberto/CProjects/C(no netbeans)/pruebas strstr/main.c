#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* void cargaDatos(){
    ocupacion = 0;
    FILE* pfichero;
    pfichero = fopen("citas.txt", "r");
    char linea[1024];
    char *campo;
    while (fgets(linea, 1024, pfichero) != NULL) {
		campo = strtok(linea, "#");
        strcpy(citas[ocupacion].lugar, campo);
        
        campo = strtok(NULL, "#");
        strcpy(citas[ocupacion].persona, campo);
        
        campo = strtok(NULL, "#");
        citas[ocupacion].fechaHora.dia = atoi(campo);
        
        campo = strtok(NULL, "#");
        citas[ocupacion].fechaHora.mes = atoi(campo);
        
        campo = strtok(NULL, "#");
        citas[ocupacion].fechaHora.ano = atoi(campo);
        
        campo = strtok(NULL, "#");
        citas[ocupacion].fechaHora.hora = atoi(campo);
        
        campo = strtok(NULL, "#");
        citas[ocupacion].fechaHora.minuto = atoi(campo);
        
        campo = strtok(NULL, "#");
        citas[ocupacion].tipo=campo[0];
        
        ocupacion++;
    }
 }*/
/*char *buscaYSustituye(char *buscada, char *sustituta){
	char cadena [1024*1024];
	FILE *prueba;
	prueba=fopen("ho.txt","r");
	char entrada[1024]; //Aqui se guarda lo que esá en el fichero
	char *aux;
	while(fgets(entrada,1024,prueba)!=NULL){
		strstr(entrada,buscada);
		strcpy(destino,sustituta);
		//printf("nombre=%s\t nif=%s\t deuda=%s\t",nombre,nif,deuda);
	}
	fclose(prueba);
	
}*/

/*Estimado #nombre con NIF: #nif le recordamos que le vamos a partir las piernas como no pague su deuda de #deuda .*/

void pruebas(/*char*destino,*/char *sustituta,char *buscada){
	FILE *prueba;
	prueba=fopen("ho.txt","r");
	char entrada[1024];
	char entrada2[1024];
	char destino[1024*1024];
	int lonPalabra;
	char *ptr;
	fgets(entrada2,1024,prueba);
	while(fgets(entrada,1024,prueba)!=NULL){
		if(entrada=='#'){
			
		}else{
			strcpy(destino,entrada);
		}
		ptr=strstr(entrada,buscada);
		lon=strlen(buscada);
		
	}
	fclose(prueba);
	printf("%d",lon);
}

int main(int argc, char *argv[]) {
	pruebas("matame","#nombre");
	/*FILE *prueba;
	prueba=fopen("ho.txt","r");
	char entrada[1024];
	char nombre[128]="#nombre";
	char nif[16]="#nif";
	char deuda[32]="#deuda";
	char *puntero;
	while(fgets(entrada,1024,prueba)!=NULL){
		strstr(entrada,nombre);
		strstr(entrada,nif);
		strstr(entrada,deuda);
		printf("nombre=%s\t nif=%s\t deuda=%s\t",nombre,nif,deuda);
	}*/
	return 0;
}
