#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	
	int edad, hijos, ayudaEdad, ayudaHijos, ayudaECivil, suma;
	char estadoCivil;
	printf("Digame su estado civil (s/c): ");
	scanf("%c", &estadoCivil);
	if (estadoCivil == 's'){
		ayudaECivil = 0;
	}else{
		ayudaECivil= 300;
	}
	printf("La ayuda que usted recibe de EC es de: %d\n", ayudaECivil);
	printf("Digame su edad: ");
	scanf("%d", &edad);
	if (edad<25){
		ayudaEdad = 100;
	}else if (edad >=25 && edad <= 40){
		ayudaEdad = 120;
	}else if (edad >40 ){
		ayudaEdad = 150;
	}
	printf("La ayuda que usted recibe de edad es de: %d\n",ayudaEdad);
	printf("Digame cuantos hijos tiene: ");
	scanf("%d", &hijos);
	if (hijos>1){
		ayudaHijos = 50*hijos;
	}else{
		ayudaHijos = 0;
	}
	printf("La ayuda que usted recibe por hijos es de: %d\n",ayudaHijos);
	suma = ayudaECivil+ayudaHijos+ayudaEdad;
	printf("La ayuda que usted recibe es de: %d\n",suma);
	return 0;
}

