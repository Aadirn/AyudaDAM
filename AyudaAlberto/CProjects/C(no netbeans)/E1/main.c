#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	int temp;
	printf("Digame la temperatura: ");
	
	//Logica del programa formada por un condicional
	
	scanf("%d",&temp);
	if(temp<5){
		printf("Esto es Sibeia \n");
	} else if (temp>=5 && temp<=10){
		printf("La cosa esta fresca \n");
	} else if (temp >=11 && temp <=20){
		printf("Templadito, templadito... \n");
	}else{
		printf("Ya llegan los calores \n");
	}
	system("pause");
	return 0;
}
