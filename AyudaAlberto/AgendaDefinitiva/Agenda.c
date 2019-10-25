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
int ocupacion;



/*
boolean esContrasenaValida(char *contrasena) {
    char miContrasena[] = "P@ssw0rd";

    return (strcmp(contrasena, miContrasena) == 0);
}
 */
//Metodo que comienza la exportacion del struct a formato JSON



void deStructAJSON(Contacto c, char *json) {
    int i;
    FILE* jsonDef;
    jsonDef = fopen("JSON2.txt", "w");
    fprintf(jsonDef, "{\"contactos\":[");
    for (i = 0; i < ocupacion; i++) {
        c = contactos[i];
        if (ocupacion - 1 == i) {
            fprintf(jsonDef, json, c.nombre, c.telefono,
                    c.fechaNacimiento.dia,
                    c.fechaNacimiento.mes,
                    c.fechaNacimiento.ano,
                    c.tipo);
            fprintf(jsonDef, "]}");
        } else {
            fprintf(jsonDef, json, c.nombre, c.telefono,
                    c.fechaNacimiento.dia,
                    c.fechaNacimiento.mes,
                    c.fechaNacimiento.ano,
                    c.tipo);
            fprintf(jsonDef, ",");
        }

    }
    fclose(jsonDef);
    printf("Exportacion Completada\n");
}
void exportDeFicheroJSON() {
    char cadenaJson [1024] = "{\"nombre\":\"%s\",\"telefono\":%ld,\"fechaNacimiento\":{\"dia\":%d,\"mes\":%d,\"anho\":%d},\"tipo\":\"%c\"}";
    deStructAJSON(contactos[ocupacion], cadenaJson);
}
    
    void deJsonAMemoria() {
    FILE* importJson;
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
    char jsoncpy1[2048];
    //char jsoncpy2[2048];
    apuntador = strtok(jsonRaw, "[");
    apuntador = strtok(NULL, "]");
    strcpy(json, apuntador);
    
    printf("%s",json);
    //json contiene el json sin el contactos del principio, con una estructura estilo
    /*{"nombre":"Pericolo Spalotes","telefono":666548721,"fechaNacimiento":{"dia":20,"mes":12,"anho":1966},"tipo":"a"},
     * {"nombre":"Puto Json","telefono":365587454,"fechaNacimiento":{"dia":1,"mes":2,"anho":2003},"tipo":"e"}*/
    
    //guardar en otra cadena de caracteres todo hasta que encuentra una llave de cierre(la que yo quiero conio[YATUSAES COMO ENCONTRARLA]) y pasarlo al otro metodo;
    //strcpy(jsoncpy, json);
    int longitud;
    int contContactos;
    int posicion=0;
    int posInic=0;
    int contLlaves=0;
    char* ptr;
    char contacto[1024];
    longitud = strlen(json);
    //el orden siempre va a ser nombre->tlfn->dia->mes->anho->tipo
    for(posicion = 0;posicion<longitud; posicion++){
        if(json[posicion]=='}'){
            contLlaves++;
        }
        if(contLlaves==2){
            strcpy(jsoncpy1,json);
            contLlaves=0;
        }
        
    printf("%s\n\n",jsoncpy1);
		    	
	}
    
    
    
}
//MIRAR EN CASA DE HACER MI VERSION


















/*void deJsonAMemoria() {
    FILE* importJson;
    importJson = fopen("JSON2.txt", "r");
    char jsonRaw[2048];
    //Paso el json del fichero a una variable para tranajar con el
    while (fgets(jsonRaw, 1024, importJson) != NULL) {
        fscanf(importJson, "%s", jsonRaw);
    }
    fclose(importJson);
    //El principio donde pone contactos molesta, fuera
    char *apuntador;
    char json[2048];
    apuntador = strtok(jsonRaw, "[");
    apuntador = strtok(NULL, "]");
    strcpy(json, apuntador);

    char contactoJson[1024];
    int longitudJson = strlen(json);
    int inicio = 0;
    int final = 0;
    int bloques = 0;
    int posicion;
    for (posicion = 0; posicion < longitudJson; posicion++) {
        if (json[posicion] == '{') {
            bloques++;
            if (bloques == 1) {
                inicio = posicion;
            }
        } else if (json[posicion] == '}') {
            bloques--;
            //Inicio tiene que ser distinto a -1, si es 0  no me pilla el contacto anterior
            if (bloques == 0 && inicio != -1) {
                final = posicion;
                memcpy(contactoJson, &json[inicio], final + 1 - inicio);
                importarJSON(contactoJson, &contactos[ocupacion]);
                inicio = 0;
                final = 0;
            }

        }
    }
    
    printf("Importacion Completada\n");


}*/
void importarJSON(const char *json, Contacto *pc){
char jsonVar[4096];
char* campo;
strcpy(jsonVar,json);
    //Busqueda Nombre
    campo = strtok(jsonVar, ":");
    campo = strtok(NULL, "\"");
    strcpy(pc->nombre, campo);
    //Busqueda tlfn
    campo = strtok(NULL, ":");
    campo = strtok(NULL, ",");
    pc->telefono=atol(campo);
    //Busqueda fecha: dia
    campo = strtok(NULL, "{");
    campo = strtok(NULL, ":");
    campo = strtok(NULL, ",");
    pc->fechaNacimiento.dia=atoi(campo);
    //Busqueda fecha: mes
    campo = strtok(NULL, ":");
    campo = strtok(NULL, ",");
    pc->fechaNacimiento.mes=atoi(campo);
    //Busqueda fecha: ano
    campo = strtok(NULL, ":");
    campo = strtok(NULL, "}");
    pc->fechaNacimiento.ano=atoi(campo);
    campo = strtok(NULL, ":");
    campo = strtok(NULL, "\"");
    pc->tipo=campo[0];
    ocupacion++;
    
    
}

boolean esContrasenaValida(char *contrasena) {
    char miContrasena[MAX_ENTRADA];

    FILE *fichero;
    if ((fichero = fopen("top-secret.txt", "r")) == NULL) {
        printf("Error. No se ha podido leer el fichero de contrasena");
        exit(-4); //Error de lectura de fichero de contraseÃ±a
    }
    fgets(miContrasena, MAX_ENTRADA, fichero);
    fclose(fichero);
    //miContrasena[strlen(miContrasena) - 1] = '\0';
    strcpy(miContrasena, strrev(miContrasena));

    return (strcmp(contrasena, miContrasena) == 0);
}

boolean esTipoValido(char c) {
    return (c == 'a' || c == 'e' || c == 'f' || c == 't');
}

boolean esAnoBisiesto(int ano) {
    return (((ano % 4 == 0) && !(ano % 100) == 0) || (ano % 400) == 0);
}

boolean esFechaValida(int dia, int mes, int ano) {

    switch (mes) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            if (dia >= 1 && dia <= 31) {
                return TRUE;
            } else {
                return FALSE;
            }
        case 4:
        case 6:
        case 9:
        case 11:
            if (dia >= 1 && dia <= 30) {
                return TRUE;
            } else {
                return FALSE;
            }
        case 2:
            if ((dia >= 1 && dia <= 29 && esAnoBisiesto(ano)) ||
                    (dia >= 1 && dia <= 28 && !esAnoBisiesto(ano))) {
                return TRUE;
            } else {
                return FALSE;
            }
        default:
            return FALSE;
    }
    return TRUE;
}

Fecha creaFecha(char *strFecha) {

    Fecha nueva;

    nueva.dia = nueva.mes = nueva.ano = 0;


    int dia = atoi(strtok(strFecha, "/"));
    int mes = atoi(strtok(NULL, "/"));
    int ano = atoi(strtok(NULL, "/"));

    if (esFechaValida(dia, mes, ano)) {

        nueva.dia = dia;
        nueva.mes = mes;
        nueva.ano = ano;
    }

    return nueva;
}

void nuevoContacto() {

    char entrada[1024];
    Contacto nuevo;

    //Comprobamos que la agenda no esta llena
    if (ocupacion >= MAX_CONTACTOS) {
        printf("La agenda esta llena. Lo sentimos.\n");
        return;
    }

    printf("Nombre?: ");
    fflush(stdin);
    fgets(entrada, MAX_ENTRADA, stdin);
    entrada[strlen(entrada) - 1] = '\0';
    strcpy(nuevo.nombre, entrada);

    printf("Telefono?: ");
    fflush(stdin);
    fgets(entrada, MAX_ENTRADA, stdin);
    nuevo.telefono = atol(entrada);

    Fecha nueva;
    do {
        printf("Fecha Nacimiento? (dd/mm/aaaa): ");
        fflush(stdin);
        fgets(entrada, MAX_ENTRADA, stdin);
        entrada[strlen(entrada) - 1] = '\0';
        nueva = creaFecha(entrada);
        nuevo.fechaNacimiento = nueva;
    } while (nueva.ano == 0);


    do {
        printf("Tipo Contacto? (a)migo, (e)enemigo, (f)amiliar, t(trabajo): ");
        fflush(stdin);
        fgets(entrada, MAX_ENTRADA, stdin);
        nuevo.tipo = entrada[0];
    } while (!esTipoValido(entrada[0]));

    contactos[ocupacion++] = nuevo;
}

void listarContactos() {
    int i;
    for (i = 0; i < ocupacion; i++) {

        printf("%d. %s\n", i, contactos[i].nombre);
    }
    printf("\n");
}

void borrarContacto() {
    listarContactos();

    int i, idBorrar;
    char cadena[MAX_ENTRADA];

    printf("\n");
    printf("Que contacto quieres borrar?:");
    fflush(stdin);
    fgets(cadena, MAX_CONTACTOS, stdin);
    idBorrar = atoi(cadena);

    if (idBorrar >= 0 && idBorrar < ocupacion) {
        for (i = idBorrar; i <= ocupacion - 2; i++) {
            contactos[i] = contactos[i + 1];
        }
        ocupacion--;
    } else {

        printf("No hay ningun contacto con ese Id\n");
    }
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

int main(int argc, char** argv) {
    boolean salir;
    int intentos;
    char cadena[MAX_ENTRADA];
    char opcion;
    Contacto c;
    //Pedimos y comprobamos la contraseÃ±a.
    //DespuÃ©s de 3 intentos fallidos se finaliza el programa
    /*for (intentos=0; ; intentos++) {
        printf("Contrasena?: ");
        fgets(cadena, MAX_ENTRADA, stdin);
        cadena[strlen(cadena) - 1] = '\0';
        if(esContrasenaValida(cadena)) break;
        if(intentos==2) return -1;
    }*/

    //Cargamos datos
    cargarDatos();

    //Mostramos el menu y esperamos opciÃ³n hasta que se elija 5.Salir
    do {
        //Mostramos menÃº y pedimos opciÃ³n
        printf("======= AGENDA DE CONTACTOS =======\n");
        printf("\n");
        printf("1.Nuevo contacto\n");
        printf("2.Listar contactos\n");
        printf("3.Borrar contacto\n");
        printf("4.Detalle contacto\n");
        printf("5.Importar JSON\n");
        printf("6.Exportar JSON\n");
        printf("7.Salir\n");
        printf("\n");
        printf("Elige una opcion: ");

        //Esperamos opcion y actuamos en consecuencia
        fflush(stdin);
        scanf("%c", &opcion);
        salir = FALSE;
        switch (opcion) {
            case '1':
                nuevoContacto();
                break;
            case '2':
                listarContactos();
                break;
            case '3':
                borrarContacto();
                break;
            case '4':
                detalleContacto();
                break;
            case '5':
                deJsonAMemoria();
                break;
            case '6':
                exportDeFicheroJSON();
                break;
            case '7':
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

    printf("Bye, bye. Gracias por usar GESTION DE CONTACTOS\n");

    return (EXIT_SUCCESS);
}


