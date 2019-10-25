#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	char origen[100];
	char destino[100];
	
	destino=origen; //Esto no
	
	strcpy(destino, origen); //esto si
	
	int i=0;
	while (origen [i]!='\0'){
		destino[i]=origen[i];
		i++;
	}
	destino[i]='\0';
	//si hubiera hecho una estructura así
	struct persona{
		char nombre[50];
		char direccion[100];
		long telefono;
		short edad;
		float peso;
	};
	//declararia variables asi
	struct persona p1,p2,p3;
	//asignar valores asi:
	p1.telefono = 981212223;
	p1.edad = 33;
	p1.peso = 66,6;
	p1.nombre = "Pepe Grillo"; //NOPE
	strcpy(p1.nombre,"Pepe Grillo");//YES
	//lecturas por teclado
		//string temporal
		char tmp[100];
		//le lee desde teclado con gets
		gets(tmp);
		//Se copia al destino
		strcpy(p1.nombre,tmp);
		//O directamente
		gets(p1.nombre);
		
		
		//En c se pueden crear "alias" de tipos de datos preexistentes
		typedef int entero;
		//y puedo hacer
		entero x,y,z;
		//Es muy util para crear alias de estructuras
		typedef struct persona{
			char nombre[50];
			char direccion[100];
			long telefono;
			short edad;
			float peso;
		}Persona;
		Persona p1,p2,p3;
		Persona personas[50]; //Declaro array para guardar 50 personas
		//y puedo hacer cosas del tipo
		for(i=0;i<50;i++){
			printf("%s\n",personas[i].nombre);//que imprime linea a linea los nombres
		}
		
		
		
	system("pause");
	return 0;
}
