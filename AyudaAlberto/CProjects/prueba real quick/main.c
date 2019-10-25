/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: Castelao
 *
 * Created on 14 de marzo de 2019, 13:02
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define FALSE 0
#define TRUE  1
#define MAX_CONTACTOS 20
#define MAX_ENTRADA   128

typedef struct {
    int dia;
    int mes;
    int ano;
} Fecha;

typedef struct {
    char nombre[50];
    long telefono;
    Fecha fechaNacimiento;
    char tipo;
} Contacto;

//Arrays de Contactos con informaciÃ³n
Contacto contactos[MAX_CONTACTOS];

//Numero de contactos en la agenda
int ocupacion = 0;

void deStructAJSON(Contacto c){
    //printf("HOLita");
    int i;
    int leng;
    char jsonRaw[1024*1024];
    char* final="]}";
    FILE* pfichero;
    FILE* pfichero2;
    pfichero=fopen("miespana.txt","w");
    //printf("HOLita");
    for(i=0;i<ocupacion;i++){
    	c=contactos[i];
    	fprintf(pfichero, "\"nombre\":\"%s\",\"telefono\":\"%ld\",\"fechaNacimiento\": {\"dia\":%d,\"mes\":%d,\"anho\":%d},\"tipo\":%c},", c.nombre, c.telefono,
                c.fechaNacimiento.dia,
                c.fechaNacimiento.mes,
                c.fechaNacimiento.ano,
                c.tipo);
        //strcpy(json,"\"nombre\":\"%s\",\"telefono\":\"%ld\",\"fechaNacimiento\": {\"dia\":%d,\"mes\":%d,\"anho\":%d},\"tipo\":%c},");
        //printf("%s",json);
	}
    fclose(pfichero);
    //printf("HOLA2");
    fgets(jsonRaw,1024*1024,pfichero);
    //printf("HOLA3");
    char* pos=strstr(strrev(jsonRaw),",");
    leng=strlen(jsonRaw);
    for(i=0;i<leng;i++){
    printf("%c",jsonRaw[i]);
    }
    strrev(jsonRaw);
    printf("HOLeco");
    strcpy(pos,final);
    printf("HOLA4");
    pfichero2=fopen("JSON.txt","a");
    fprintf(pfichero2, "{\"contactos\": [");
    printf("HOLA5");
    fprintf(pfichero2,jsonRaw);
    fclose(pfichero2);
    printf("HOLA6");
    
    
    //printf("%s",json);
	/*fclose(pfichero);*/
}




void cargarDatos() {

    //Abrimos el fichero contactos.txt en modo lectura
    ocupacion = 0;
    FILE* pfichero;
    pfichero = fopen("contactos.txt", "r");
    char linea[MAX_ENTRADA];
    char *campo;
    while (fgets(linea, 1024, pfichero) != NULL) {
        //Extraemos el nombre (campo 1Âº del registro)

        campo = strtok(linea, "#");
        strcpy(contactos[ocupacion].nombre, campo);
        //Extraemos el telÃ©fono (campo 2Âº del registro)
        campo = strtok(NULL, "#");
        contactos[ocupacion].telefono = atol(campo);
        //Extraemos el dÃ­a de la fecha (campo 3Âº del registro)
        campo = strtok(NULL, "#");
        contactos[ocupacion].fechaNacimiento.dia = atoi(campo); 
        //Extraemos el mes de la fecha (campo 4Âº del registro)
        campo = strtok(NULL, "#");
        contactos[ocupacion].fechaNacimiento.mes = atoi(campo); 
        //Extraemos el aÃ±o de la fecha (campo 5Âº del registro)
        campo = strtok(NULL, "#");
        contactos[ocupacion].fechaNacimiento.ano = atoi(campo); 
        //Extraemos el tipo (campo 4Âº del registro)
        campo = strtok(NULL, "#");
        contactos[ocupacion].tipo = campo[0]; 
        //Incrementamos el contador de contactos de la agenda
        ocupacion++;
    }

}

int main(int argc, char** argv) {
    cargarDatos();
    Contacto c;
    deStructAJSON(c);
    return (EXIT_SUCCESS);
}

