#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define FALSE 0
#define TRUE 1
#define MAX_CONTACTOS 20

typedef int Boolean;

typedef struct{
    int dia;
    int mes;
    int anho;
}Fecha;

typedef struct{
	char nombre[50];
	long telefono;
	Fecha fechaNacimiento;
	char tipo;
}Contacto;
//los ---- son estructuras, los .... son campos
Contacto contactos[MAX_CONTACTOS];

int ocupacion;



Boolean esTipoValido(char c){
	Boolean valido;
	 if(c!='a'&c!='e'&c!='f'&c!='t'){
		printf("TIPO NO VALIDO, VUELVA A INTENTARLO\n");
		return FALSE;
	}else{
		return TRUE;
	}
}
Boolean esContrasenaValida(char *cadena){
	char contrasena[125]="P@ssw0rd";
	if(cadena!=contrasena){
		return FALSE;
	}else{
		return TRUE;
	}
}
Fecha creaFecha(int dia, int mes, int anho){
	
}
									
void listarContactos(){
	printf("\n");
	int i;
	for(i = 0; i<ocupacion;i++){
		printf("%d.%s\n",i,contactos[i].nombre);
	}
	printf("\n");
}

void cargarDatos(){
	//abrimos fichero en modo lectura
	FILE* pfichero;
	pfichero=fopen("contactos.txt","r");
	char linea[1024];
	char *ptr;
	while(fgets(linea,128,pfichero)!=NULL){
		ptr=strtok(linea,"#");
		strcpy(contactos[ocupacion].nombre,ptr);
		ptr=strtok(NULL,"#");
		contactos[ocupacion].telefono=atol(ptr);
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
	pfichero=fopen("contactos.txt","w");
	for(i=0;i<ocupacion;i++){
		c=contactos[i];
		fprintf(pfichero,"%s#%ld#%d#%d#%d#%c\n",c.nombre,c.telefono,c.fechaNacimiento.dia,c.fechaNacimiento.mes,c.fechaNacimiento.anho,c.tipo);
	}
	fclose(pfichero);
}

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
		printf("Nombre: %s\n",contactos[id].nombre);
		printf("Telefono: %ld\n",contactos[id].telefono);
		printf("Fecha Nacimiento: %d/%d/%d\n",contactos[id].fechaNacimiento.dia,contactos[id].fechaNacimiento.mes,contactos[id].fechaNacimiento.anho);
		if(contactos[id].tipo=='a'){
				printf("Tipo: Amigo\n");
			}else if(contactos[id].tipo=='e'){
				printf("Tipo: Enemigo\n");
			}else if(contactos[id].tipo=='t'){
				printf("Tipo: Trabajo\n");
			}else if(contactos[id].tipo=='f'){
				printf("Tipo: Familiar\n");
			}
	}else{
		printf("No existe ningun contacto con esa ID\n");
	}
}
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
	strcpy(contactos[ocupacion].nombre,entrada);
	
	fflush(stdin);
	printf("Telefono: \n");
	gets(entrada);
	contactos[ocupacion].telefono=atol(entrada);
	
	fflush(stdin);
	printf("Fecha Nacimiento: \n\n");
	printf("Dame el dia: \n");
		gets(entrada);
	contactos[ocupacion].fechaNacimiento.dia=atoi(entrada);
	printf("Dame el mes: \n");
    	gets(entrada);
	contactos[ocupacion].fechaNacimiento.mes=atoi(entrada);
	printf("Dame el anho: \n");
        gets(entrada);
	contactos[ocupacion].fechaNacimiento.anho=atoi(entrada);
	//AÑADIR AQUI LO DE FECHA VALIDA
	do{
	fflush(stdin);
	printf("Tipo Contacto (Amigo(a), Enemigo(e), Familiar(f) o Trabajo(t)): \n");
	gets(entrada);
	}while(!esTipoValido(entrada[0]));
	contactos[ocupacion].tipo = entrada[0];
	
	ocupacion++;
	
}
void borrarContactos(){
	listarContactos();
	fflush(stdin);
	char temp[128];
	printf("Que contacto quiere borrar? \n");
	gets(temp);
	int borrado = atoi(temp);
	int i;
	if(borrado>ocupacion&&borrado>=0){
		printf("Contacto no existente, fuera de rango\n");
	}else{
	
	//Hay que pedir el contacto que se quiere borrar y en cada array sobreescribirlo con el siguiente de ocupacion, es decir si quiero borrar algo de la posicion 2, muevo los de la pos 3,4,5,... una posicion adelante. ocupacion-1.
	//for para intentar borrar el nombre
	for(i=borrado;i<ocupacion-1;i++){
		contactos[i]=contactos[i+1];
		}
		ocupacion--;
		}
	}
									
int main(int argc, char *argv[]) {
	int selMenu;
	int salir;
	int i;
	char entrada[1024];
	char contrasena[50];
	/*do{
	printf("La contrasena por favor:\n");
	gets(entrada);
	strcpy(contrasena,entrada);
	printf("%s",contrasena);
}while(!esContrasenaValida(contrasena));*/
	//Cargamos datos
	cargarDatos();
	do{
	fflush(stdin);
	printf("~~~~~~~~~~AGENDA DE CONTACTOS~~~~~~~~~~\n");
	printf("1.Nuevo\n2.Lista todos los contactos\n3.Eliminar\n4.Detalle\n5.Salir\n\n");
	printf("Seleccione(1|2|3|4|5): ");
	scanf("%d",&selMenu);
        salir=FALSE;
	switch(selMenu){
		case 1:	nuevoContactos();
			break;
		case 2:	listarContactos();
			break;
		case 3:	borrarContactos();
			break;
		case 4:	detalleContactos();
			break;
		case 5:	
			guardarDatos();
				salir=TRUE;
			break;
		default:
		printf("ACCION NO VALIDA!\n");					
	}
	system("pause");
	system("cls");
}while(!salir);
printf("Bye,Bye");
	return 0;
}
