#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define FALSE 0
#define TRUE  1
#define MAX_CONTACTOS 20

typedef int boolean;

typedef struct{
	int dia;
	int mes;
	int anho;
}Fecha;

typedef struct {
	char nombre[50];
	long telefono;
	Fecha fechaNacimiento;	
	char tipo;
} Contacto;

//Arrays de Contactos con información
Contacto contactos[MAX_CONTACTOS];

//Número de contactos en la agenda
int ocupacion=0;

/*void contraseña valida(char *cadena){
	

}
*/

boolean esTipoValido(char c){
	
	if(c!='a'&c!='f'&c!='t'&c!='e'){
		return FALSE;
	}else{
		return TRUE;
	}
}

boolean esContrasenhaValida(char * cadena){
	
	FILE *pfichero;
	pfichero = fopen("Top-Secret.txt","r");
	char entrada[1024];
	char contrasenha[100];
	while(fgets(entrada,100,pfichero)!=NULL){
		strcpy(contrasenha,strrev(entrada));//strrev da la vuelta a lo hay en el archivo topsecret
		
	}
	
	
	int comparar;
	comparar = strcmp(contrasenha,cadena);
	if(comparar == 0){
		return TRUE;
	}else{
		return FALSE;
		
	}
}

void validarFecha(int dia, int mes, int anho){
	int boolean = 0;
	if(mes>=1 && mes <=12){
	switch(mes){
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			if(dia>=1 && dia<=31){
				boolean = 1;
			}
			break;
		case 4: case 6: case 9: case 11:
			if(dia>=1 && dia<=31){
				boolean = 1;
			}
			break;	
		case 2: 
			if((anho%4 == 0) && (anho%100 == 0) || (anho %400 == 0)){
				if(dia>=1 && dia<=29){
					boolean = 1;
}
				}else{
					if(dia>=1 && dia<=28){
						boolean = 1;
					}
					}
					break;	
}
}
}

void nuevoContacto(){
	
	char entrada[1024];
	Contacto nuevo;
	
	//Comprobamos que la agenda no está llena
	if(ocupacion>=MAX_CONTACTOS){
		printf("La agenda esta llena. Lo sentimos.\n");
		return;
	}
	
	printf("Nombre?: ");
	fflush(stdin);
	gets(entrada);
	strcpy(nuevo.nombre,entrada);
	
	printf("Telefono?: ");
	fflush(stdin);
	gets(entrada);
	nuevo.telefono = atol(entrada);
	
	printf("Fecha Nacimiento: \n\n");
	fflush(stdin);
	printf("Introduzca el Dia: \n");
		gets(entrada);
	nuevo.fechaNacimiento.dia=atoi(entrada);
	printf("Introduzca el mes: \n");
    	gets(entrada);
	nuevo.fechaNacimiento.mes=atoi(entrada);
	printf("Introduzca el anho: \n");
        gets(entrada);
	nuevo.fechaNacimiento.anho=atoi(entrada);
	

	do{
		fflush(stdin);
	
	printf("Tipo Contacto? (a)migo, (e)enemigo, (f)amiliar, t(trabajo): ");
	fflush(stdin);
	gets(entrada);
	}while(!esTipoValido(entrada[0]));
	nuevo.tipo = entrada[0];
	
	contactos[ocupacion] = nuevo;
	
	ocupacion++;
}

void listarContactos(){
	int i;
	for(i=0;i<ocupacion;i++){
		printf("%d. %s\n",i,contactos[i].nombre);
	}
	printf("\n");
}

void borrarContacto(){
	
	int i,idBorrar;
	char cadena[128];
	
	printf("\n");
	printf("Que contacto quieres borrar?:");
	fflush(stdin);
	gets(cadena);
	idBorrar = atoi(cadena);
	
	if(idBorrar>=0 && idBorrar<ocupacion){
		for(i=idBorrar;i<=ocupacion-2;i++){
			contactos[i] = contactos[i+1];		
		}
		ocupacion--;
	}else{
		printf("No hay ningun contacto con ese Id\n");
	}	
}

void detalleContacto(){
	
	int idBuscado;
	char cadena[128];
	Contacto buscado;
	
	printf("\n");
	printf("De que contacto quieres info?:");
	fflush(stdin);
	gets(cadena);
	idBuscado = atoi(cadena);
	
	if(idBuscado>=0 && idBuscado<ocupacion){
		buscado = contactos[idBuscado];
		printf("Id: %d\n",idBuscado);
		printf("Nombre:%s\n",buscado.nombre);
		printf("Telefono:%ld\n",buscado.telefono);
		printf("Fecha Nacimiento:%d/%d/%d\n",buscado.fechaNacimiento.dia,buscado.fechaNacimiento.mes,buscado.fechaNacimiento.anho);
		
		switch(buscado.tipo){
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
	}else{
		printf("No hay ningun contacto con ese Id\n");
	}	
		
}

void cargarDatos(){
	
	
	//Arrays de Contactos con información
	

FILE* pfichero;
pfichero = fopen("contactos.txt","r");
char linea[1024];
char *ptr;
while(fgets(linea,128,pfichero)!=NULL){
	ptr=strtok(linea,"#");
	strcpy(contactos[ocupacion].nombre,ptr);
	ptr=strtok(NULL,"#");
	contactos[ocupacion].telefono=atoi(ptr);
	ptr=strtok(NULL,"#");
	contactos[ocupacion].fechaNacimiento.dia=atoi(ptr);
	ptr=strtok(NULL,"#");
	contactos[ocupacion].fechaNacimiento.mes=atoi(ptr);
	ptr=strtok(NULL,"#");
	contactos[ocupacion].fechaNacimiento.anho=atoi(ptr);
	ptr=strtok(NULL,"#");
	contactos[ocupacion].tipo=ptr[0];
	ocupacion++;
}
}

void guardarDatos(){
	int i;
	FILE* pfichero;
	Contacto c;
	pfichero = fopen("contactos.txt","w");
	//pfichero = fopen("contactos.txt","w");
	for(i=0;i<ocupacion;i++){
		c = contactos[i];
		fprintf(pfichero, "%s#%ld#%d#%d#%d#%c\n", c.nombre, c.telefono, c.fechaNacimiento.dia,c.fechaNacimiento.mes,c.fechaNacimiento.anho,c.tipo);
		
	}
	fclose(pfichero);
}

int main(int argc, char *argv[]) {
	
	boolean salir;
	char opcion;
	
	int contador = 0;
	int resultado;
	char entrada[50];	

	
	while(TRUE){
printf("Contrasenhaa: \n");
		gets(entrada);
		if(esContrasenhaValida(entrada)){
			break;
		}contador++;
		if(contador==3){
			printf("No te sabes la contraseña listillo");
			return 0;
		}		
		
		
	}
	
	
	//Cargamos datos
	
	cargarDatos();

	//Mostramos el menu y esperamos opción hasta que se elija 5.Salir
	do{
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
		scanf("%c",&opcion);
		salir = FALSE;
		switch(opcion){
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
				guardarDatos();
				salir = TRUE;
				break;
			default:
				printf("Opcion no valida\n");
		}
		printf("\n\n");					
	}while(!salir);
	
	printf("Cerrando\n");

	system("pause");	
	return 0;
}
