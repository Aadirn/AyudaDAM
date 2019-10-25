#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define FALSE 0
#define TRUE 1
#define MAX_CONTACTOS 20
/* run this program using the console pauser or add your own getch, system("pause") or input loop
//habrá un menú con estas opciones:
1.Nuevo
2.Lista todos los contactos
3.Eliminar
4.Detalle
5.Salir
Un contacto tiene la siguiente informacion:
nombre hasta 50(cadena)
telefono guardado numericamente(long)
fecha nacimiento cadena dd/mm/aaaa
tipo de contacto tipo char: amigo, enemigo, familiar o trabajo(a,e,f,t)

La agenda de contactos se implementa con un conjunto de arrays paralelos de capacidad maxima 20*/
typedef int boolean;

char nombres[MAX_CONTACTOS][50+1]={"Pericolo Spalotes","Merche Roma", "Pepito Grillo"};
long telefonos[MAX_CONTACTOS]={666548721,658936777, 611235455};
char fechasNacimiento[MAX_CONTACTOS][12]={"20/12/1966","01/03/1999","07/05/2001"};
char tipos[MAX_CONTACTOS]={'a','f','a'};

//Cantidad de contactos en la agenda que deberá ir aumentando conforme nuevos contactos se añaden
int ocupacion=3;

void nuevoContactos(){
	//comprobar que la agenda no esta llena
	if(ocupacion==MAX_CONTACTOS){
		printf("La agenda esta al completo.\n");
		return;
	}
	char entrada[1024];
	printf("Introduzca a continuacion los datos del nuevo contacto\n");
	/*Tengo que pedir con un gets el nombre, el telefono, la fecha de nacimiento que de momento no la voy a verificar de si está en el formato dd/mm/aaaa
	y el tipo de amigo, enemigo, blablabla.
	Tengo que a fuerza guardarlos en los arrays ya creados arriba y ocupacion modificarlo y aumentarlo en uno, lo unico que no se es si 
	al aumentarlo modificaré la variable original.*/
	fflush(stdin);
	printf("Nombre completo: \n");
	gets(entrada);
	strcpy(nombres[ocupacion],entrada);
	
	fflush(stdin);
	printf("Telefono: \n");
	gets(entrada);
	telefonos[ocupacion]=atol(entrada);
	
	fflush(stdin);
	printf("Fecha Nacimiento (dd/mm/aaaa): \n");
	gets(entrada);
	strcpy(fechasNacimiento[ocupacion],entrada);
	
	fflush(stdin);
	printf("Tipo Contacto (Amigo(a), Enemigo(e), Familiar(f) o Trabajo(t)): \n");
	gets(entrada);
	tipos[ocupacion] = entrada[0];
	
	ocupacion++;
	
}
void listarContactos(){
	printf("\n");
	int i;
	for(i = 0; i<ocupacion;i++){
		printf("%d.%s\n",i,nombres[i]);
	}
	printf("\n");
}
void borrarContactos(){
	listarContactos();
	fflush(stdin);
	char temp[128];
	printf("Que contacto quiere borrar? \n");
	gets(temp);
	int borrado = atoi(temp);
	int i,j,k,l;
	if(borrado>ocupacion){
		printf("Contacto no existente, fuera de rango\n");
	}else{
	
	//Hay que pedir el contacto que se quiere borrar y en cada array sobreescribirlo con el siguiente de ocupacion, es decir si quiero borrar algo de la posicion 2, muevo los de la pos 3,4,5,... una posicion adelante. ocupacion-1.
	//for para intentar borrar el nombre
	for(i=borrado;i<ocupacion-1;i++){
		strcpy(nombres[i],nombres[i+1]);
		
		telefonos[i]=telefonos[i+1];
		
		strcpy(fechasNacimiento[i],fechasNacimiento[i+1]);
		
		tipos[i]=tipos[i+1];
		}
		ocupacion--;
		}
	}
//id:1/nombre luis pitis/ telefono: 123456789/ fechaNac:11//22/3344/ tipo contacto:amigo
void detalleContactos(){
	listarContactos();
	int p,j,k;
	char temp[128];
	fflush(stdin);
	printf("De que contacto quieres informacion?: \n");
	gets(temp);
	int id=atoi(temp);
	if(id>=0&&id<ocupacion){
		printf("ID: %d\n",id);
		printf("Nombre: %s\n",nombres[id]);
		printf("Telefono: %ld\n",telefonos[id]);
		printf("Fecha Nacimiento: %s\n",fechasNacimiento[id]);
		if(tipos[id]=='a'){
				printf("Tipo: Amigo\n");
			}else if(tipos[id]=='e'){
				printf("Tipo: Enemigo\n");
			}else if(tipos[id]=='t'){
				printf("Tipo: Trabajo\n");
			}else if(tipos[id]=='f'){
				printf("Tipo: Familiar\n");
			}
	}else{
		printf("No existe ningun contacto con esa ID\n");
	}
}
void finApp(){
	printf("Se ha seleccionado salir\n");
}
int main(int argc, char *argv[]) {
	int selMenu;
	boolean salir;
	do{
	fflush(stdin);
	printf("~~~~~~~~~~AGENDA DE CONTACTOS~~~~~~~~~~\n");
	printf("1.Nuevo\n2.Lista todos los contactos\n3.Eliminar\n4.Detalle\n5.Salir\n\n");
	printf("Seleccione(1|2|3|4|5): ");
	scanf("%d",&selMenu);
	switch(selMenu){
		salir=FALSE;
		case 1:	nuevoContactos();
			break;
		case 2:	listarContactos();
			break;
		case 3:	borrarContactos();
			break;
		case 4:	detalleContactos();
			break;
		case 5:	finApp();
				salir=TRUE;
			break;
		default:
		printf("ACCION NO VALIDA!\n");					
	}
	system("pause");
	system("cls");
}while(!salir);	
/*typedef struct persona{
			String nombre[50];
			long telefono;
			String edad;
			char contacto;
		}Persona;*/
		printf("Bye,Bye");
	return 0;
}
