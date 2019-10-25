#include <stdio.h>
#include <stdlib.h>
#define MAX_CITAS 128
#define FALSE 0
#define TRUE  1
typedef int boolean;

typedef struct {
	int dia;
	int mes;
	int ano;
	int hora;
	int minuto;
} Instante;

typedef struct {
    char lugar[128];
    char persona[64];
    Instante fechaHora;
    char tipo; //a	mistad f	amiliar t	rabajo o	tro
} Cita;
Cita citas [MAX_CITAS];/*={	{"Clinica Dental","Rodrigo Pompom","23/10/1999 23:59",'f'},
							{"Casa","Pico Pala","12/08/1990 18:23",'o'}
																};*/
 int ocupacion;
 
 
 boolean esTipoValido(char c){
 	return (c == 'a' || c == 'f' || c == 't' || c == 'o');
 }
 
 /*char desencriptador(char *contrasena){
 	int i;
 	int tamanho = strlen(contrasena);
 	int caracter;
 	char desencriptada[tamanho];
    for(i=0;i<tamanho;i++){
    	caracter = contrasena[i];
    	desencriptada[i] = caracter+1;
	}
	desencriptada[strlen(desencriptada) - 3] = '\0';
	printf("%s",desencriptada);
	return desencriptada;
 }
 
 boolean esContrasenaValida(char *cadena){
 	char miContrasena[1024];
 	int i;
    
    FILE *fichero;
    if( (fichero = fopen("secreto.txt","r")) == NULL){
        printf("Error. No se ha podido leer el fichero de contrasena");
        exit (-4);
    }
    fgets(miContrasena,1024,fichero);
    fclose(fichero);
    return (strcmp(cadena, desencriptador(miContrasena)) == 0);
 }*/
 
 void guardaDatos(){
 	int i;
    FILE* pfichero;
    Cita s;
    pfichero = fopen("citas.txt", "w");
    for (i = 0; i < ocupacion; i++) {

        s=citas[i];
        fprintf(pfichero, "%s#%s#%02d#%02d#%04d#%02d#%02d#%c\n", s.lugar,s.persona,s.fechaHora.dia,s.fechaHora.mes,
		s.fechaHora.ano,s.fechaHora.hora,s.fechaHora.minuto,s.tipo);
    }
    fclose(pfichero);
 }
 void cargaDatos(){
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
 }
 
 boolean esAnoBisiesto(int ano) {
    return (((ano % 4 == 0) && !(ano % 100) == 0) || (ano % 400) == 0);
}
 
 boolean esFechaValida(int dia, int mes, int ano,int hora, int minutos) {

    switch (mes) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            if (dia >= 1 && dia <= 31) {
                if(hora<24&&minutos<60){
    				return TRUE;
            } else {
                return FALSE;
            }
        }
        case 4:
        case 6:
        case 9:
        case 11:
            if (dia >= 1 && dia <= 30) {
                if(hora<24&&minutos<60){
    				return TRUE;
            } else {
                return FALSE;
            }
            }
        case 2:
            if ((dia >= 1 && dia <= 29 && esAnoBisiesto(ano)) ||
                    (dia >= 1 && dia <= 28 && !esAnoBisiesto(ano))) {
                if(hora<24&&minutos<60){
    				return TRUE;
            } else {
                return FALSE;
            }
            }
        default:
            return FALSE;
    }
    if(hora>=24||minutos>=60){
    	return FALSE;
	}else{
    return TRUE;
}
}
 
 Instante creaInstante(char *strInst) {

    Instante nuevo;

    nuevo.dia = nuevo.mes = nuevo.ano = nuevo.hora = nuevo.minuto = 0;


    int dia = atoi(strtok(strInst, "/")); //12/12/1212 20:10
    int mes = atoi(strtok(NULL, "/"));
    int ano = atoi(strtok(NULL, " "));
    int hora = atoi(strtok(NULL, ":"));
    int minuto = atoi(strtok(NULL, ""));

    if (esFechaValida(dia,mes,ano,hora,minuto)) {

        nuevo.dia = dia;
        nuevo.mes = mes;
        nuevo.ano = ano;
        nuevo.hora = hora;
        nuevo.minuto = minuto;
    }

    return nuevo;
}
 
 void nuevaCita(){
 	char entrada[1024];
    Cita nueva;

    //Comprobacion de si esta lleno de citas o no
    if (ocupacion >= MAX_CITAS) {
        printf("No caben mas citas en la memoria, lo siento.\n");
        return;
    }

    printf("Lugar?: ");
    fflush(stdin);
    gets(entrada);
    strcpy(nueva.lugar, entrada);

    printf("Persona?: ");
    fflush(stdin);
    gets(entrada);
    strcpy(nueva.persona,entrada);

    Instante nuevo;
    do {
        printf("Fecha Cita? (dd/mm/aaaa HH:MM): ");
        fflush(stdin);
        fgets(entrada, 1024, stdin);
        entrada[strlen(entrada) - 1] = '\0';
        nuevo = creaInstante(entrada);
        nueva.fechaHora = nuevo;
    } while (nuevo.ano == 0);


    do {
        printf("Tipo Cita? (a)mistad, (f)amiliar, t(trabajo), (o)tro: ");
        fflush(stdin);
        gets(entrada);
        nueva.tipo = entrada[0];
    } while (!esTipoValido(entrada[0]));

    citas[ocupacion++] = nueva;
 }
 void listarCitas(){
 	int i;
    for (i = 0; i < ocupacion; i++) {
		//printf("paso por aqui");
        printf("%d. %s\n", i, citas[i].lugar);
    }
    printf("\n");
}
 void detalleCita(){
 	listarCitas();
 	int id;
 	char cadena[128];
 	Cita buscado;

    printf("\n");
    printf("De que cita desea detalles?:");
    fflush(stdin);
    fgets(cadena, MAX_CITAS, stdin);
    id = atoi(cadena);

    if (id >= 0 && id < ocupacion) {
        buscado = citas[id];
        printf("Id: %d\n", id);
        printf("Lugar:%s\n", buscado.lugar);
        printf("Persona:%s\n", buscado.persona);
        printf("Fecha Cita:%02d/%02d/%04d %02d:%02d\n",
                buscado.fechaHora.dia,
				buscado.fechaHora.mes,
				buscado.fechaHora.ano,
				buscado.fechaHora.hora,
				buscado.fechaHora.minuto);
        /*printf("Fecha Cita:%s\n",
                buscado.fecha);*/

        switch (buscado.tipo) {
            case 'a':
                printf("Tipo: Amistad\n");
                break;
            case 'o':
                printf("Tipo: Otro\n");
                break;
            case 'f':
                printf("Tipo: Familiar\n");
                break;
            case 't':
                printf("Tipo: Trabajo\n");
                break;
        }
    } else {

        printf("No se encuentra cita con ese ID\n");
    }
 	
 }
 void borrarCita(){
 	listarCitas();

    int i, idBorrar;
    char cadena[1024];

    printf("\n");
    printf("Que contacto quieres borrar?:");
    fflush(stdin);
    fgets(cadena, MAX_CITAS, stdin);
    idBorrar = atoi(cadena);

    if (idBorrar >= 0 && idBorrar < ocupacion) {
        for (i = idBorrar; i <= ocupacion - 2; i++) {
            citas[i] = citas[i + 1];
        }
        ocupacion--;
    } else {

        printf("No hay ningun contacto con ese Id\n");
    }
}
 
int main(int argc, char *argv[]) {
	cargaDatos();
	boolean salir;
    char opcion;
    char cadena[128];
   /* do{
    	printf("Contrasena?: ");
        fgets(cadena, 1024, stdin);
        if(esContrasenaValida(cadena)) break;
    }while(!esContrasenaValida(cadena));*/
    do {
        printf("***********CITAS***********\n");
        printf("\n");
        printf("1.Nueva cita\n");
        printf("2.Listar citas\n");
        printf("3.Detalle cita\n");
        printf("4.Borrar cita\n");
        printf("5.Salir\n");
        printf("\n");
        printf("Elige una opcion: ");

        fflush(stdin);
        scanf("%c", &opcion);
        salir = FALSE;
        switch (opcion) {
            case '1':
                nuevaCita();
                break;
            case '2':
                listarCitas();
                break;
            case '3':
                detalleCita();
                break;
            case '4':
                borrarCita();
                break;
            case '5':
            	printf("Guardando datos\n");
                guardaDatos();
                salir = TRUE;
                break;
            default:
                printf("Opcion no valida\n");
        }
        system("pause");
        printf("\n\n");
        system("cls");
        
        
    } while (!salir);

    printf("Ha salido, hasta la proxima\n");
	return 0;
}
