#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define FALSE 0
#define TRUE  1
#define MAX_CONTACTOS 20
#define MAX_ENTRADA   128
typedef int boolean;

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

//Arrays de Contactos con informaci0n
Contacto contactos[MAX_CONTACTOS];

//Numero de contactos en la agenda
int ocupacion = 0;
//Ultima version de la conversion a json de nuestro struct

void deStructAJSON(Contacto c, char *json) {
    int i;
    FILE* jsonDef;
    //Abro fichero donde voy a guardar el json
    jsonDef = fopen("JSON2.txt", "w");
    //Añado al principio este trozo de texto porque es la única vez que aparece en todo el json
    fprintf(jsonDef, "{\"contactos\":[");
    for (i = 0; i < ocupacion; i++) {
        //Recorro nuestro array de contactos guardandolo en c
        c = contactos[i];
        //Si la ocupacion es igual a la posicion del ultimo elemento del array, por tanto indicando ser el utmio contacto, entonces...
        if (ocupacion - 1 == i) {
            //Escribo los valores normales en el archivo
            fprintf(jsonDef, json, c.nombre, c.telefono,
                    c.fechaNacimiento.dia,
                    c.fechaNacimiento.mes,
                    c.fechaNacimiento.ano,
                    c.tipo);
            //Y como es el ultimo le añado al final la llave de cierre y el corchete de cierre
            fprintf(jsonDef, "]}");
        } else {
            //Si no es el utlimo, lo cual engloba al resto, se coloca una coma despues de }
            fprintf(jsonDef, json, c.nombre, c.telefono,
                    c.fechaNacimiento.dia,
                    c.fechaNacimiento.mes,
                    c.fechaNacimiento.ano,
                    c.tipo);
            fprintf(jsonDef, ",");
        }

    }
    //Cierro el fichero
    fclose(jsonDef);
    printf("Exportacion Completada\n");
}

void exportDeFicheroJSON() {
    //Esta es la cadena base para sustituir los "%" por los valores correspondientes almacenados en el array
    char cadenaJson [1024] = "{\"nombre\":\"%s\",\"telefono\":%ld,\"fechaNacimiento\":{\"dia\":%d,\"mes\":%d,\"anho\":%d},\"tipo\":\"%c\"}";
    //Llamo a mi funcion pasandole nuestros contactos y la cadena base
    deStructAJSON(contactos[ocupacion], cadenaJson);
}

void importarJSON(const char *json, Contacto *pc) {
    char jsonVar[4096];
    char* campo;
    //Copio del const char a una cadena normal
    strcpy(jsonVar, json);
    //Busqueda Nombre
    //El if es para que no me esté parseando el mismo contacto todo el rato
    if (ocupacion == 0) {
        campo = strtok(jsonVar, ":");
    } else {
        campo = strtok(NULL, ":");
    }
    campo = strtok(NULL, "\"");
    strcpy(pc->nombre, campo);
    //Busqueda tlfn
    campo = strtok(NULL, ":");
    campo = strtok(NULL, ",");
    pc->telefono = atol(campo);
    //Busqueda fecha: dia
    campo = strtok(NULL, "{");
    campo = strtok(NULL, ":");
    campo = strtok(NULL, ",");
    pc->fechaNacimiento.dia = atoi(campo);
    //Busqueda fecha: mes
    campo = strtok(NULL, ":");
    campo = strtok(NULL, ",");
    pc->fechaNacimiento.mes = atoi(campo);
    //Busqueda fecha: ano
    campo = strtok(NULL, ":");
    campo = strtok(NULL, "}");
    pc->fechaNacimiento.ano = atoi(campo);
    campo = strtok(NULL, ":");
    campo = strtok(NULL, "\"");
    pc->tipo = campo[0];
    ocupacion++;

}

void deJsonAMemoria() {
    FILE* importJson;
    //Abro fichero en modo lectura
    importJson = fopen("JSON2.txt", "r");
    char jsonRaw[2048];
    //Paso el json del fichero a una variable para trabajar con el
    while (fgets(jsonRaw, 1024, importJson) != NULL) {
        fscanf(importJson, "%s", jsonRaw);
    }
    fclose(importJson);
    //El principio donde pone contactos molesta, fuera
    char *apuntador;
    char json[2048];
    apuntador = strtok(jsonRaw, "[");
    apuntador = strtok(NULL, "]");
    //Paso a una variable el json limpio de "{"contactos":["
    strcpy(json, apuntador);
    //printf("%s",json);

    //Inicio variables de contadores
    int i = 0;
    int longitud;
    int puntador = 0;
    int contLlave = 0;
    //Esta variable cuenta los ":" de 1 solo contacto
    int cont2PuntosContact = 0;
    longitud = strlen(json);
    //Esta son los ":" totales en todo el json
    int cont2PuntosTotal = 0;
    //Con este for recorro todo el json
    for (puntador = 0; puntador < longitud; puntador++) {
        //En este if aumento el contador de los dos puntos cada vez que encuentra uno
        if (json[puntador] == ':') {
            cont2PuntosTotal++;
        }
    }
    for (puntador = 0; puntador < longitud; puntador++) {

        if (json[puntador] == '}') {
            contLlave++;
        }

        if (json[puntador] == ':') {
            cont2PuntosContact++;
        }
        if (contLlave == 2) {
            break;
        }
    }
    //Cuando salga del for tendré que tener el numero total de los dos puntos en el json
    //y lo divido por el numero de dos puntos que hay en un contacto, lo que hay entre { y }
    cont2PuntosTotal = cont2PuntosTotal / cont2PuntosContact;
    //Así, me dará el numero de contactos que hay en el json
    //Recorro el numero de contactos del json para que me lo parsee
    for (i = 0; i < cont2PuntosTotal; i++) {
        //Le paso mi json y los contactos
        importarJSON(json, &contactos[ocupacion]);
    }
    printf("Importacion Completada\n");
}
//Funciones necesarias para las comprobaciones

void listarContactos() {
    int i;
    for (i = 0; i < ocupacion; i++) {

        printf("%d. %s\n", i, contactos[i].nombre);
    }
    printf("\n");
}

void detalleContacto() {
    listarContactos();

    int idBuscado;
    char cadena[128];
    Contacto buscado;

    printf("\n");
    printf("De que contacto quieres info?:");
    fflush(stdin);
    fgets(cadena, MAX_CONTACTOS, stdin);
    idBuscado = atoi(cadena);

    if (idBuscado >= 0 && idBuscado < ocupacion) {
        buscado = contactos[idBuscado];
        printf("Id: %d\n", idBuscado);
        printf("Nombre:%s\n", buscado.nombre);
        printf("Telefono:%ld\n", buscado.telefono);
        printf("Fecha Nacimiento:%02d/%02d/%04d\n",
                buscado.fechaNacimiento.dia,
                buscado.fechaNacimiento.mes,
                buscado.fechaNacimiento.ano);

        switch (buscado.tipo) {
            case 'a':
                printf("Tipo: Amigo\n");
                break;
            case 'e':
                printf("Tipo: Enemigo\n");
                break;
            case 'f':
                printf("Tipo: Familiar\n");
                break;
            case 't':
                printf("Tipo: Trabajo\n");
                break;
        }
    } else {

        printf("No hay ningun contacto con ese Id\n");
    }

}

void guardarDatos() {
    int i;
    FILE* pfichero;
    Contacto c;
    pfichero = fopen("contactos.txt", "w");
    for (i = 0; i < ocupacion; i++) {

        c = contactos[i];
        fprintf(pfichero, "%s#%ld#%02d#%02d#%04d#%c\n", c.nombre, c.telefono,
                c.fechaNacimiento.dia,
                c.fechaNacimiento.mes,
                c.fechaNacimiento.ano,
                c.tipo);
    }
    fclose(pfichero);
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
    fclose(pfichero);
}

int main(int argc, char** argv) {
    boolean salir;
    char opcion;

    cargarDatos();
    do {
        printf("======= AGENDA DE CONTACTOS =======\n");
        printf("\n");
        printf("1.Listar contactos\n");
        printf("2.Detalle contacto\n");
        printf("3.Importar JSON\n");
        printf("4.Exportar JSON\n");
        printf("5.Salir\n");
        printf("\n");
        printf("Elige una opcion: ");

        //Esperamos opcion y actuamos en consecuencia
        fflush(stdin);
        scanf("%c", &opcion);
        salir = FALSE;
        switch (opcion) {
            case '1':
                listarContactos();
                break;
            case '2':
                detalleContacto();
                break;
            case '3':
                deJsonAMemoria();
                break;
            case '4':
                exportDeFicheroJSON();
                break;
            case '5':
                guardarDatos();
                salir = TRUE;
                break;
            default:
                printf("Opcion no valida\n");
        }
        system("pause");
        printf("\n\n");
        system("cls");


    } while (!salir);
    return (EXIT_SUCCESS);
}

