#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX_LANZAMIENTOS 600000000

/* 		hacer un array
		aleatorio con srand
		bucle for
		dentro del bucle ni idea que poner
		un switch para cada millón
		*/

int main(int argc, char *argv[]) {
	int veces0,veces1,veces2,veces3,veces4,veces5;
	int lanzamientos;
	int aleatorio;
	
	veces0=0;
	veces1=0;
	veces2=0;
	veces3=0;
	veces4=0;
	veces5=0;
	
	//generador aleatorios
	srand(time(NULL));
	//lanzamos dado MAX_LANZAMIENTOS veces
	
	for(lanzamientos=0; lanzamientos < MAX_LANZAMIENTOS; lanzamientos++){
		aleatorio=rand()%6;
		switch(aleatorio){
			case 0:
				veces0++;
				break;
			case 1:
				veces1++;
				break;
			case 2:
				veces2++;
				break;
			case 3:
				veces3++;
				break;
			case 4:
				veces4++;
				break;
			case 5:
				veces5++;
				break;
		}
	}
	//mostramos estadistica
	printf("0 -> %d		(%f%%)\n",veces0, (veces0/(double)MAX_LANZAMIENTOS)*100);
	printf("1 -> %d		(%f%%)\n",veces1, (veces0/(double)MAX_LANZAMIENTOS)*100);
	printf("2 -> %d		(%f%%)\n",veces2, (veces0/(double)MAX_LANZAMIENTOS)*100);
	printf("3 -> %d		(%f%%)\n",veces3, (veces0/(double)MAX_LANZAMIENTOS)*100);
	printf("4 -> %d		(%f%%)\n",veces4, (veces0/(double)MAX_LANZAMIENTOS)*100);
	printf("5 -> %d		(%f%%)\n",veces5, (veces0/(double)MAX_LANZAMIENTOS)*100);
	
	return 0;
}
