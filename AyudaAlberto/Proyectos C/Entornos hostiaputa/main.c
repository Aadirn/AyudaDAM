#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define FALSE 0
#define TRUE  1
#define MAX_CONTACTOS 20

typedef int boolean;

typedef struct {
    int dia;
    int mes;
    int anho;
} Fecha;

typedef struct {
    char nombre[50];
    long telefono;
    Fecha fechaNacimiento;
    char tipo;
} Contacto;


Fecha crearFecha(const char* cadena);
void cargarDatos();
void nuevoContacto();
void listarContactos();
void borrarContacto();
void detalleContacto();
void guardarDatos();
boolean esTipoValido();
void checkCumpleanieros();
boolean esContrasenaValida(char *cadena);

//Arrays de Contactos con información
Contacto contactos[MAX_CONTACTOS];

//Número de contactos en la agenda
int ocupacion = 0;

int main(int argc, char** argv) {

    boolean salir;
    char opcion;

    //comprabar la contrasena
    char entrada[1024];
    int result;
    int contador = 0;
    while (1) {
        printf("Constrasenia: ");
        fflush(stdin);
        fgets(entrada, 1024, stdin);
        entrada[strcspn(entrada, "\n")] = '\0'; // para quitar el \n que crea fgets
        result = esContrasenaValida(entrada);
        if (result == TRUE) {
            break;
        }
        contador++;
        if (contador == 3) {
            printf("Demasiados fallos (-1)");
            return;
        }
    }
    //-----
    cargarDatos();
    checkCumpleanieros();
    //Mostramos el menu y esperamos opción hasta que se elija 5.Salir
    do {
        //Mostramos menú y pedimos opción
        printf("======= AGENDA DE CONTACTOS =======\n");
        printf("\n");
        printf("1.Nuevo contacto\n");
        printf("2.Listar contactos\n");
        printf("3.Borrar contacto\n");
        printf("4.Detalle contacto\n");
        printf("5.Salir\n");
        printf("\n");
        printf("Elige una opcion: ");

        //Esperamos opcion y actuamos en consecuencia
        fflush(stdin);
        scanf("%c", &opcion);
        salir = FALSE;
        switch (opcion) {
            case '1':
                system("cls");
                nuevoContacto();
                break;
            case '2':
                system("cls");
                listarContactos();
                break;
            case '3':
                system("cls");
                borrarContacto();
                break;
            case '4':
                system("cls");
                detalleContacto();
                break;
            case '5':
                system("cls");
                guardarDatos();
                salir = TRUE;
                break;
            default:
                printf("Opcion no valida\n");
        }

    } while (!salir);

    printf("Bye, bye. Gracias por usar GESTION DE CONTACTOS\n");
    return (EXIT_SUCCESS);
}

/*
int esContrasenaValida(char *cadena) {
    char password[] = "P@ssw0rd";
    int result;
    result = strcmp(cadena, password); //los comprabamos
    if (result < 0 || result > 0) {
        return FALSE;
    } else {
        return TRUE;
    }

}
 */

boolean esContrasenaValida(char *cadena) {
    FILE* pfichero;
    pfichero = fopen("top-secret.txt", "r");
    char linea[1024];
    char *campo;
    const char s[2] = "#";
    char password[1024];
    while (fgets(linea, 1024, pfichero) != NULL) {
        strcpy(password, strrev(linea)); //usamos un metodo de string.h para revertir la palabra
    }

    int result;
    result = strcmp(cadena, password); //los comprabamos
    if (result < 0 || result > 0) {
        return FALSE;
    } else {
        return TRUE;
    }

}

Fecha crearFecha(const char *cadena) {
    //----                              //parsear fecha
    char tmp[1024];
    strcpy(tmp, cadena);
    char* token = strtok(tmp, "/"); //fecha[0] dia [1] mes [2] año 
    char fecha[3][124];
    int i = 0;
    while (token != NULL) {
        strcpy(fecha[i], token);
        token = strtok(NULL, "/");
        i++;
    }
    Fecha fechaErronea;
    fechaErronea.dia = 0;
    fechaErronea.mes = 0;
    fechaErronea.anho = 0;
    if (i > 3) {
        return fechaErronea;
    }
    //------
    int anho = atoi(fecha[2]);
    int mes = atoi(fecha[1]);
    int dia = atoi(fecha[0]);
    Fecha nueva;
    nueva.dia = dia;
    nueva.mes = mes;
    nueva.anho = anho;
    //--
    if (anho <= 999) { //comprabar si la fecha es valida
        return fechaErronea;
    }
    if (mes < 1 || mes > 12) {
        return fechaErronea;
    }
    switch (mes) {
        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            if (dia <= 0 || dia > 31) {
                return fechaErronea;
            }
            break;
        case 4: case 6: case 9: case 11:
            if (dia <= 0 || dia > 30) {
                return fechaErronea;
            }
            break;
        case 2:
            if (dia <= 0 || dia > 28) {
                if (dia == 29 && (anho % 400 == 0 || (anho % 4 == 0 && anho % 100 != 0))) {
                    return nueva;
                }
                return fechaErronea;
            }
    }
    return nueva;
}

void nuevoContacto() {
    char entrada[1024];
    char tmp[1024];
    Contacto nuevo;

    //Comprobamos que la agenda no está llena
    if (ocupacion >= MAX_CONTACTOS) {
        printf("La agenda esta llena. Lo sentimos.\n");
        return;
    }

    printf("Nombre?: ");
    fflush(stdin);
    fgets(entrada, 1024, stdin);
    entrada[strcspn(entrada, "\n")] = '\0'; // para quitar el \n que crea fgets
    strcpy(nuevo.nombre, entrada);
    //----  
    /*
            printf("Telefono?: ");
            fflush(stdin);
            gets(entrada);
            nuevo.telefono = atol(entrada);
     */
    printf("Telefono?: ");
    fflush(stdin);
    fgets(entrada, 1024, stdin);
    nuevo.telefono = atol(entrada);
    //----  
    /*
        printf("Fecha Nacimiento? (dd/mm/aaaa): ");
        fflush(stdin);
        gets(entrada);
        strcpy(nuevo.fechaNacimiento, entrada);
     */

    Fecha fecha;
    while (1) {
        printf("Fecha Nacimiento? (dd/mm/aaaa): ");
        fflush(stdin);
        fgets(entrada, 1024, stdin);
        fecha = crearFecha(entrada);
        if (fecha.dia != 0 && fecha.mes != 0 && fecha.anho != 0) {
            break;
        }
    }
    nuevo.fechaNacimiento = fecha;
    //----  
    do {
        printf("Tipo Contacto? (a)migo, (e)enemigo, (f)amiliar, t(trabajo): ");
        fflush(stdin);
        strcpy(tmp, fgets(entrada, 1024, stdin));
    } while (!esTipoValido(tmp));
    nuevo.tipo = entrada[0];
    //---
    contactos[ocupacion] = nuevo;
    ocupacion++;
}

void cargarDatos() {
    //Abrimos el fichero contactos.txt en modo lectura

    FILE* pfichero;
    pfichero = fopen("contactos.txt", "r");
    char linea[1024];
    char *campo;
    const char s[2] = "#";

    while (fgets(linea, 1024, pfichero) != NULL) {
        campo = strtok(linea, s);
        strcpy(contactos[ocupacion].nombre, campo);
        campo = strtok(NULL, s);
        contactos[ocupacion].telefono = atol(campo);
        campo = strtok(NULL, s);
        contactos[ocupacion].fechaNacimiento.dia = atoi(campo);
        campo = strtok(NULL, s);
        contactos[ocupacion].fechaNacimiento.mes = atoi(campo);
        campo = strtok(NULL, s);
        contactos[ocupacion].fechaNacimiento.anho = atoi(campo);
        campo = strtok(NULL, s);
        contactos[ocupacion].tipo = campo[0];

        ocupacion++;

    }


}

void listarContactos() {
    int i;
    for (i = 0; i < ocupacion; i++) {
        printf("%d. %s\n", i, contactos[i].nombre);
    }
    printf("\n");
}

void borrarContacto() {
    int i, idBorrar;
    char cadena[128];

    printf("\n");
    printf("Que contacto quieres borrar?:");
    fflush(stdin);
    fgets(cadena, 128, stdin);
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

    int idBuscado;
    char cadena[128];
    Contacto buscado;

    printf("\n");
    printf("De que contacto quieres info?:");
    fflush(stdin);
    fgets(cadena, 128, stdin);
    idBuscado = atoi(cadena);

    if (idBuscado >= 0 && idBuscado < ocupacion) {
        buscado = contactos[idBuscado];
        printf("Id: %d\n", idBuscado);
        printf("Nombre:%s\n", buscado.nombre);
        printf("Telefono:%ld\n", buscado.telefono);
        printf("Fecha Nacimiento:%d/%d/%d\n", buscado.fechaNacimiento.dia, buscado.fechaNacimiento.mes, buscado.fechaNacimiento.anho);

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
        fprintf(pfichero, "%s#%ld#%d#%d#%d#%c\n", c.nombre, c.telefono, c.fechaNacimiento.dia, c.fechaNacimiento.mes, c.fechaNacimiento.anho, c.tipo);
    }
    fclose(pfichero);

    printf("Has elegido Salir\n");
}

boolean esTipoValido(char *cadena) {
    switch (cadena[0]) {
        case 'a':
        case 'e':
        case 'f':
        case 't':
            return TRUE;
        default:
            return FALSE;
    }
}

void checkCumpleanieros() {
    time_t rawtime; //conseguir fecha de ahora
    struct tm * timeinfo;

    time(&rawtime);
    timeinfo = localtime(&rawtime);
    int anho = timeinfo ->tm_year + 1900;
    int mes = timeinfo->tm_mon + 1;
    int dia = timeinfo->tm_mday;
    printf("Hoy es: %d/%d/%d", dia, mes, anho);

    int contador = 0;
    int i;
    for (i = 0; i < ocupacion; i++) {
        if ((contactos[i].fechaNacimiento.anho == anho) && (contactos[i].fechaNacimiento.mes == mes) && (contactos[i].fechaNacimiento.dia == dia)) {
            printf("\n%d.%s esta celebrando el cumpleanios!", i, contactos[i].nombre);
            contador++;
        }
    }

    if (contador == 0) {
        printf("\nNadie cumple hoy :( ");
    }

    printf("\n");


}

